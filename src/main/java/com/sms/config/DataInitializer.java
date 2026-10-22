package com.sms.config;

import com.sms.model.Student;
import com.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Initializes realistic sample student records for demonstration and academic evaluation.
 * Author: Swaraj Surendra Pakhale (Roll No: 172)
 * College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;

    @Autowired
    public DataInitializer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) {
        if (studentRepository.count() == 0) {
            Student s1 = new Student(
                    "Swaraj", "Pakhale", "swaraj.pakhale@ycce.edu",
                    "Computer Technology", "CT-172", "253070172",
                    "Semester 7", BigDecimal.valueOf(9.15), "9876543210"
            );

            Student s2 = new Student(
                    "Ananya", "Deshmukh", "ananya.deshmukh@ycce.edu",
                    "Computer Technology", "CT-105", "253070105",
                    "Semester 7", BigDecimal.valueOf(8.90), "9822114455"
            );

            Student s3 = new Student(
                    "Rohan", "Kulkarni", "rohan.kulkarni@ycce.edu",
                    "Information Technology", "IT-201", "253070201",
                    "Semester 5", BigDecimal.valueOf(8.45), "9844332211"
            );

            Student s4 = new Student(
                    "Pratiksha", "Sharma", "pratiksha.sharma@ycce.edu",
                    "Electronics & Telecomm", "ET-310", "253070310",
                    "Semester 7", BigDecimal.valueOf(9.30), "9855667788"
            );

            Student s5 = new Student(
                    "Yash", "Patil", "yash.patil@ycce.edu",
                    "Mechanical Engineering", "ME-405", "253070405",
                    "Semester 5", BigDecimal.valueOf(7.85), "9866778899"
            );

            Student s6 = new Student(
                    "Sneha", "Joshi", "sneha.joshi@ycce.edu",
                    "Computer Technology", "CT-142", "253070142",
                    "Semester 7", BigDecimal.valueOf(8.70), "9877889900"
            );

            studentRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6));
            System.out.println("Seeded 6 sample student records for YCCE evaluation.");
        }
    }
}
