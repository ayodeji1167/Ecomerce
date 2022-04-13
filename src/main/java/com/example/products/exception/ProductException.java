package com.example.products.exception;

public class ProductException extends RuntimeException {
    private String message;

    public ProductException(String message) {
        super(message);
    }
}
