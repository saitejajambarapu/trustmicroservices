package com.trust.microservices.consumer;

import com.trust.microservices.dto.EmailDto;
import com.trust.microservices.dto.EmailKafkaDto;
import com.trust.microservices.enums.EmailAction;
import com.trust.microservices.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumer {

    private EmailService emailService;

    @KafkaListener(topics = "user_created_topic")
    public void userCreated(EmailKafkaDto emailKafkaDto){
        log.info("in usercreated  method");
        System.out.println(emailKafkaDto.getMessage()+emailKafkaDto.getTo());
        EmailDto emailDto = EmailDto.builder()
                .action(EmailAction.CREATED.toString())
                .message(emailKafkaDto.getMessage())
                .ccMail(null)
                .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedOn(Timestamp.valueOf(LocalDateTime.now()))
                .emailBody("Your Account has been created successfully !")
                .subject("Account Created SuccessFully!")
                .toMail(emailKafkaDto.getTo())
                .build();
        emailService.saveEmail(emailDto);
    }

}
