package com.codestates.project.dto.request;

import com.codestates.project.domain.Gender;
import com.codestates.project.domain.UserInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class UserCreateRequest {

    @NotBlank
    private String name;
    @Positive
    private Integer age;
    private Gender gender;
    @NotBlank
    private String phone;

    @Positive
    private Long libraryId;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public UserInfo toEntity() {
        return new UserInfo(name, age, gender, phone, libraryId);
    }

    public UserCreateRequest(String name, Integer age, Gender gender, String phone, Long libraryId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.libraryId = libraryId;
    }
}