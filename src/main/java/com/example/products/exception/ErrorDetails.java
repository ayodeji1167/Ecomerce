package com.example.products.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public class ErrorDetails {
    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime localDateTime;

    public ErrorDetails(String message, HttpStatus httpStatus, LocalDateTime localDateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getZonedDateTime() {
        return localDateTime;
    }
}
