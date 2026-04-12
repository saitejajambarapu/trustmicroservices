package com.trust.microservices.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "email_t")
@Data
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;

    private String action;

    private String message;

    private String toMail;

    private Timestamp createdOn;

    private Timestamp lastModifiedOn;

    private String ccMail;

    private String subject;

    private String emailBody;

}
