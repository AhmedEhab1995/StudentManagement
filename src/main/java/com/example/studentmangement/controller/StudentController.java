package com.example.studentmangement.controller;

import com.example.studentmangement.dto.CourseDTO;
import com.example.studentmangement.service.StudentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public void registerToCourse(@RequestBody CourseDTO course) {
        studentService.registerToCourse(course.getName(), SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @DeleteMapping
    public void unregisterFromCourse(@RequestBody CourseDTO course) {
        studentService.unregisterFromCourse(course.getName(), SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @GetMapping
    public List<String> findStudentCourses() {
        return studentService.findStudentCourses(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
