package com.sms.service;

import com.sms.dto.DashboardStatsDto;
import com.sms.model.Student;
import java.util.List;

/**
 * Service interface for Student management operations.
 * Author: Swaraj Surendra Pakhale (Roll No: 172)
 * College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 */
public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student createStudent(Student student);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    List<Student> searchStudents(String keyword);

    DashboardStatsDto getDashboardStats();
}
