package com.trust.microservices.controller;

import com.trust.microservices.dto.UserDto;
import com.trust.microservices.entity.User;
import com.trust.microservices.service.UserService;
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

    @GetMapping("/getUserById")
    public User getUser(@RequestParam Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/getUsersById")
    public List<User> getUsers(@RequestParam List<Long> id){
        return userService.getUsersById(id);
    }

    @DeleteMapping("/deleteUser/{userId}/{password}")
    public Boolean deleteUser(@PathVariable Long userId,@PathVariable String password){
            return userService.deleteUser(userId ,password);
    }


}
