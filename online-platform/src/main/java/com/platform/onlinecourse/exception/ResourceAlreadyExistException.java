package com.platform.onlinecourse.exception;

public class ResourceAlreadyExistException extends RuntimeException{
    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
