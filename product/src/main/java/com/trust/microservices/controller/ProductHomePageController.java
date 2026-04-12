package com.trust.microservices.controller;

import com.trust.microservices.dto.HomePageDto;
import com.trust.microservices.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Home")
public class ProductHomePageController {

    @Autowired
    private HomePageService homePageService;

    @GetMapping()
    public HomePageDto getHomeDetails(){
        return homePageService.getHomePageDetails();
    }

    @GetMapping("/{id}")
    public HomePageDto getHomeDetails(@PathVariable Long id){
        return homePageService.getHomePageDetails();
    }

}
