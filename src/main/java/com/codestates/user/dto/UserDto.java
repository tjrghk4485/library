package com.codestates.user.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
public class UserDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {
        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        private String name;
        @NotBlank
        private String phoneNumber;


        }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
        public static class Response {
        private String name;
        private String phoneNumber;
        }
    }

