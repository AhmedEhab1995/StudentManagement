package com.example.studentmangement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

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

    public Course(String name) {
        this.name = name;
    }

    public Course(Long id) {
        this.id = id;
    }
}
