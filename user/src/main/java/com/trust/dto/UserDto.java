package com.trust.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDto {

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private int roleId;
}
