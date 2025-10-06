package com.finserv.sqlsolver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service class for solving SQL problems
 * 
 * This service is responsible for:
 * 1. Containing the SQL solution logic
 * 2. Generating the final SQL query for the assessment
 * 3. Providing detailed explanation of the solution approach
 * 
 * Problem: Calculate the number of employees who are younger than each employee,
 * grouped by their respective departments.
 * 
 * @author Professional Technical Coder
 */
@Service
public class SqlSolverService {

    private static final Logger log = LoggerFactory.getLogger(SqlSolverService.class);

    /**
     * The final SQL query that solves the assessment problem
     * 
     * Problem Analysis:
     * - We have three tables: DEPARTMENT, EMPLOYEE, and PAYMENTS
     * - Need to find younger employees in the same department for each employee
     * - Younger means later date of birth (DOB)
     * - Output should include employee details, department name, and count
     * - Results ordered by employee ID in descending order
     * 
     * Solution Logic:
     * 1. Main query: Select from EMPLOYEE table (e1) for each employee
     * 2. Department join: JOIN with DEPARTMENT table to get department names
     * 3. Self join: LEFT JOIN with EMPLOYEE table (e2) to find younger employees
     *    - Same department: e1.DEPARTMENT = e2.DEPARTMENT
     *    - Younger employee: e2.DOB > e1.DOB (later birth date = younger)
     * 4. Count: Use COUNT(e2.EMP_ID) to count younger employees
     * 5. Group: Group by employee details to get count per employee
     * 6. Order: ORDER BY EMP_ID DESC as required
     */
    private static final String FINAL_SQL_QUERY = """
        SELECT 
            e1.EMP_ID,
            e1.FIRST_NAME,
            e1.LAST_NAME,
            d.DEPARTMENT_NAME,
            COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT
        FROM EMPLOYEE e1
        JOIN DEPARTMENT d 
            ON e1.DEPARTMENT = d.DEPARTMENT_ID
        LEFT JOIN EMPLOYEE e2 
            ON e1.DEPARTMENT = e2.DEPARTMENT
            AND e2.DOB > e1.DOB
        GROUP BY 
            e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
        ORDER BY 
            e1.EMP_ID DESC;
        """;

    /**
     * Gets the final SQL query solution
     * 
     * @return The complete SQL query that solves the assessment problem
     */
    public String getFinalQuery() {
        log.info("Generating SQL solution for younger employees count by department");
        log.debug("SQL Query: {}", FINAL_SQL_QUERY.trim());
        return FINAL_SQL_QUERY;
    }

    /**
     * Provides explanation of the SQL solution
     * 
     * @return Human-readable explanation of the query logic
     */
    public String getQueryExplanation() {
        return """
            This SQL query calculates the number of employees who are younger than each employee 
            within their respective departments by:
            
            1. Self-joining the EMPLOYEE table to compare ages within the same department
            2. Using LEFT JOIN to ensure all employees are included (even if no younger colleagues)
            3. Filtering for younger employees using DOB comparison (e2.DOB > e1.DOB)
            4. Counting younger employees using COUNT(e2.EMP_ID)
            5. Grouping results by employee to get individual counts
            6. Ordering by EMP_ID in descending order as required
            
            The result provides each employee's details along with the count of younger 
            colleagues in their department.
            """;
    }
}