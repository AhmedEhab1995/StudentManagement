package com.example.studentmangement.service;

import com.example.studentmangement.entity.Course;
import com.example.studentmangement.entity.Student;
import com.example.studentmangement.entity.StudentCourse;
import com.example.studentmangement.repo.StudentCourseRepo;
import com.example.studentmangement.repo.StudentRepo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final CourseService courseService;
    private final StudentCourseRepo studentCourseRepo;

    public StudentServiceImpl(StudentRepo studentRepo, CourseService courseService, StudentCourseRepo studentCourseRepo) {
        this.studentRepo = studentRepo;
        this.courseService = courseService;
        this.studentCourseRepo = studentCourseRepo;
    }

    @Override
    public void registerToCourse(String courseName, String userEmail) {
        Long courseId = courseService.findByName(courseName)
                .map(Course::getId)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));
        Long studentId = studentRepo.findByEmail(userEmail)
                .map(Student::getId)
                .orElseThrow(() -> new NoSuchElementException("Student not found"));

        studentCourseRepo.save(new StudentCourse(studentId, courseId));
    }

    @Override
    public void unregisterFromCourse(String courseName, String userEmail) {
        Long courseId = courseService.findByName(courseName)
                .map(Course::getId)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));
        Long studentId = studentRepo.findByEmail(userEmail)
                .map(Student::getId)
                .orElseThrow(() -> new NoSuchElementException("Student not found"));

        studentCourseRepo.delete(new StudentCourse(studentId, courseId));
    }

    @Override
    public List<String> findStudentCourses(String userEmail) {
        return studentCourseRepo.findByStudentEmail(userEmail)
                .stream()
                .map(Course::getName)
                .toList();
    }

    @Override
    @Cacheable(value = "StudentCache", unless = "#result == null", key = "{#studentEmail}")
    public Optional<Student> findByEmail(String studentEmail) {
        return studentRepo.findByEmail(studentEmail);
    }


}
