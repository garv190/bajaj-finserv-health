package com.finserv.sqlsolver.config;

import org.springframework.context.annotation.Configuration;

/**
 * JWT Configuration Class
 * 
 * This configuration class handles JWT-related settings and configurations.
 * Currently, JWT handling is done through RestTemplate headers, but this class
 * can be extended for more complex JWT operations if needed.
 * 
 * @author Professional Technical Coder
 */
@Configuration
public class JwtConfig {

    /**
     * JWT Bearer token prefix
     */
    public static final String BEARER_PREFIX = "Bearer ";
    
    /**
     * Authorization header name
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";
    
    /**
     * Creates Authorization header value with Bearer prefix
     * 
     * @param token JWT token
     * @return Complete Authorization header value
     */
    public String createAuthorizationHeader(String token) {
        return BEARER_PREFIX + token;
    }
}