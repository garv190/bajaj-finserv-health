package com.finserv.sqlsolver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for webhook generation API
 * 
 * This class represents the response received from the webhook generation API.
 * Contains the webhook URL where the solution should be submitted and the access token for authentication.
 * 
 * @author Professional Technical Coder
 */
public class WebhookResponse {
    
    /**
     * The webhook URL where the SQL solution should be submitted
     */
    @JsonProperty("webhookUrl")
    private String webhookUrl;
    
    /**
     * JWT access token to be used in Authorization header for solution submission
     */
    @JsonProperty("accessToken")
    private String accessToken;
    
    // Default constructor
    public WebhookResponse() {}
    
    // All args constructor
    public WebhookResponse(String webhookUrl, String accessToken) {
        this.webhookUrl = webhookUrl;
        this.accessToken = accessToken;
    }
    
    // Getters and Setters
    public String getWebhookUrl() { return webhookUrl; }
    public void setWebhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; }
    
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    
    // Legacy getter for backward compatibility and common usage
    public String getWebhook() { return webhookUrl; }
    public void setWebhook(String webhook) { this.webhookUrl = webhook; }
    
    // Validation method
    public boolean isValid() {
        return webhookUrl != null && !webhookUrl.trim().isEmpty() &&
               accessToken != null && !accessToken.trim().isEmpty();
    }
}