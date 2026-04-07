package com.trust.service;

import com.trust.dto.HomePageDto;
import com.trust.dto.ProductDto;
import com.trust.dto.ProductUserDetailsDto;
import com.trust.dto.UserDto;
import com.trust.entity.Product;

import java.util.List;

public interface ProductService {

    ProductDto editProduct(ProductDto productDto, long id);
    Long createProduct(ProductDto productDto);
    List<Product> getAllProducts();

    ProductDto getProductById(Long id);

    List<UserDto> getUsers();

    Boolean buyProduct(Long productId, Long buyerId);

    ProductUserDetailsDto productInfo(Long productId);

}
