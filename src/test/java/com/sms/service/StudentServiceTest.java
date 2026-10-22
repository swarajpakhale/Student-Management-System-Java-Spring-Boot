package com.sms.service;

import com.sms.exception.DuplicateRecordException;
import com.sms.exception.StudentNotFoundException;
import com.sms.model.Student;
import com.sms.repository.StudentRepository;
import com.sms.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests verifying StudentService business rules, CRUD workflows, and duplicate rejection.
 * Author: Swaraj Surendra Pakhale (Roll No: 172)
 * College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 */
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student testStudent;

    @BeforeEach
    void setUp() {
        testStudent = new Student(
                "Swaraj", "Pakhale", "swaraj.pakhale@ycce.edu",
                "Computer Technology", "CT-172", "253070172",
                "Semester 7", BigDecimal.valueOf(9.15), "9876543210"
        );
        testStudent.setId(1L);
    }

    @Test
    @DisplayName("Test 2: Create student successfully when email and roll number are unique")
    void testCreateStudent_Success() {
        when(studentRepository.existsByEmail("swaraj.pakhale@ycce.edu")).thenReturn(false);
        when(studentRepository.existsByRollNumber("CT-172")).thenReturn(false);
        when(studentRepository.existsByEnrollmentNumber("253070172")).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(testStudent);

        Student created = studentService.createStudent(testStudent);

        assertNotNull(created);
        assertEquals("Swaraj", created.getFirstName());
        assertEquals("CT-172", created.getRollNumber());
        verify(studentRepository, times(1)).save(testStudent);
    }

    @Test
    @DisplayName("Test 3: Create student throws DuplicateRecordException if email already exists")
    void testCreateStudent_DuplicateEmailThrowsException() {
        when(studentRepository.existsByEmail("swaraj.pakhale@ycce.edu")).thenReturn(true);

        assertThrows(DuplicateRecordException.class, () -> studentService.createStudent(testStudent));
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    @DisplayName("Test 4: Create student throws DuplicateRecordException if roll number already exists")
    void testCreateStudent_DuplicateRollNumberThrowsException() {
        when(studentRepository.existsByEmail("swaraj.pakhale@ycce.edu")).thenReturn(false);
        when(studentRepository.existsByRollNumber("CT-172")).thenReturn(true);

        assertThrows(DuplicateRecordException.class, () -> studentService.createStudent(testStudent));
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    @DisplayName("Test 5: Update student updates existing attributes successfully")
    void testUpdateStudent_Success() {
        Student updateDetails = new Student(
                "Swaraj", "Pakhale", "swaraj.updated@ycce.edu",
                "Computer Technology", "CT-172", "253070172",
                "Semester 8", BigDecimal.valueOf(9.40), "9876543210"
        );

        when(studentRepository.findById(1L)).thenReturn(Optional.of(testStudent));
        when(studentRepository.findByEmail("swaraj.updated@ycce.edu")).thenReturn(Optional.empty());
        when(studentRepository.save(any(Student.class))).thenReturn(testStudent);

        Student updated = studentService.updateStudent(1L, updateDetails);

        assertNotNull(updated);
        assertEquals(BigDecimal.valueOf(9.40), updated.getCgpa());
        assertEquals("Semester 8", updated.getSemester());
        verify(studentRepository, times(1)).save(testStudent);
    }

    @Test
    @DisplayName("Test 6: Delete student removes record if found")
    void testDeleteStudent_Success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(testStudent));
        doNothing().when(studentRepository).delete(testStudent);

        assertDoesNotThrow(() -> studentService.deleteStudent(1L));
        verify(studentRepository, times(1)).delete(testStudent);
    }

    @Test
    @DisplayName("Test 7: Search students returns list matching keyword")
    void testSearchStudents_ReturnsMatchingList() {
        when(studentRepository.searchStudents("Pakhale")).thenReturn(Collections.singletonList(testStudent));

        List<Student> results = studentService.searchStudents("Pakhale");

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Pakhale", results.get(0).getLastName());
    }
}
