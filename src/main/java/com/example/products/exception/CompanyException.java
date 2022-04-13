package com.example.products.exception;

public class CompanyException extends RuntimeException {
    private String message;

    public CompanyException(String message) {
        super(message);
    }
}
