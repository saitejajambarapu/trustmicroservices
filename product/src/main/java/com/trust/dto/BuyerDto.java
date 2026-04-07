package com.trust.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class BuyerDto {
    private Long buyerId;
    private String buyerName;
    private Timestamp boughtRequest;
}
