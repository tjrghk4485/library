package com.codestates.book.service;

import com.codestates.book.dto.BookDto;
import com.codestates.book.entity.Book;
import com.codestates.book.repository.BookRepository;
import com.codestates.exception.CustomException;
import com.codestates.user.entity.User;
import com.codestates.user.entity.UserLoanHistory;
import com.codestates.user.repository.UserLoanHistoryRepository;
import com.codestates.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import static com.codestates.exception.ExceptionType.EXISTS_LOANED_BOOK;
import static com.codestates.user.entity.UserLoanStatus.LOANED;
import static com.codestates.user.entity.UserLoanStatus.RETURNED;

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

    public Page<Book> searchBooks(String query, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"createdDate"));
        Page<Book> booksPage = bookRepository.findByTitleContainingIgnoreCase(query, pageable);
        return booksPage;
    }

    public void loanBook(BookDto.Post requestBody) {
        int loanedCount = userLoanHistoryRepository.countByBookAndStatus(new Book(requestBody.getBookId()), LOANED);
        if (loanedCount > 0) {
            throw new CustomException(EXISTS_LOANED_BOOK);
        }
        User user = userRepository.findByUserId(requestBody.getUserId());
        user.loanBook(requestBody.getBookId());
    }

    public void returnBook(BookDto.Post requestBody) {
       User user = userRepository.findByUserId(requestBody.getUserId());
       user.returnBook(requestBody.getBookId());
    }
}
