package com.codestates.project.service;

import com.codestates.project.domain.Library;
import com.codestates.project.domain.UserInfo;
import com.codestates.project.dto.request.UserCreateRequest;
import com.codestates.project.dto.response.UserLoanResponse;
import com.codestates.project.exception.CustomException;
import com.codestates.project.exception.ExceptionType;
import com.codestates.project.repository.UserLoanHistoryRepository;
import com.codestates.project.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;

    public UserService(UserRepository userRepository, UserLoanHistoryRepository userLoanHistoryRepository) {
        this.userRepository = userRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
    }

    public List<UserLoanResponse> getUserLoanHistories(Long userId) {
        return userLoanHistoryRepository.findAllUserLoanHistories(userId);
    }

    public Long saveUser(UserCreateRequest request) {
        Optional<UserInfo> user = userRepository.findByLibraryAndNameAndPhone(
                new Library(request.getLibraryId()),
                request.getName(),
                request.getPhone()
        );
        if (user.isPresent()) {
            throw new CustomException(ExceptionType.EXISTS_USER);
        }

        return userRepository.save(request.toEntity()).getId();
    }

    public void deleteUser(Long userId) {
        UserInfo user = userRepository.findByUserId(userId);
        if (user.isNotDeletable()) {
            throw new CustomException(ExceptionType.EXISTS_LOANED_USER);
        }
        userRepository.delete(user);
    }
}