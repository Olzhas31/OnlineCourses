package com.example.OnlineCourses.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/lessons/{lessonId}/comments")
@AllArgsConstructor
public class CommentController {

}
