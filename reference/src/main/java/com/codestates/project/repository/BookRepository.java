package com.codestates.project.repository;

import com.codestates.project.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>, DslBookRepository {
}