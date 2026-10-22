package com.sms.service.impl;

import com.sms.dto.DashboardStatsDto;
import com.sms.exception.DuplicateRecordException;
import com.sms.exception.StudentNotFoundException;
import com.sms.model.Student;
import com.sms.repository.StudentRepository;
import com.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation containing core business validation and persistence logic.
 * Author: Swaraj Surendra Pakhale (Roll No: 172)
 * College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAllByOrderByIdDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student record not found with ID: " + id));
    }

    @Override
    public Student createStudent(Student student) {
        // Enforce uniqueness constraints
        if (studentRepository.existsByEmail(student.getEmail().trim().toLowerCase())) {
            throw new DuplicateRecordException("A student with email '" + student.getEmail() + "' already exists.");
        }
        if (studentRepository.existsByRollNumber(student.getRollNumber().trim())) {
            throw new DuplicateRecordException("A student with roll number '" + student.getRollNumber() + "' already exists.");
        }
        if (studentRepository.existsByEnrollmentNumber(student.getEnrollmentNumber().trim())) {
            throw new DuplicateRecordException("A student with enrollment number '" + student.getEnrollmentNumber() + "' already exists.");
        }

        student.setEmail(student.getEmail().trim().toLowerCase());
        student.setRollNumber(student.getRollNumber().trim());
        student.setEnrollmentNumber(student.getEnrollmentNumber().trim());

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = getStudentById(id);

        // Check if email belongs to someone else
        String newEmail = studentDetails.getEmail().trim().toLowerCase();
        if (!existingStudent.getEmail().equalsIgnoreCase(newEmail)) {
            Optional<Student> emailOwner = studentRepository.findByEmail(newEmail);
            if (emailOwner.isPresent() && !emailOwner.get().getId().equals(id)) {
                throw new DuplicateRecordException("Email '" + newEmail + "' is already assigned to another student.");
            }
        }

        // Check roll number uniqueness
        String newRoll = studentDetails.getRollNumber().trim();
        if (!existingStudent.getRollNumber().equalsIgnoreCase(newRoll)) {
            Optional<Student> rollOwner = studentRepository.findByRollNumber(newRoll);
            if (rollOwner.isPresent() && !rollOwner.get().getId().equals(id)) {
                throw new DuplicateRecordException("Roll number '" + newRoll + "' is already assigned to another student.");
            }
        }

        existingStudent.setFirstName(studentDetails.getFirstName().trim());
        existingStudent.setLastName(studentDetails.getLastName().trim());
        existingStudent.setEmail(newEmail);
        existingStudent.setDepartment(studentDetails.getDepartment().trim());
        existingStudent.setRollNumber(newRoll);
        existingStudent.setEnrollmentNumber(studentDetails.getEnrollmentNumber().trim());
        existingStudent.setSemester(studentDetails.getSemester().trim());
        existingStudent.setCgpa(studentDetails.getCgpa());
        existingStudent.setContactNumber(studentDetails.getContactNumber().trim());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student existing = getStudentById(id);
        studentRepository.delete(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllStudents();
        }
        return studentRepository.searchStudents(keyword.trim());
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardStatsDto getDashboardStats() {
        DashboardStatsDto stats = new DashboardStatsDto();
        long total = studentRepository.count();
        stats.setTotalStudents(total);

        stats.setComputerTechCount(studentRepository.countByDepartment("Computer Technology"));
        stats.setInfoTechCount(studentRepository.countByDepartment("Information Technology"));
        stats.setElectronicsCount(studentRepository.countByDepartment("Electronics & Telecomm"));
        stats.setOtherDeptCount(total - (stats.getComputerTechCount() + stats.getInfoTechCount() + stats.getElectronicsCount()));

        List<Student> all = studentRepository.findAll();
        if (!all.isEmpty()) {
            double sum = all.stream().map(Student::getCgpa).mapToDouble(BigDecimal::doubleValue).sum();
            stats.setAverageCgpa(Math.round((sum / all.size()) * 100.0) / 100.0);
        } else {
            stats.setAverageCgpa(0.0);
        }

        stats.setRecentStudents(studentRepository.findTop5ByOrderByIdDesc());
        return stats;
    }
}
