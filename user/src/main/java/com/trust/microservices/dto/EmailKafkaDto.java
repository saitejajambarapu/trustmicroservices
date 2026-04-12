package com.trust.microservices.dto;

import lombok.Data;

@Data
public class EmailKafkaDto {

    private String to;
    private String message;
}
