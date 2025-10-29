package com.felfel.springsecurity.controller;

import com.felfel.springsecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    ArrayList<Student> students = new  ArrayList<>();

    {
        students.add(new Student(1, "felfel", "java"));
        students.add(new Student(2, "ahmed", "python"));
        students.add(new Student(3, "mohamed", "javascript"));
    }
    @GetMapping("students")
    public List<Student> getAllStudents()
    {
        return students;
    }

    @GetMapping("csrf-token" )
    public CsrfToken getCSRFToken(HttpServletRequest http) {
        return (CsrfToken) http.getAttribute("_csrf");
    }
    @PostMapping("students" )
    public Student createStudent(@RequestBody Student student) {
        students.add(student);

        return (Student) students
                .stream()
                .filter(s ->
                        s.getId() == student.getId())
                .findFirst()
                .orElse(null);
    }
}
