package com.example.products.exception;

public class CategoryException extends RuntimeException {
    private String message;

    public CategoryException(String message) {
        super(message);
    }
}
