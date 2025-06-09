package com.handler.exception.exception;

public class InvalidResourceException extends RuntimeException{
    public InvalidResourceException(String message) {
        super(message);
    }
}
