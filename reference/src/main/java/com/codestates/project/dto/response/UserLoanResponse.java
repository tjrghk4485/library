package com.codestates.project.dto.response;

import com.codestates.project.domain.UserLoanStatus;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDate;

public class UserLoanResponse {

    private String title;
    private UserLoanStatus status;
    private LocalDate loanDate;
    private LocalDate returnedDate;

    @QueryProjection
    public UserLoanResponse(String title, UserLoanStatus status, LocalDate loanDate, LocalDate returnedDate) {
        this.title = title;
        this.status = status;
        this.loanDate = loanDate;
        this.returnedDate = returnedDate;
    }

    public String getTitle() {
        return title;
    }

    public UserLoanStatus getStatus() {
        return status;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }
}