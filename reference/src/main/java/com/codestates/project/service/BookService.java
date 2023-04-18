package com.codestates.project.service;

import com.codestates.project.domain.Book;
import com.codestates.project.domain.UserInfo;
import com.codestates.project.dto.request.BookLoanRequest;
import com.codestates.project.dto.request.BookReturnRequest;
import com.codestates.project.dto.request.BookSearchRequest;
import com.codestates.project.dto.response.BookResponse;
import com.codestates.project.exception.CustomException;
import com.codestates.project.repository.UserLoanHistoryRepository;
import com.codestates.project.repository.BookRepository;
import com.codestates.project.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.project.domain.UserLoanStatus.LOANED;
import static com.codestates.project.exception.ExceptionType.EXISTS_LOANED_BOOK;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository, UserLoanHistoryRepository userLoanHistoryRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> searchBooks(BookSearchRequest request, Pageable pageable) {
        return bookRepository.searchBooks(request.getSearchType(), request.getSearchValue(), pageable);
    }

    public void loanBook(BookLoanRequest request) {
        int loanedCount = userLoanHistoryRepository.countByBookAndStatus(new Book(request.getBookId()), LOANED);
        if (loanedCount > 0) {
            throw new CustomException(EXISTS_LOANED_BOOK);
        }

        UserInfo user = userRepository.findByUserId(request.getUserId());
        user.loanBook(request.getBookId());
    }

    public void returnBook(BookReturnRequest request) {
        UserInfo user = userRepository.findByUserId(request.getUserId());
        user.returnBook(request.getBookId());
    }
}