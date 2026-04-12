package com.trust.microservices.service;

import com.trust.microservices.dto.UserDto;
import com.trust.microservices.model.User;

import java.util.List;

public interface UserService {

    String createUser(UserDto user);

    List<User> getAllUsers();

    User getUserById(Long id);

    List<User> getUsersById(List<Long> id);

    Boolean deleteUser (Long userId, String password);
}
