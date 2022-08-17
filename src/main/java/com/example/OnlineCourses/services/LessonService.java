package com.example.OnlineCourses.services;

import com.example.OnlineCourses.dtos.LessonDTO;

import java.util.List;

public interface LessonService {

    List<LessonDTO> getAllLessonsByCourseId(Long courseId);

    LessonDTO getLessonById(Long id);

    void createLesson(LessonDTO lessonDTO, Long courseId);

    void update(LessonDTO lessonDTO);

    void deleteLessonById(Long id);
}
