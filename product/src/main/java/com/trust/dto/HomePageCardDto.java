package com.trust.dto;

import lombok.Data;

@Data

public class HomePageCardDto {

    private Long  productId;

    private String imageUrl;

    private String productName;

    private String description;

    private Double price;

    private Double trustValue;
}
