package com.trust.microservices.serviceimpl;

import com.trust.microservices.dto.UserDto;
import com.trust.microservices.dto.UserLoginDto;
import com.trust.microservices.dto.UserLoginResponseDto;
import com.trust.microservices.entity.User;
import com.trust.microservices.repository.UserRepo;
import com.trust.microservices.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserRepo userRepo;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Override
    public UserLoginResponseDto loginUser(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(),userLoginDto.getPassword()));
        User user = (User) authentication.getPrincipal();

        String token = jwtService.generateAccessToken(user);
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
        userLoginResponseDto.setJwtToken(token);
        return userLoginResponseDto;
    }

    public UserLoginResponseDto signUp(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepo.save(user);
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
        userLoginResponseDto.setJwtToken(jwtService.generateAccessToken(user));
        return userLoginResponseDto;
    }
}
