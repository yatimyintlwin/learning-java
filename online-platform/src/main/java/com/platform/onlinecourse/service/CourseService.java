package com.platform.onlinecourse.service;

import com.platform.onlinecourse.model.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    List<Course> getAllCourses();
}
