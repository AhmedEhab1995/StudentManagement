package com.example.studentmangement.config;

import com.example.studentmangement.repo.StudentRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class StudentDetailService implements UserDetailsService {
    private final StudentRepo studentRepo;

    public StudentDetailService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return studentRepo.findByEmail(email)
                .map(student -> new User(student.getEmail(), student.getPassword(), Collections.emptyList()))
                .orElseThrow(() -> new UsernameNotFoundException("Student with email " + email + " not found!"));
    }
}
