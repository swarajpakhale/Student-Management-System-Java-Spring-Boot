-- Student Management System Database Schema (MySQL 8.0)
-- Academic Project for Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
-- Student: Swaraj Surendra Pakhale (Roll: 172, Branch: CT-B)

CREATE DATABASE IF NOT EXISTS `student_management_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `student_management_db`;

CREATE TABLE IF NOT EXISTS `students` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `department` VARCHAR(80) NOT NULL,
    `roll_number` VARCHAR(30) NOT NULL UNIQUE,
    `enrollment_number` VARCHAR(30) NOT NULL UNIQUE,
    `semester` VARCHAR(20) NOT NULL,
    `cgpa` DECIMAL(3, 2) NOT NULL,
    `contact_number` VARCHAR(15) NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE INDEX `idx_student_name` ON `students` (`last_name`, `first_name`);
CREATE INDEX `idx_student_dept` ON `students` (`department`);
