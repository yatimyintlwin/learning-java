package com.platform.onlinecourse.repository;

import com.platform.onlinecourse.model.Course;

import java.util.List;

public interface CourseRepository {
    Course save(Course course);
    List<Course> findAll();
}
