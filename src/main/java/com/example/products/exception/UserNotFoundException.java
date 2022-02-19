package com.example.products.exception;

public class UserNotFoundException extends RuntimeException {
    private String message;

    public UserNotFoundException(String message){
        super(message);
    }
}
