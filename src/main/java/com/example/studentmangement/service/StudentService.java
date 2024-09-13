package com.example.studentmangement.service;

import com.example.studentmangement.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    void registerToCourse(String courseName, String userEmail);

    void unregisterFromCourse(String courseName, String userEmail);

    List<String> findStudentCourses(String userEmail);

    Optional<Student> findByEmail(String studentEmail);
}
