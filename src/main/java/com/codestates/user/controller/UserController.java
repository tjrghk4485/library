package com.codestates.user.controller;

import com.codestates.user.entity.User;
import com.codestates.user.mapper.UserMapper;
import com.codestates.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.codestates.user.dto.UserDto;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;


    public UserController(UserService userService, UserMapper mapper){
        this.userService = userService;
        this.mapper  = mapper;
    }
    @PostMapping("/join")
    public ResponseEntity postUser(@Valid @RequestBody UserDto.Post requestBody){
        User createdUser = userService.createUser(mapper.userPostDtoToUser(requestBody));
        return new ResponseEntity<>(mapper.userToUserDtoResponse(createdUser), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }


}
