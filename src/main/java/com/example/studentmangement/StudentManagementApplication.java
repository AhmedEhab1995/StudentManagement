package com.example.studentmangement;

import com.example.studentmangement.entity.Course;
import com.example.studentmangement.entity.Student;
import com.example.studentmangement.repo.CourseRepo;
import com.example.studentmangement.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableCaching
@SpringBootApplication
public class StudentManagementApplication implements ApplicationRunner {
    @Autowired
    private StudentRepo repo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        repo.save(Student.builder()
                .email("test@test.com")
                .password(passwordEncoder.encode("hello"))
                .name("test student")
                .build()
        );

        repo.save(Student.builder()
                .email("test2@test.com")
                .password(passwordEncoder.encode("hello"))
                .name("test student2")
                .build()
        );

        courseRepo.save(new Course("Course 1"));
        courseRepo.save(new Course("Course 2"));
    }
}
