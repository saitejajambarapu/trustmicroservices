package com.trust.entity;

import com.trust.enums.ProductHealth;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "Product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  productId;

    private String productName;

    private String type;

    private Double price;

    private Double marketValue;

    private Date boughtDate;

    private Double boughtPrice;

    private Boolean isActive;

    private ProductHealth productCondition;

    @Column(length = 250)
    private String Description;

    private String Specifications;


}
