package com.example.products.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NameAlreadyExistException.class)

    public ResponseEntity<?> nameAlreadyExistHandler(NameAlreadyExistException nameAlreadyExistException) {
        ErrorDetails errorDetails = new ErrorDetails
                (nameAlreadyExistException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}