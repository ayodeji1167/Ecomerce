package com.example.products.exception;

public class NameAlreadyExistException extends RuntimeException{
    private String message;

    public NameAlreadyExistException(String message){
        super(message);
    }

}
