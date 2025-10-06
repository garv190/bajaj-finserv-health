package com.finserv.sqlsolver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Utility class for JSON operations
 * 
 * This utility provides helper methods for JSON serialization, deserialization,
 * and logging of JSON data for debugging purposes during API interactions.
 * 
 * @author Professional Technical Coder
 */
@Component
public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private final ObjectMapper objectMapper;

    public JsonUtil() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Converts an object to JSON string
     * 
     * @param object Object to convert
     * @return JSON string representation
     */
    public String toJsonString(Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            log.debug("Successfully converted object to JSON");
            return json;
        } catch (JsonProcessingException e) {
            log.error("Error converting object to JSON: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Converts JSON string to object of specified type
     * 
     * @param jsonString JSON string
     * @param clazz Target class type
     * @param <T> Type parameter
     * @return Converted object
     */
    public <T> T fromJsonString(String jsonString, Class<T> clazz) {
        try {
            T object = objectMapper.readValue(jsonString, clazz);
            log.debug("Successfully converted JSON to object of type: {}", clazz.getSimpleName());
            return object;
        } catch (JsonProcessingException e) {
            log.error("Error converting JSON to object: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Pretty prints JSON for logging purposes
     * 
     * @param object Object to pretty print
     * @return Formatted JSON string
     */
    public String toPrettyJsonString(Object object) {
        try {
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            log.debug("Successfully created pretty JSON");
            return prettyJson;
        } catch (JsonProcessingException e) {
            log.error("Error creating pretty JSON: {}", e.getMessage());
            return toJsonString(object); // Fallback to regular JSON
        }
    }

    /**
     * Safely logs JSON data without exposing sensitive information
     * 
     * @param object Object to log
     * @param description Description of the object being logged
     */
    public void logJsonSafely(Object object, String description) {
        try {
            String json = toJsonString(object);
            if (json != null) {
                // Log first 100 characters to avoid exposing full sensitive data
                String safeJson = json.length() > 100 ? json.substring(0, 100) + "..." : json;
                log.info("{}: {}", description, safeJson);
            }
        } catch (Exception e) {
            log.warn("Could not log JSON for {}: {}", description, e.getMessage());
        }
    }

    /**
     * Validates if a string is valid JSON
     * 
     * @param jsonString String to validate
     * @return true if valid JSON, false otherwise
     */
    public boolean isValidJson(String jsonString) {
        try {
            objectMapper.readTree(jsonString);
            return true;
        } catch (JsonProcessingException e) {
            log.debug("Invalid JSON string: {}", e.getMessage());
            return false;
        }
    }
}