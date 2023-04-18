package com.codestates.project.repository;

import com.codestates.project.domain.Library;
import com.codestates.project.domain.UserInfo;
import com.codestates.project.exception.CustomException;
import com.codestates.project.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByLibraryAndNameAndPhone(Library library, String name, String phone);

    default UserInfo findByUserId(Long userId) {
        return this.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_USER));
    }
}