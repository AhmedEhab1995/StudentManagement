package com.example.studentmangement.repo;

import com.example.studentmangement.entity.Course;
import com.example.studentmangement.entity.Student;
import com.example.studentmangement.entity.StudentCourse;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepo extends JpaRepository<StudentCourse, Long> {

    @Query("SELECT sc.course FROM StudentCourse sc WHERE sc.student.email = :email")
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    List<Course> findByStudentEmail(@Param("email") String email);

    @Query("SELECT sc.student FROM StudentCourse sc WHERE sc.course.name = :name")
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    List<Student> findByCourseName(@Param("name") String name);
}
