package com.trust.serviceimpl;

import com.trust.dto.UserDto;
import com.trust.model.User;
import com.trust.repository.UserRepo;
import com.trust.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String createUser(UserDto user) {
        User userEntity = modelMapper.map(user,User.class);
        userEntity.setRoleId(2);
        userEntity.setActive(true);
        userEntity.setModifiedOn(new Timestamp(System.currentTimeMillis()));
        userEntity.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        userEntity.setLastLogInOn(new Timestamp(System.currentTimeMillis()));
        userEntity = userRepo.save(userEntity);
        return "Account created Successfully with id : "+ userEntity.getUserId();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()) return null;
        return user.get();
    }
}
