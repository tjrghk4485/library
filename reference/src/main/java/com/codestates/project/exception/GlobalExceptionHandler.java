package com.codestates.project.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getHttpStatusCode());
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleRuntimeException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}