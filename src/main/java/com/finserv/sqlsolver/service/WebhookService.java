package com.finserv.sqlsolver.service;

import com.finserv.sqlsolver.model.WebhookRequest;
import com.finserv.sqlsolver.model.WebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class for handling webhook generation API calls
 * 
 * This service is responsible for:
 * 1. Making a POST request to generate webhook URL
 * 2. Parsing the response to extract webhook URL and access token
 * 3. Handling any errors during the API call
 * 
 * @author Professional Technical Coder
 */
@Service
public class WebhookService {

    private static final Logger log = LoggerFactory.getLogger(WebhookService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.webhook.generate.url}")
    private String webhookGenerateUrl;

    /**
     * Generates webhook URL by calling the Bajaj Finserv API
     * 
     * @param name Candidate's full name
     * @param regNo Registration number
     * @param email Candidate's email
     * @return WebhookResponse containing webhook URL and access token
     * @throws RuntimeException if API call fails
     */
    public WebhookResponse generateWebhook(String name, String regNo, String email) {
        log.info("Starting webhook generation for candidate: {}", name);
        
        try {
            // Create request payload
            WebhookRequest request = new WebhookRequest(name, regNo, email);
            
            // Set up HTTP headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            // Create HTTP entity with headers and body
            HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);
            
            log.info("Sending POST request to: {}", webhookGenerateUrl);
            log.debug("Request payload: {}", request);
            
            // Make the API call
            ResponseEntity<WebhookResponse> response = restTemplate.exchange(
                webhookGenerateUrl,
                HttpMethod.POST,
                entity,
                WebhookResponse.class
            );
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                WebhookResponse webhookResponse = response.getBody();
                log.info("Successfully generated webhook URL: {}", webhookResponse.getWebhook());
                log.debug("Access token received (first 10 chars): {}...", 
                    webhookResponse.getAccessToken().substring(0, Math.min(10, webhookResponse.getAccessToken().length())));
                
                return webhookResponse;
            } else {
                throw new RuntimeException("Failed to generate webhook. Status: " + response.getStatusCode());
            }
            
        } catch (Exception e) {
            log.error("Error generating webhook: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to generate webhook URL", e);
        }
    }
}