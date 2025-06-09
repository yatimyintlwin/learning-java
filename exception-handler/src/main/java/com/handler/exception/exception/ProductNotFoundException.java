package com.handler.exception.exception;

public class ProductNotFoundException extends ResourceNotFoundException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
