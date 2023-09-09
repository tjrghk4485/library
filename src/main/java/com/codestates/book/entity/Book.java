package com.codestates.book.entity;

import com.codestates.baseentity.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class Book extends BaseTimeEntity {

    public static int LOAN_PERIOD = 14;
    public static int MAX_LOAN_COUNT = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    private String author;

    private String publisher;

    public Book() {
    }

    public Book(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

