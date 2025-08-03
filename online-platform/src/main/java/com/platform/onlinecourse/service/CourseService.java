package com.platform.onlinecourse.service;

import com.platform.onlinecourse.dto.CourseRequest;
import com.platform.onlinecourse.model.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(CourseRequest request);
    List<Course> getAllCourses();
}
