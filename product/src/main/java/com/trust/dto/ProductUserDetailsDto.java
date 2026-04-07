package com.trust.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductUserDetailsDto {
    private String productName;
    private String sellerName;
    private List<BuyerDto> buyers;
    private Long productId;
    private Long sellerId;
}
