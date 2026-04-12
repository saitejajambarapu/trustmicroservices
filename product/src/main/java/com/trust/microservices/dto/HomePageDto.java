package com.trust.microservices.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HomePageDto {

    private List<Map<String, List<HomePageCardDto>>> homePageDetails;
}
