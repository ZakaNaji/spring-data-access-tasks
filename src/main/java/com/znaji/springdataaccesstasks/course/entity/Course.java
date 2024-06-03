package com.znaji.springdataaccesstasks.course.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITLE", nullable = false, length = 100) // "title" column is not nullable and has a length of 100
    private String title;
    @Column(name = "BEGIN_DATE") // "BEGIN_DATE" column is created in the database
    private LocalDate beginDate;
    @Column(name = "END_DATE") // "END_DATE" column is created in the database
    private LocalDate endDate;
    @Column(name = "FEE") // "FEE" column is created in the database
    private int fee;
}
