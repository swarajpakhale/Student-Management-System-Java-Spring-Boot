package com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application entry point for Student Management System.
 *
 * Student Details:
 * - Student Name: Swaraj Surendra Pakhale
 * - Roll Number: 172
 * - Branch: Computer Technology (CT-B)
 * - College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 * - Academic Year: 2026–2027
 */
@SpringBootApplication
public class StudentManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementSystemApplication.class, args);
        System.out.println("==================================================================");
        System.out.println(" Student Management System is Running on Port 8081!");
        System.out.println(" Access Web Application: http://localhost:8081");
        System.out.println(" Access H2 Database Console: http://localhost:8081/h2-console");
        System.out.println(" Developed by: Swaraj Surendra Pakhale (Roll: 172, CT-B), YCCE Nagpur");
        System.out.println("==================================================================");
    }
}
