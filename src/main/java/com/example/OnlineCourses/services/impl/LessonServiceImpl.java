package com.example.OnlineCourses.services.impl;

import com.example.OnlineCourses.domains.Lesson;
import com.example.OnlineCourses.dtos.LessonDTO;;
import com.example.OnlineCourses.exceptions.RestAPIException;
import com.example.OnlineCourses.repositories.CourseRepository;
import com.example.OnlineCourses.repositories.LessonRepository;
import com.example.OnlineCourses.services.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<LessonDTO> getAllLessonsByCourseId(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RestAPIException("course with id " + courseId + " not found"))
                .getLessons().stream()
                .map((l) -> new LessonDTO(
                        l.getId(),
                        l.getName()
                )).collect(Collectors.toList());
    }

    @Override
    public LessonDTO getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RestAPIException("lesson with id " + id + " not found"));
        return new LessonDTO(lesson.getId(), lesson.getName());
    }

    @Override
    public void createLesson(LessonDTO lessonDTO, Long courseId) {
        Lesson lesson = new Lesson();
        lesson.setName(lessonDTO.getName());
        lesson.setCourse(courseRepository.findById(courseId)
                .orElseThrow(() -> new RestAPIException("course with id " + courseId + " not found")));
        lessonRepository.save(lesson);
    }

    @Override
    public void update(LessonDTO lessonDTO) {
        Lesson item = lessonRepository.findById(lessonDTO.getId())
                .orElseThrow(() -> new RestAPIException("lesson with id " + lessonDTO.getId() + " not found"));
        if (lessonDTO.getName() != null){
            item.setName(lessonDTO.getName());
        }
        lessonRepository.saveAndFlush(item);
    }

    @Override
    public void deleteLessonById(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new RestAPIException("lesson with id " + id + " not found");
        }
        lessonRepository.deleteById(id);
    }
}
