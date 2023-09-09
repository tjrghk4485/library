package com.codestates.user.entity;

import com.codestates.book.entity.Book;
import com.codestates.exception.CustomException;
import com.codestates.exception.ExceptionType;
import lombok.*;
import com.codestates.baseentity.Library;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String phoneNumber;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "library_id")
    private Library library;
    public User(Long id) {
        this.userId = id;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    public boolean isNotDeletable() {
        return userLoanHistories.stream()
                .anyMatch(userLoanHistory -> userLoanHistory.getStatus() == UserLoanStatus.LOANED);
    }

    public void loanBook(Long bookId) {
        assertValidLoanStatus();
        this.userLoanHistories.add(new UserLoanHistory(userId, bookId, library.getId()));
    }

    private void assertValidLoanStatus() {
        if (isOverLoanCount() || isOverDue()) {
            throw new CustomException(ExceptionType.NOT_ALLOW_LOAN);
        }
    }

    private boolean isOverLoanCount() {
        return userLoanHistories.stream()
                .filter(h -> h.getStatus() == UserLoanStatus.LOANED).count() >= Book.MAX_LOAN_COUNT;
    }

    private boolean isOverDue() {
        return userLoanHistories.stream()
                .anyMatch(h -> h.getStatus() == UserLoanStatus.LOANED
                        && LocalDate.now().minusDays(Book.LOAN_PERIOD).isAfter(h.getLoanDate()));
    }

    public void returnBook(Long bookId) {
        UserLoanHistory userLoanHistory = userLoanHistories.stream()
                .filter(h -> bookId.equals(h.getBook().getId()) && h.getStatus() == UserLoanStatus.LOANED)
                .findFirst()
                .orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_BOOK));
        userLoanHistory.doReturn();
    }
}
