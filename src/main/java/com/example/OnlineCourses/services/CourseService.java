package com.example.OnlineCourses.services;

import com.example.OnlineCourses.domains.User;
import com.example.OnlineCourses.dtos.CourseDTO;
import com.example.OnlineCourses.dtos.UserDTO;

import java.util.List;

public interface CourseService {

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(Long id);

    void update(CourseDTO courseDTO);

    void deleteCourseById(Long id);

    List<CourseDTO> getCoursesByTeacher(Long teacherId);

    List<CourseDTO> getCoursesByStudent(Long studentId);

    List<UserDTO> getStudentsByCourse(Long courseId);

    void createCourse(CourseDTO courseDTO, User principal);
}
