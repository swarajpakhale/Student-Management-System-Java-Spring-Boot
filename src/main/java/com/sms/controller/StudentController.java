package com.sms.controller;

import com.sms.dto.DashboardStatsDto;
import com.sms.exception.DuplicateRecordException;
import com.sms.model.Student;
import com.sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Spring MVC Controller serving Thymeleaf dynamic web pages.
 * Author: Swaraj Surendra Pakhale (Roll No: 172)
 * College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 */
@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        DashboardStatsDto stats = studentService.getDashboardStats();
        model.addAttribute("stats", stats);
        model.addAttribute("activePage", "dashboard");
        return "dashboard";
    }

    @GetMapping("/students")
    public String listStudents(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Student> students = studentService.searchStudents(keyword);
        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        model.addAttribute("activePage", "students");
        return "students";
    }

    @GetMapping("/students/new")
    public String showCreateForm(Model model) {
        Student student = new Student();
        student.setDepartment("Computer Technology");
        student.setSemester("Semester 7");
        model.addAttribute("student", student);
        model.addAttribute("isEdit", false);
        model.addAttribute("activePage", "add_student");
        return "student_form";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            model.addAttribute("activePage", "add_student");
            return "student_form";
        }

        try {
            studentService.createStudent(student);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Student record for '" + student.getFullName() + "' created successfully!");
            return "redirect:/students";
        } catch (DuplicateRecordException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("isEdit", false);
            model.addAttribute("activePage", "add_student");
            return "student_form";
        }
    }

    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("isEdit", true);
        model.addAttribute("activePage", "students");
        return "student_form";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            model.addAttribute("activePage", "students");
            return "student_form";
        }

        try {
            studentService.updateStudent(id, student);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Student record for '" + student.getFullName() + "' updated successfully!");
            return "redirect:/students";
        } catch (DuplicateRecordException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("isEdit", true);
            model.addAttribute("activePage", "students");
            return "student_form";
        }
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Student student = studentService.getStudentById(id);
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Student record for '" + student.getFullName() + "' (" + student.getRollNumber() + ") was deleted successfully.");
        return "redirect:/students";
    }
}
