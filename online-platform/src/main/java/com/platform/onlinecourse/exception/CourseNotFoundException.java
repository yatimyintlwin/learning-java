package com.platform.onlinecourse.exception;

public class CourseNotFoundException extends ResourceNotFoundException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
