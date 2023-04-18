package com.codestates.project.repository.impl;

import com.codestates.project.dto.response.QUserLoanResponse;
import com.codestates.project.dto.response.UserLoanResponse;
import com.codestates.project.repository.DslUserLoanHistoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.codestates.project.domain.QBook.book;
import static com.codestates.project.domain.QUserLoanHistory.userLoanHistory;

public class DslUserLoanHistoryRepositoryImpl implements DslUserLoanHistoryRepository {

    private final JPAQueryFactory queryFactory;

    public DslUserLoanHistoryRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<UserLoanResponse> findAllUserLoanHistories(Long userId) {
        return queryFactory.select(
                        new QUserLoanResponse(
                                book.title,
                                userLoanHistory.status,
                                userLoanHistory.loanDate,
                                userLoanHistory.returnedDate
                        )
                )
                .from(userLoanHistory)
                .leftJoin(userLoanHistory.book, book)
                .where(userLoanHistory.user.id.eq(userId))
                .fetch();
    }
}
