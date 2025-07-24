package com.platform.onlinecourse.service.impl;

import com.platform.onlinecourse.exception.CourseNotFoundException;
import com.platform.onlinecourse.exception.InvalidCredentialsException;
import com.platform.onlinecourse.model.Course;
import com.platform.onlinecourse.repository.CourseRepository;
import com.platform.onlinecourse.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        if(courseRepository.findByCourseTitle(course.getTitle()) != null) {
            throw new InvalidCredentialsException("Course already exists");
        }

        course.setId(UUID.randomUUID().toString());
        course.setTitle(course.getTitle());
        course.setDescription(course.getDescription());
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new CourseNotFoundException("No courses available");
        }
        return courses;
    }
}
