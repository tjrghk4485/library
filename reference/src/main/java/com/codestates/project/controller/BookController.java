package com.codestates.project.controller;

import com.codestates.project.dto.request.BookLoanRequest;
import com.codestates.project.dto.request.BookReturnRequest;
import com.codestates.project.dto.request.BookSearchRequest;
import com.codestates.project.dto.response.BookResponse;
import com.codestates.project.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(name = "도서 목록 조회")
    public ResponseEntity<Page<BookResponse>> searchBooks(BookSearchRequest request, Pageable pageable) {
        return ResponseEntity.ok(bookService.searchBooks(request, pageable));
    }

    @PostMapping(path = "/loan", name = "도서 대출")
    public ResponseEntity<Void> loanBook(@RequestBody @Valid BookLoanRequest request) {
        bookService.loanBook(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/return", name = "도서 반납")
    public ResponseEntity<Void> returnBook(@RequestBody @Valid BookReturnRequest request) {
        bookService.returnBook(request);
        return ResponseEntity.ok().build();
    }
}