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
//        if (courseRepository.findByCourseTitle(request.getTitle()) != null) {
//            log.warn("Failed to create course: title '{}' already exists", request.getTitle());
//            throw new CourseAlreadyExistException("Course already exists");
//        }

        Course course = new Course();
        course.setId(UUID.randomUUID().toString());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());

        Course savedCourse = courseRepository.save(course);

        log.info("Course created successfully: {} (ID: {})", savedCourse.getTitle(), savedCourse.getId());
        return savedCourse;
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
