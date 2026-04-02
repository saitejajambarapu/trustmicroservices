package com.trust.dto;

import com.trust.enums.ProductHealth;
import lombok.Data;

import java.sql.Date;

@Data
public class ProductDto {

    private String productName;

    private String type;

    private Double price;

    private Double marketValue;

    private Date boughtDate;

    private Double boughtPrice;

    private Boolean isActive;

    private ProductHealth productCondition;

    private String Description;

    private String Specifications;
}
