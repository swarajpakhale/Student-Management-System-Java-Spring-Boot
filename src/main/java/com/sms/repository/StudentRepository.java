package com.sms.repository;

import com.sms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA Repository for Student entity with search and aggregation queries.
 * Author: Swaraj Surendra Pakhale (Roll No: 172)
 * College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findByRollNumber(String rollNumber);

    Optional<Student> findByEnrollmentNumber(String enrollmentNumber);

    boolean existsByEmail(String email);

    boolean existsByRollNumber(String rollNumber);

    boolean existsByEnrollmentNumber(String enrollmentNumber);

    List<Student> findAllByOrderByIdDesc();

    List<Student> findTop5ByOrderByIdDesc();

    @Query("SELECT s FROM Student s WHERE " +
           "LOWER(s.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.rollNumber) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.department) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "ORDER BY s.id DESC")
    List<Student> searchStudents(@Param("keyword") String keyword);

    long countByDepartment(String department);
}
