package com.codestates.project.repository;

import com.codestates.project.domain.Book;
import com.codestates.project.domain.UserLoanHistory;
import com.codestates.project.domain.UserLoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long>, DslUserLoanHistoryRepository {

    int countByBookAndStatus(Book book, UserLoanStatus status);
}