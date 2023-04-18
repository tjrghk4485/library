package com.codestates.project.dto.response;

import com.codestates.project.domain.BookCategory;
import com.querydsl.core.annotations.QueryProjection;

public class BookResponse {

    private String title;

    private BookCategory category;
    private String author;
    private String publisher;

    @QueryProjection
    public BookResponse(String title, BookCategory category, String author, String publisher) {
        this.title = title;
        this.category = category;
        this.author = author;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public BookCategory getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }
}
