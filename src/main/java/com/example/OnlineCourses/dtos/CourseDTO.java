package com.example.OnlineCourses.dtos;

import lombok.Data;

@Data
public class CourseDTO {

    private Long id;
    private String name;
    private String description;

    public CourseDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
