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

    private boolean isActive;

    private String password;

    private Timestamp lastLogInOn;

    private Timestamp createdOn;

    private Timestamp modifiedOn;

    private int roleId;
}
