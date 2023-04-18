package com.codestates.project.domain;

import com.codestates.project.domain.baseentity.BaseTimeEntity;
import com.codestates.project.exception.CustomException;
import com.codestates.project.exception.ExceptionType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "userUnique",
                        columnNames = {"library_id", "name", "phone"}
                )
        }
)
public class UserInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "library_id")
    private Library library;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    public UserInfo() {
    }

    public UserInfo(Long id) {
        this.id = id;
    }

    public UserInfo(String name, Integer age, Gender gender, String phone, Long libraryId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.library = new Library(libraryId);
    }

    public UserInfo(String name, Integer age, Gender gender, String phone, Long libraryId, List<UserLoanHistory> userLoanHistories) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.library = new Library(libraryId);
        this.userLoanHistories = userLoanHistories;
    }

    public Long getId() {
        return id;
    }

    public List<UserLoanHistory> getUserLoanHistories() {
        return Collections.unmodifiableList(userLoanHistories);
    }

    public boolean isNotDeletable() {
        return userLoanHistories.stream()
                .anyMatch(userLoanHistory -> userLoanHistory.getStatus() == UserLoanStatus.LOANED);
    }

    public void loanBook(Long bookId) {
        assertValidLoanStatus();
        this.userLoanHistories.add(new UserLoanHistory(id, bookId, library.getId()));
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