package com.codestates.project.controller;

import com.codestates.project.dto.request.UserCreateRequest;
import com.codestates.project.dto.response.UserLoanResponse;
import com.codestates.project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userId}/loan", name = "사용자 대출 히스토리 조회")
    public ResponseEntity<List<UserLoanResponse>> getUserLoan(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserLoanHistories(userId));
    }

    @PostMapping(name = "사용자 등록")
    public ResponseEntity<Void> saveUser(@RequestBody @Valid UserCreateRequest request) {
        Long saveId = userService.saveUser(request);
        return ResponseEntity.created(URI.create("/users/" + saveId)).build();
    }

    @DeleteMapping(path = "/{userId}", name = "사용자 삭제")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}