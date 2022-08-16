package com.example.OnlineCourses.services.impl;

import com.example.OnlineCourses.domains.Course;
import com.example.OnlineCourses.domains.User;
import com.example.OnlineCourses.dtos.CourseDTO;
import com.example.OnlineCourses.dtos.UserDTO;
import com.example.OnlineCourses.exceptions.RestAPIException;
import com.example.OnlineCourses.repositories.CourseRepository;
import com.example.OnlineCourses.services.CourseService;
import com.example.OnlineCourses.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;

    @Override
    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> courses = courseRepository.findAll().stream()
                .map((c) -> new CourseDTO(c.getId(), c.getName(), c.getDescription()))
                .collect(Collectors.toList());
        return courses;
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course c = courseRepository.findById(id)
                .orElseThrow(() -> new RestAPIException("course with id " + id + " not found"));
        return new CourseDTO(c.getId(), c.getName(), c.getDescription());
    }

    @Override
    public void update(CourseDTO courseDTO) {
        Course item = courseRepository.findById(courseDTO.getId())
                .orElseThrow(() -> new RestAPIException("course with id " + courseDTO.getId() + " not found"));
        if (courseDTO.getName() != null){
            item.setName(courseDTO.getName());
        }
        if (courseDTO.getDescription() != null){
            item.setDescription(courseDTO.getDescription());
        }
        courseRepository.saveAndFlush(item);
    }

    @Override
    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RestAPIException("course with id " + id + " not found");
        }
        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseDTO> getCoursesByTeacher(Long teacherId) {
        return userService.getTeacherCourses(teacherId);
    }

    @Override
    public List<CourseDTO> getCoursesByStudent(Long studentId) {
        return userService.getStudentCourses(studentId);
    }

    @Override
    public List<UserDTO> getStudentsByCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RestAPIException("course with id " + courseId + " not found"))
                .getStudents().stream()
                .map((s) -> new UserDTO(
                        s.getId(),
                        s.getLogin(),
                        s.getUDetails().getName(),
                        s.getUDetails().getSurname(),
                        s.getUDetails().getEmail(),
                        s.getUDetails().getPhoneNumber())
                ).collect(Collectors.toList());
    }

    @Override
    public void createCourse(CourseDTO courseDTO, User teacher) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setTeacher(teacher);
        courseRepository.save(course);
    }
}
