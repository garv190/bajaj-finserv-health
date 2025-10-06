package com.finserv.sqlsolver.service;

import com.finserv.sqlsolver.model.SqlQueryPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class for submitting the SQL solution to the webhook URL
 * 
 * This service is responsible for:
 * 1. Preparing the final SQL query solution payload
 * 2. Making a POST request to the webhook URL with JWT authentication
 * 3. Handling the response and any errors during submission
 * 
 * @author Professional Technical Coder
 */
@Service
public class SubmissionService {

    private static final Logger log = LoggerFactory.getLogger(SubmissionService.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Submits the SQL solution to the provided webhook URL
     * 
     * @param webhookUrl The URL where the solution should be submitted
     * @param accessToken JWT token for authentication
     * @param sqlQuery The final SQL query solution
     * @return true if submission is successful, false otherwise
     */
    public boolean submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        log.info("Starting solution submission to webhook: {}", webhookUrl);
        
        try {
            // Create request payload with the final SQL query
            SqlQueryPayload payload = new SqlQueryPayload(sqlQuery);
            
            // Set up HTTP headers with JWT authentication
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + accessToken);
            
            // Create HTTP entity with headers and body
            HttpEntity<SqlQueryPayload> entity = new HttpEntity<>(payload, headers);
            
            log.info("Submitting solution to: {}", webhookUrl);
            log.info("Using Authorization header: Bearer {}", accessToken.substring(0, Math.min(20, accessToken.length())) + "...");
            log.info("Headers: {}", headers);
            log.info("SQL Query being submitted: {}", sqlQuery.trim());
            
            // Make the API call
            ResponseEntity<String> response = restTemplate.exchange(
                webhookUrl,
                HttpMethod.POST,
                entity,
                String.class
            );
            
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Solution submitted successfully! Status: {}", response.getStatusCode());
                log.debug("Response body: {}", response.getBody());
                return true;
            } else {
                log.error("Failed to submit solution. Status: {}, Body: {}", 
                    response.getStatusCode(), response.getBody());
                return false;
            }
            
        } catch (Exception e) {
            log.error("Error submitting solution: {}", e.getMessage(), e);
            return false;
        }
    }
}