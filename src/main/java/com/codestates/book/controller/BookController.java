package com.codestates.book.controller;


import com.codestates.book.dto.BookDto;
import com.codestates.book.entity.Book;
import com.codestates.book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search/title")
    public ResponseEntity searchTitleBooks(@RequestParam("query") String query,
                                      @Positive @RequestParam(defaultValue = "1") int page,
                                      @Positive @RequestParam(defaultValue = "10") int size){
        Page<Book> pageBook = bookService.searchBooks(query,page -1,size);
        return new ResponseEntity(pageBook, HttpStatus.OK);
    }
    @GetMapping("/search/author")
    public ResponseEntity searchAuthorBooks(@RequestParam("query") String query,
                                      @Positive @RequestParam(defaultValue = "1") int page,
                                      @Positive @RequestParam(defaultValue = "10") int size){
        Page<Book> pageBook = bookService.searchBooks(query,page -1,size);
        return new ResponseEntity(pageBook, HttpStatus.OK);
    }
    @GetMapping("/search/publisher")
    public ResponseEntity searchPublisherBooks(@RequestParam("query") String query,
                                            @Positive @RequestParam(defaultValue = "1") int page,
                                            @Positive @RequestParam(defaultValue = "10") int size){
        Page<Book> pageBook = bookService.searchBooks(query,page -1,size);
        return new ResponseEntity(pageBook, HttpStatus.OK);
    }

    @PostMapping("/loan")
    public ResponseEntity loanBook(@RequestBody @Valid BookDto.Post requestBody){
    bookService.loanBook(requestBody);
    return ResponseEntity.ok().build();
    }

    @PostMapping("/return")
    public ResponseEntity returnBook(@RequestBody @Valid BookDto.Post requestBody){
        bookService.returnBook(requestBody);
        return ResponseEntity.ok().build();
    }
}


