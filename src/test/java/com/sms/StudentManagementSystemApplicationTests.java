package com.sms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Verifies that the Spring Boot ApplicationContext bootstraps without configuration issues.
 * Author: Swaraj Surendra Pakhale (Roll No: 172)
 * College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 */
@SpringBootTest
class StudentManagementSystemApplicationTests {

    @Test
    @DisplayName("Test 1: Application context loads successfully")
    void contextLoads() {
        // Assertion: Spring application context boots and wires all repository/service/controller beans
    }
}
