package com.example.studentmangement.service;

import com.example.studentmangement.entity.Course;
import com.example.studentmangement.repo.CourseRepo;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }


    @Override
    public List<String> findAll() {
        return courseRepo.findAll()
                .stream()
                .map(Course::getName)
                .toList();
    }

    @Override
    public void save(String courseName) {
        courseRepo.save(new Course(courseName));
    }

    @Override
    public Optional<Course> findByName(String courseName) {
        return courseRepo.findByName(courseName);
    }
}
