package com.trust.service;

import com.trust.dto.ProductDto;
import com.trust.dto.UserDto;
import com.trust.entity.Product;

import java.util.List;

public interface ProductService {

    ProductDto editProduct(ProductDto productDto, long id);
    Long createProduct(ProductDto productDto);
    List<Product> getAllProducts();

    List<UserDto> getUsers();
}
