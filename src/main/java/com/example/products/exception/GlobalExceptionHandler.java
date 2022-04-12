package com.example.products.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

//Name already exist handler
    @ExceptionHandler(NameAlreadyExistException.class)
    public ResponseEntity<?> nameAlreadyExistHandler(NameAlreadyExistException nameAlreadyExistException) {
        ErrorDetails errorDetails = new ErrorDetails
                (nameAlreadyExistException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    //Product not found handler
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> productNotFound (ProductNotFoundException productNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails
                (productNotFoundException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    //Category not found handler
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?>  categoryNotFoundException (CategoryNotFoundException categoryNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails
                (categoryNotFoundException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //Company not found handler
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<?>  companyNotFoundHandler (CompanyNotFoundException companyNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails
                (companyNotFoundException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //AppUser not found handler
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?>  userNotFoundHandler (UserNotFoundException userNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails
                (userNotFoundException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
