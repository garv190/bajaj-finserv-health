package com.finserv.sqlsolver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Payload model for submitting the SQL solution
 * 
 * This class represents the request body that contains the final SQL query solution
 * to be submitted to the webhook URL for assessment evaluation.
 * 
 * @author Professional Technical Coder
 */
public class SqlQueryPayload {
    
    /**
     * The final SQL query that solves the given assessment problem
     * For this assessment: calculating younger employees count by department
     */
    @JsonProperty("finalQuery")
    private String finalQuery;
    
    // Default constructor
    public SqlQueryPayload() {}
    
    // All args constructor
    public SqlQueryPayload(String finalQuery) {
        this.finalQuery = finalQuery;
    }
    
    // Getters and Setters
    public String getFinalQuery() { return finalQuery; }
    public void setFinalQuery(String finalQuery) { this.finalQuery = finalQuery; }
}