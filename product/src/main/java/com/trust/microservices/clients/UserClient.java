package com.trust.microservices.clients;

import com.trust.microservices.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
//, url = "http://USER:8080/user"

@FeignClient(name = "USER")
public interface UserClient {

    @GetMapping("/user/getUsers")
    public List<UserDto> getUsers();

    @GetMapping("/user/getUserById")
    public UserDto getUserById(@RequestParam Long id);

    @GetMapping("/user/getUsersById")
    public List<UserDto> getUsers(@RequestParam List<Long> id);
}
