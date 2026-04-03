package com.trust.controller;

import com.trust.dto.UserDto;
import com.trust.model.User;
import com.trust.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public String createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);

    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }
}
