package com.example.OnlineCourses.dtos;

import lombok.Data;

@Data
public class LessonDTO {

    private Long id;

    private String name;

    public LessonDTO(){}

    public LessonDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
