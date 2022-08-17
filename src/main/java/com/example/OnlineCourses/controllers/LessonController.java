package com.example.OnlineCourses.controllers;

import com.example.OnlineCourses.dtos.LessonDTO;
import com.example.OnlineCourses.services.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/lessons")
@AllArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    // істеп тұр
    @GetMapping
    public ResponseEntity<List<LessonDTO>> getAllLessonsByCourseId(@PathVariable Long courseId){
        return ResponseEntity.ok(lessonService.getAllLessonsByCourseId(courseId));
    }

    // істеп тұр
    @GetMapping("/{id}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable Long id){
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    // істеп тұр
    @PostMapping
    public ResponseEntity createLesson(@RequestBody LessonDTO lessonDTO, @PathVariable Long courseId){
        lessonService.createLesson(lessonDTO, courseId);
        return ResponseEntity.ok("lesson created");
    }

    // істеп тұр
    @PutMapping
    public ResponseEntity updateLesson(@RequestBody LessonDTO lessonDTO){
        lessonService.update(lessonDTO);
        return ResponseEntity.ok("lesson by id : " + lessonDTO.getId() + " was updated");
    }

    // істеп тұр
    @DeleteMapping("/{id}")
    public ResponseEntity deleteLesson(@PathVariable Long id){
        lessonService.deleteLessonById(id);
        return ResponseEntity.ok("lesson by id : " + id + " was deleted");
    }
}
