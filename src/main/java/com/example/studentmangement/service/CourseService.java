package com.example.studentmangement.service;


import com.example.studentmangement.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<String> findAll();

    void save(String courseName);

    Optional<Course> findByName(String courseName);
}
