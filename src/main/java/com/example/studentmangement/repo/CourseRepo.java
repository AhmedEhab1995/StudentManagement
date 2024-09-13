package com.example.studentmangement.repo;

import com.example.studentmangement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);
}
