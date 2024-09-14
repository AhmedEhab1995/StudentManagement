package com.example.studentmangement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "courses")
@Cacheable
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
