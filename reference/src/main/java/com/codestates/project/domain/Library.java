package com.codestates.project.domain;

import com.codestates.project.domain.baseentity.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class Library extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_id")
    private Long id;

    private String name;

    private String openingDate;

    private String closedDate;

    public Library() {
    }

    public Library(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}