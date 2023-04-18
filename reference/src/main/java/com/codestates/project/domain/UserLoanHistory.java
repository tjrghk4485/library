package com.codestates.project.domain;

import com.codestates.project.domain.baseentity.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDate;

import static com.codestates.project.domain.UserLoanStatus.LOANED;
import static com.codestates.project.domain.UserLoanStatus.RETURNED;

@Entity
public class UserLoanHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserLoanStatus status;

    private LocalDate loanDate;

    private LocalDate returnedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "library_id")
    private Library library;

    public UserLoanHistory() {
    }

    public UserLoanHistory(Long userId, Long bookId, Long libraryId) {
        this.user = new UserInfo(userId);
        this.book = new Book(bookId);
        this.library = new Library(libraryId);
        this.status = LOANED;
        this.loanDate = LocalDate.now();
    }

    public UserLoanHistory(UserLoanStatus status, LocalDate loanDate, LocalDate returnedDate, Long userId, Long bookId, Long libraryId) {
        this.status = status;
        this.loanDate = loanDate;
        this.returnedDate = returnedDate;
        this.user = new UserInfo(userId);
        this.book = new Book(bookId);
        this.library = new Library(libraryId);
    }

    public UserLoanStatus getStatus() {
        return status;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void doReturn() {
        this.status = RETURNED;
        this.returnedDate = LocalDate.now();
    }
}
