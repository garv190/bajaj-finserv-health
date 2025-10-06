package com.finserv.sqlsolver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for generating webhook URL
 * 
 * This class represents the request body that needs to be sent to the webhook generation API.
 * Contains candidate details required for the assessment registration.
 * 
 * @author Professional Technical Coder
 */
public class WebhookRequest {
    
    /**
     * Candidate's full name
     */
    @JsonProperty("name")
    private String name;
    
    /**
     * Registration number (determines question assignment based on last 2 digits)
     * Even numbers get Question 2, Odd numbers get Question 1
     */
    @JsonProperty("regNo")
    private String regNo;
    
    /**
     * Candidate's email address
     */
    @JsonProperty("email")
    private String email;
    
    // Default constructor
    public WebhookRequest() {}
    
    // All args constructor
    public WebhookRequest(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}