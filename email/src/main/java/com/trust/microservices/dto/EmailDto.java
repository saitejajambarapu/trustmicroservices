package com.trust.microservices.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class EmailDto {

    private String action;

    private String message;

    private String toMail;

    private Timestamp createdOn;

    private Timestamp lastModifiedOn;

    private String ccMail;

    private String subject;

    private String emailBody;
}
