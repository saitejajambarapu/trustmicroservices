package com.trust.microservices.service;

import com.trust.microservices.dto.ProductDto;
import com.trust.microservices.dto.ProductUserDetailsDto;
import com.trust.microservices.dto.UserDto;
import com.trust.microservices.entity.Product;

import java.util.List;

public interface ProductService {

    ProductDto editProduct(ProductDto productDto, long id);
    Long createProduct(ProductDto productDto);
    List<Product> getAllProducts();

    ProductDto getProductById(Long id);

    List<UserDto> getUsers();

    Boolean buyProduct(Long productId, Long buyerId);

    ProductUserDetailsDto productInfo(Long productId);

    Boolean productDelete (Long productID,Long userId);

}
