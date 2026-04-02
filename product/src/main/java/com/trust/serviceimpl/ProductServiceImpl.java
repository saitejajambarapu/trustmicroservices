package com.trust.serviceimpl;

import com.trust.dto.ProductDto;
import com.trust.entity.Product;
import com.trust.repository.ProductRepository;
import com.trust.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;

    private final ProductRepository productRepository;

    @Override
    public Long createProduct(ProductDto productDto) {

        Product product = new Product();
        modelMapper.map(productDto,product);
        productRepository.save(product);
        return product.getProductId();

    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;

    }


}
