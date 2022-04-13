package com.example.products.exception;

public class AppUserException extends RuntimeException {
    private String message;

    public AppUserException(String message) {
        super(message);
    }

}
