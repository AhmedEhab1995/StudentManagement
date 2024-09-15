package com.example.studentmangement.service;


import com.example.studentmangement.entity.Course;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();

    Optional<Course> findByName(String courseName);

    public byte[] getCourseSchedulePdf(String courseName) throws DocumentException, FileNotFoundException;

    }
