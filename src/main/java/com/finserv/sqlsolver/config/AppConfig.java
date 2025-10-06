package com.finserv.sqlsolver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Application Configuration Class
 * 
 * This configuration class defines all the beans required for the application,
 * including HTTP client configurations and other application-level settings.
 * 
 * @author Professional Technical Coder
 */
@Configuration
public class AppConfig {

    /**
     * Configures RestTemplate with appropriate timeout settings for API calls
     * 
     * @return RestTemplate instance with custom configuration
     */
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        
        // Set connection timeout (30 seconds)
        factory.setConnectTimeout(30000);
        
        // Set read timeout (30 seconds)  
        factory.setReadTimeout(30000);
        
        return new RestTemplate(factory);
    }
}