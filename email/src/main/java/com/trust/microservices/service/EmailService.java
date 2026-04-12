package com.trust.microservices.service;

import com.trust.microservices.dto.EmailDto;
import com.trust.microservices.dto.EmailKafkaDto;

public interface EmailService {

    void saveEmail(EmailDto emailDto);

}
