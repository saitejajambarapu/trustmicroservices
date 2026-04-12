package com.trust.microservices.serviceImpl;

import com.trust.microservices.Repository.EmailRepository;
import com.trust.microservices.dto.EmailDto;
import com.trust.microservices.entity.EmailEntity;
import com.trust.microservices.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    private final ModelMapper modelMapper;

    public void saveEmail(EmailDto emailDto) {
        EmailEntity emailEntity = modelMapper.map(emailDto, EmailEntity.class);
        emailRepository.save(emailEntity);
        log.info("saved in the database");
    }
}
