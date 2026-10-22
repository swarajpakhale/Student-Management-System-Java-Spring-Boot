package com.sms.controller;

import com.sms.dto.DashboardStatsDto;
import com.sms.model.Student;
import com.sms.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Controller-level integration tests using MockMvc verifying views, models, and validation errors.
 * Author: Swaraj Surendra Pakhale (Roll No: 172)
 * College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 */
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    @DisplayName("Test 8: GET /dashboard returns dashboard view with populated stats model")
    void testDashboard_ReturnsDashboardView() throws Exception {
        DashboardStatsDto stats = new DashboardStatsDto();
        stats.setTotalStudents(6);
        stats.setComputerTechCount(3);
        when(studentService.getDashboardStats()).thenReturn(stats);

        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(model().attributeExists("stats"))
                .andExpect(model().attribute("activePage", "dashboard"));
    }

    @Test
    @DisplayName("Test 9: GET /students returns students view with student records")
    void testListStudents_ReturnsStudentsView() throws Exception {
        Student s = new Student(
                "Swaraj", "Pakhale", "swaraj.pakhale@ycce.edu",
                "Computer Technology", "CT-172", "253070172",
                "Semester 7", BigDecimal.valueOf(9.15), "9876543210"
        );
        when(studentService.searchStudents(null)).thenReturn(Collections.singletonList(s));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students"))
                .andExpect(model().attributeExists("students"));
    }

    @Test
    @DisplayName("Test 10: POST /students with blank first name fails validation and returns form view")
    void testSaveStudent_ValidationErrorOnEmptyFirstName() throws Exception {
        mockMvc.perform(post("/students")
                        .param("firstName", "")
                        .param("lastName", "Pakhale")
                        .param("email", "swaraj@ycce.edu")
                        .param("department", "Computer Technology")
                        .param("rollNumber", "CT-172")
                        .param("enrollmentNumber", "253070172")
                        .param("semester", "Semester 7")
                        .param("cgpa", "9.15")
                        .param("contactNumber", "9876543210"))
                .andExpect(status().isOk())
                .andExpect(view().name("student_form"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("student", "firstName"));

        verify(studentService, never()).createStudent(any(Student.class));
    }

    @Test
    @DisplayName("Test 11: POST /students with invalid email format fails validation")
    void testSaveStudent_ValidationErrorOnInvalidEmail() throws Exception {
        mockMvc.perform(post("/students")
                        .param("firstName", "Swaraj")
                        .param("lastName", "Pakhale")
                        .param("email", "not-a-valid-email")
                        .param("department", "Computer Technology")
                        .param("rollNumber", "CT-172")
                        .param("enrollmentNumber", "253070172")
                        .param("semester", "Semester 7")
                        .param("cgpa", "9.15")
                        .param("contactNumber", "9876543210"))
                .andExpect(status().isOk())
                .andExpect(view().name("student_form"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("student", "email"));

        verify(studentService, never()).createStudent(any(Student.class));
    }
}
