package com.codestates.project.repository;

import com.codestates.project.dto.response.UserLoanResponse;

import java.util.List;

public interface DslUserLoanHistoryRepository {
    List<UserLoanResponse> findAllUserLoanHistories(Long userId);
}