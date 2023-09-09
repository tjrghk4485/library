package com.codestates.user.repository;

import com.codestates.book.entity.Book;
import com.codestates.user.entity.UserLoanHistory;
import com.codestates.user.entity.UserLoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    int countByBookAndStatus(Book book, UserLoanStatus status);

    UserLoanHistory findByBookId(Long bookId);
}
