package com.example.OnlineCourses.domains;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User studentId;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}
