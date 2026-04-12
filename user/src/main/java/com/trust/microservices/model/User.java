package com.trust.microservices.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name="user_t")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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
