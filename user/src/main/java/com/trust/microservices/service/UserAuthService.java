package com.trust.microservices.service;


import com.trust.microservices.dto.UserDto;
import com.trust.microservices.dto.UserLoginDto;
import com.trust.microservices.dto.UserLoginResponseDto;

public interface UserAuthService {

    UserLoginResponseDto loginUser(UserLoginDto userLoginDto);

    UserLoginResponseDto signUp(UserDto userDto);
}
