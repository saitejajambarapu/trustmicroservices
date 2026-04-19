package com.trust.microservices.controller;

import com.trust.microservices.dto.UserDto;
import com.trust.microservices.dto.UserLoginDto;
import com.trust.microservices.dto.UserLoginResponseDto;
import com.trust.microservices.entity.User;
import com.trust.microservices.service.UserAuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;



    @PostMapping("/login")
    public String loginUser(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request, HttpServletResponse response){

        UserLoginResponseDto userLoginResponseDto =  userAuthService.loginUser(userLoginDto);

        Cookie cookie = new Cookie("jwtToken",userLoginResponseDto.getJwtToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return "Logged in successfullly !";
    }

    @PostMapping("/signup")
    public String signUpUser(@RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response){

        UserLoginResponseDto userLoginResponseDto =  userAuthService.signUp(userDto);

        Cookie cookie = new Cookie("jwtToken",userLoginResponseDto.getJwtToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "Sign Up successfullly !";
    }



}
