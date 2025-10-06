package com.finserv.sqlsolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application Class for Bajaj Finserv SQL Solver
 * 
 * This application automatically executes on startup to:
 * 1. Generate a webhook URL by calling the Bajaj Finserv API
 * 2. Solve the SQL problem (calculate younger employees count by department)
 * 3. Submit the solution to the webhook URL using JWT authentication
 * 
 * Architecture:
 * - config/: Configuration classes and beans
 * - model/: Data models and DTOs
 * - service/: Business logic services
 * - util/: Utility classes for JWT and JSON handling
 * - runner/: Startup execution logic
 * 
 * @author Professional Technical Coder
 * @version 1.0
 */
@SpringBootApplication
public class BajajFinservSqlSolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(BajajFinservSqlSolverApplication.class, args);
    }
}