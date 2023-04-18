package com.codestates.project.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public enum ExceptionType {

    EXISTS_USER(BAD_REQUEST, "이미 존재하는 아이디 입니다."),
    NOT_FOUND_USER(NOT_FOUND, "유저 정보가 없습니다."),
    NOT_FOUND_BOOK(NOT_FOUND, "도서 정보가 없습니다."),
    EXISTS_LOANED_USER(BAD_REQUEST, "대출 중인 사용자는 삭제할 수 없습니다."),
    EXISTS_LOANED_BOOK(BAD_REQUEST, "대출 중인 도서입니다."),

    NOT_ALLOW_LOAN(BAD_REQUEST, "대출이 불가능한 상태입니다.");

    private final HttpStatus status;
    private final String errorMessage;

    ExceptionType(HttpStatus status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
