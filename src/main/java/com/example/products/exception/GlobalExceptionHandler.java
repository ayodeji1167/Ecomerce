package com.example.products.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppUserException.class)
    public ResponseEntity<?> nameAlreadyExistHandler(AppUserException appUserException) {
        ErrorDetails errorDetails = new ErrorDetails
                (appUserException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ProductException.class)
    public ResponseEntity<?> productNotFound(ProductException productException) {
        ErrorDetails errorDetails = new ErrorDetails
                (productException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<?> categoryNotFoundException(CategoryException categoryException) {
        ErrorDetails errorDetails = new ErrorDetails
                (categoryException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now());


        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CompanyException.class)
    public ResponseEntity<?> companyNotFoundHandler(CompanyException companyException) {
        ErrorDetails errorDetails = new ErrorDetails
                (companyException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
