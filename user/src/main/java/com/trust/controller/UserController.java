package com.trust.controller;

import com.trust.dto.UserDto;
import com.trust.model.User;
import com.trust.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public String createUser(UserDto userDto){

        return userService.createUser(userDto);

    }
}
