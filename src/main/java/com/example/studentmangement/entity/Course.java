package com.example.studentmangement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "courses")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "course_schedules", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "schedule", nullable = false)
    private List<String> scheduleSlots = new ArrayList<>();

    public Course(String name, List<String> scheduleSlots) {
        this.name = name;
        this.scheduleSlots = scheduleSlots;
    }

    public Course(Long id) {
        this.id = id;
    }
}
