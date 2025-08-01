package com.platform.onlinecourse.exception;

public class CourseAlreadyExistException extends ResourceAlreadyExistException{
    public CourseAlreadyExistException(String message) {
        super(message);
    }
}
