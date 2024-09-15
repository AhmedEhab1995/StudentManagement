package com.example.studentmangement.service;

import com.example.studentmangement.entity.Course;
import com.example.studentmangement.exception.CourseNotFoundException;
import com.example.studentmangement.repo.CourseRepo;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;
    private final PDFGeneratorService pdfService;

    public CourseServiceImpl(CourseRepo courseRepo, PDFGeneratorService pdfService) {
        this.courseRepo = courseRepo;
        this.pdfService = pdfService;
    }


    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public Optional<Course> findByName(String courseName) {
        Course course = courseRepo.findByName(courseName)
                .orElseThrow(() -> new CourseNotFoundException("Course: " + courseName +" not found."));
        return Optional.ofNullable(course);
    }
    @Override
    public byte[] getCourseSchedulePdf(String courseName) throws DocumentException, FileNotFoundException {
        Course course = courseRepo.findByName(courseName)
                .orElseThrow(() -> new CourseNotFoundException("Course: " + courseName +" not found."));

        List<String> scheduleSlots = course.getScheduleSlots();
        return pdfService.exportToPdf(courseName, scheduleSlots, "export.pdf");
    }

}
