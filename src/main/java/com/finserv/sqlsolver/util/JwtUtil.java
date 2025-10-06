package com.finserv.sqlsolver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Utility class for JWT token operations
 * 
 * This utility provides helper methods for JWT token handling,
 * validation, and header creation for the assessment API calls.
 * 
 * @author Professional Technical Coder
 */
@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
    private static final String BEARER_PREFIX = "Bearer ";
    
    /**
     * Creates Authorization header value with Bearer prefix
     * 
     * @param token JWT token
     * @return Complete Authorization header value
     */
    public String createBearerToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            log.warn("Attempting to create Bearer token with null or empty token");
            return null;
        }
        
        String bearerToken = BEARER_PREFIX + token.trim();
        log.debug("Created Bearer token with length: {}", bearerToken.length());
        return bearerToken;
    }
    
    /**
     * Validates if the token is not null or empty
     * 
     * @param token JWT token to validate
     * @return true if token is valid, false otherwise
     */
    public boolean isValidToken(String token) {
        boolean isValid = token != null && !token.trim().isEmpty();
        log.debug("Token validation result: {}", isValid);
        return isValid;
    }
    
    /**
     * Extracts token from Bearer header
     * 
     * @param authHeader Authorization header value
     * @return JWT token without Bearer prefix
     */
    public String extractTokenFromBearer(String authHeader) {
        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            String token = authHeader.substring(BEARER_PREFIX.length()).trim();
            log.debug("Extracted token from Bearer header");
            return token;
        }
        log.warn("Invalid Bearer header format");
        return null;
    }
    
    /**
     * Logs token information for debugging (safely, without exposing the actual token)
     * 
     * @param token JWT token
     */
    public void logTokenInfo(String token) {
        if (token != null) {
            log.info("Token info - Length: {}, Starts with: {}...", 
                token.length(), 
                token.substring(0, Math.min(10, token.length())));
        } else {
            log.warn("Token is null");
        }
    }
}