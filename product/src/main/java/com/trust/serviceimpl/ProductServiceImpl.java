package com.trust.serviceimpl;

import com.trust.dto.ProductDto;
import com.trust.entity.Product;
import com.trust.repository.ProductRepository;
import com.trust.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public ProductDto editProduct(ProductDto productDto, long id) {
        Optional<Product> product = productRepository.findById(id);
        ProductDto modifiedProduct = new ProductDto();
        if(product!=null){
            modelMapper.map(productDto,product.get());
            productRepository.save(product.get());
            modelMapper.map(product.get(),modifiedProduct);
        }
        return modifiedProduct;

    }


}
