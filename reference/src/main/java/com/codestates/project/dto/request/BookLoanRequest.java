package com.codestates.project.dto.request;

public class BookLoanRequest {

    private Long userId;
    private Long bookId;

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }
}
