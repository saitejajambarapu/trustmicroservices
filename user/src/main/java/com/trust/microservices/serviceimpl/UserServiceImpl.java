package com.trust.microservices.serviceimpl;

import com.trust.microservices.dto.EmailKafkaDto;
import com.trust.microservices.dto.UserDto;
import com.trust.microservices.entity.User;
import com.trust.microservices.repository.UserRepo;
import com.trust.microservices.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    private final KafkaTemplate<Long, EmailKafkaDto> kafkaTemplate;
    @Override
    public String createUser(UserDto user) {
        User userEntity = modelMapper.map(user,User.class);
        userEntity.setRoleId(2);
        userEntity.setActive(true);
        userEntity.setModifiedOn(new Timestamp(System.currentTimeMillis()));
        userEntity.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        userEntity.setLastLogInOn(new Timestamp(System.currentTimeMillis()));
        userEntity = userRepo.save(userEntity);
        EmailKafkaDto emailKafkaDto = new EmailKafkaDto();
        emailKafkaDto.setTo(user.getEmail());
        emailKafkaDto.setMessage("User account created");
        kafkaTemplate.send("user_created_topic",emailKafkaDto);
        return "Account created Successfully with id : "+ userEntity.getUserId();
    }

    @Override
    public List<User> getAllUsers() {
        log.info("in product user client method");
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()) return null;
        return user.get();
    }

    @Override
    public List<User> getUsersById(List<Long> id) {
        return userRepo.findAllById(id).get();

    }

    @Override
    public Boolean deleteUser(Long userId, String password) {
        Optional<User> userDelete = userRepo.findById(userId);
        if (!userDelete.isEmpty() && password.equals(userDelete.get().getPassword())){
            userDelete.get().setActive(false);
            userRepo.save(userDelete.get());
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email);
    }
}
