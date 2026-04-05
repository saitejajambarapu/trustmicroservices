package com.trust.service;

import com.trust.dto.UserDto;
import com.trust.model.User;

import java.util.List;

public interface UserService {

    String createUser(UserDto user);

    List<User> getAllUsers();

    User getUserById(Long id);
}
