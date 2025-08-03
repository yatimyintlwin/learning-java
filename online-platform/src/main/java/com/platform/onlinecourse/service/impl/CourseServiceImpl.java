package com.platform.onlinecourse.service.impl;

import com.platform.onlinecourse.dto.CourseRequest;
import com.platform.onlinecourse.exception.CourseAlreadyExistException;
import com.platform.onlinecourse.exception.CourseNotFoundException;
import com.platform.onlinecourse.model.Course;
import com.platform.onlinecourse.repository.CourseRepository;
import com.platform.onlinecourse.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(CourseRequest request) {
        log.info("Attempting to create course with title: {}", request.getTitle());

        if (courseRepository.findByCourseTitle(request.getTitle()) != null) {
            log.warn("Course already exists: {}", request.getTitle());
            throw new CourseAlreadyExistException("Course already exists");
        }

        Course course = new Course();
        course.setId(UUID.randomUUID().toString());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());

        Course savedCourse = courseRepository.save(course);

        log.info("Course created successfully: {} (ID: {})", savedCourse.getTitle(), savedCourse.getId());
        log.debug("Course details: {}", savedCourse);
        return savedCourse;
    }

    @Override
    public List<Course> getAllCourses() {
        log.info("Fetching all courses");
        List<Course> courses = courseRepository.findAll();

        if (courses.isEmpty()) {
            log.warn("No courses available in the repository");
            throw new CourseNotFoundException("No courses available");
        }

        log.debug("Number of courses fetched: {}", courses.size());
        return courses;
    }
}
