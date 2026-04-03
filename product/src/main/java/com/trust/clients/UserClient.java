package com.trust.clients;

import com.trust.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user", url = "http://localhost:8080/user")
public interface UserClient {

    @GetMapping("/getUsers")
    public List<UserDto> getUsers();
}
