package com.codestates.user.service;

import com.codestates.exception.CustomException;
import com.codestates.exception.ExceptionType;
import com.codestates.user.entity.User;
import com.codestates.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user){
    return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user.isNotDeletable()) {
            throw new CustomException(ExceptionType.EXISTS_LOANED_USER);
        }
        userRepository.delete(user);
    }
}
