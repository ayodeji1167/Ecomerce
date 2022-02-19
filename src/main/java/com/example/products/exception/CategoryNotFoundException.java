package com.example.products.exception;

public class CategoryNotFoundException extends RuntimeException{
    private String message;

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
