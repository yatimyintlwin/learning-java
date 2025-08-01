package com.platform.onlinecourse.exception;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
