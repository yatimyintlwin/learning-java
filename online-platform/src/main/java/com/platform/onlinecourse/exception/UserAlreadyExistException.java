package com.platform.onlinecourse.exception;

public class UserAlreadyExistException extends ResourceAlreadyExistException{
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
