package com.trust.microservices.service;

import com.trust.microservices.dto.ProductDto;
import com.trust.microservices.enums.ProductHealth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;


    @Test
    public void createProductTest(){
        ProductDto dto = new ProductDto();

        dto.setProductName("iPhone 13");
        dto.setType("Electronics");
        dto.setPrice(52000.0);
        dto.setMarketValue(60000.0);
        dto.setBoughtDate(Date.valueOf("2023-05-10"));
        dto.setBoughtPrice(65000.0);
        dto.setIsActive(true);
        dto.setProductCondition(ProductHealth.GOOD);
        dto.setDescription("Well maintained, minor scratches");
        dto.setSpecifications("128GB, Blue, Battery 90%");
        dto.setUserId(1L);
        System.out.println("the product Id is : "+ productService.createProduct(dto));
    }
}