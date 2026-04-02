package com.trust.service;

import com.trust.dto.ProductDto;
import com.trust.entity.Product;

import java.util.List;

public interface ProductService {

    Long createProduct(ProductDto productDto);
    List<Product> getAllProducts();
}
