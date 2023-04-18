package com.codestates.project.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final ExceptionType exceptionType;

    public CustomException(ExceptionType exceptionType) {
        super(exceptionType.getErrorMessage());
        this.exceptionType = exceptionType;
    }

    public HttpStatus getHttpStatus() {
        return exceptionType.getStatus();
    }

    public int getHttpStatusCode() {
        return exceptionType.getStatus().value();
    }
}