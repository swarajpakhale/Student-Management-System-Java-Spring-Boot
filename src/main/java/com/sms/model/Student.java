package com.sms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a Student record in the Student Management System.
 * Author: Swaraj Surendra Pakhale (Roll No: 172)
 * College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Email address is mandatory")
    @Email(message = "Please provide a valid email format (e.g., student@ycce.edu)")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "Department selection is mandatory")
    @Column(nullable = false, length = 80)
    private String department;

    @NotBlank(message = "Roll number is mandatory")
    @Column(nullable = false, unique = true, length = 30)
    private String rollNumber;

    @NotBlank(message = "Enrollment number is mandatory")
    @Column(nullable = false, unique = true, length = 30)
    private String enrollmentNumber;

    @NotBlank(message = "Semester is mandatory")
    @Column(nullable = false, length = 20)
    private String semester;

    @NotNull(message = "CGPA is mandatory")
    @DecimalMin(value = "0.00", message = "CGPA cannot be less than 0.00")
    @DecimalMax(value = "10.00", message = "CGPA cannot exceed 10.00")
    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal cgpa;

    @NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits")
    @Column(nullable = false, length = 15)
    private String contactNumber;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String department,
                   String rollNumber, String enrollmentNumber, String semester,
                   BigDecimal cgpa, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.rollNumber = rollNumber;
        this.enrollmentNumber = enrollmentNumber;
        this.semester = semester;
        this.cgpa = cgpa;
        this.contactNumber = contactNumber;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public BigDecimal getCgpa() {
        return cgpa;
    }

    public void setCgpa(BigDecimal cgpa) {
        this.cgpa = cgpa;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
