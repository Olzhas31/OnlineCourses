package com.example.OnlineCourses.controllers;

import com.example.OnlineCourses.domains.User;
import com.example.OnlineCourses.dtos.CourseDTO;
import com.example.OnlineCourses.dtos.UserDTO;
import com.example.OnlineCourses.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // істеп тұр
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // істеп тұр
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CourseDTO>> getCoursesByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(courseService.getCoursesByTeacher(teacherId));
    }

    // істеп тұр
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CourseDTO>> getCoursesByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(courseService.getCoursesByStudent(studentId));
    }

    // істеп тұр
    @GetMapping("/{id}/students")
    public ResponseEntity<List<UserDTO>> getStudentsByCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getStudentsByCourse(id));
    }

    // істеп тұр
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    // істеп тұр
    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseDTO courseDTO, Authentication authentication){
        courseService.createCourse(courseDTO, (User) authentication.getPrincipal());
        return ResponseEntity.ok("course created");
    }

    // істеп тұр
    @PutMapping
    public ResponseEntity updateCourse(@RequestBody CourseDTO courseDTO){
        courseService.update(courseDTO);
        return ResponseEntity.ok("course by id : " + courseDTO.getId() + " was updated");
    }

    // істеп тұр
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id){
        courseService.deleteCourseById(id);
        return ResponseEntity.ok("course by id : " + id + " was deleted");
    }
}
