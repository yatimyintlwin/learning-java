package com.handler.exception.exception;

public class CustomerNotFoundException extends ResourceNotFoundException{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
