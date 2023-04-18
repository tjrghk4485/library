package com.codestates.project.repository.impl;

import com.codestates.project.dto.request.BookSearchType;
import com.codestates.project.dto.response.BookResponse;
import com.codestates.project.dto.response.QBookResponse;
import com.codestates.project.repository.DslBookRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static com.codestates.project.domain.QBook.book;

public class DslBookRepositoryImpl implements DslBookRepository {

    private final JPAQueryFactory queryFactory;

    public DslBookRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<BookResponse> searchBooks(BookSearchType type, String value, Pageable pageable) {
        var result = queryFactory.select(
                        new QBookResponse(
                                book.title,
                                book.category,
                                book.author,
                                book.publisher
                        )
                )
                .from(book)
                .where(type.getCondition(value))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(result, pageable, getTotalCount(type, value));
    }

    private Long getTotalCount(BookSearchType type, String value) {
        return queryFactory.select(book.id.count())
                .from(book)
                .where(type.getCondition(value))
                .fetchOne();
    }
}