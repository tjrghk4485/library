package com.codestates.user.entity;


import com.codestates.baseentity.Library;
import com.codestates.baseentity.BaseTimeEntity;
import com.codestates.book.entity.Book;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static com.codestates.user.entity.UserLoanStatus.LOANED;
import static com.codestates.user.entity.UserLoanStatus.RETURNED;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
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
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "library_id")
    private Library library;


    public UserLoanHistory(Long userId, Long bookId, Long libraryId) {
        this.user = new User(userId);
        this.book = new Book(bookId);
        this.library = new Library(libraryId);
        this.status = LOANED;
        this.loanDate = LocalDate.now();
    }

    public UserLoanHistory(UserLoanStatus status, LocalDate loanDate, LocalDate returnedDate, Long userId, Long bookId, Long libraryId) {
        this.status = status;
        this.loanDate = loanDate;
        this.returnedDate = returnedDate;
        this.user = new User(userId);
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
