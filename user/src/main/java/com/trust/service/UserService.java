package com.trust.service;

import com.trust.dto.UserDto;
import com.trust.model.User;

public interface UserService {

    String createUser(UserDto user);
}
