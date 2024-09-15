package com.example.studentmangement.controller;

import com.example.studentmangement.entity.Course;
import com.example.studentmangement.exception.CourseNotFoundException;
import com.example.studentmangement.service.CourseService;
import com.example.studentmangement.service.PDFGeneratorService;
import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    private final PDFGeneratorService pdfService;

    public CourseController(CourseService courseService, PDFGeneratorService pdfService) {
        this.courseService = courseService;
        this.pdfService = pdfService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }
    @GetMapping("{name}")
    public ResponseEntity getCourseByCourseName(@PathVariable String name) {
        try {
            byte[] pdfContents = courseService.getCourseSchedulePdf(name);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "export.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
        }  catch (CourseNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

}
