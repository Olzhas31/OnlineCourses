package com.example.OnlineCourses.repositories;

import com.example.OnlineCourses.domains.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
