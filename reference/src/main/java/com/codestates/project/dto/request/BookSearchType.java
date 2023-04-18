package com.codestates.project.dto.request;

import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.function.Function;

import static com.codestates.project.domain.QBook.book;

public enum BookSearchType {
    TITLE(book.title::contains),
    AUTHOR(book.author::contains),
    PUBLISHER(book.publisher::contains);

    private final Function<String, BooleanExpression> expression;

    BookSearchType(Function<String, BooleanExpression> expression) {
        this.expression = expression;
    }

    public BooleanExpression getCondition(String value) {
        return expression.apply(value);
    }
}