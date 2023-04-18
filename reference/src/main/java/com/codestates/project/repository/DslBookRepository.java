package com.codestates.project.repository;

import com.codestates.project.dto.request.BookSearchType;
import com.codestates.project.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DslBookRepository {
    Page<BookResponse> searchBooks(BookSearchType type, String value, Pageable pageable);
}