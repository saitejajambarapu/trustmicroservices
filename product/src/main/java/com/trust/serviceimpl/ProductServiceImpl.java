package com.trust.serviceimpl;

import com.trust.Exception.ResourceNotFoundException;
import com.trust.clients.UserClient;
import com.trust.dto.ProductDto;
import com.trust.dto.UserDto;
import com.trust.entity.Product;
import com.trust.repository.ProductRepository;
import com.trust.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;

    private final UserClient userClient;

    private final ProductRepository productRepository;

    @Override
    public Long createProduct(ProductDto productDto) {
        Long id  = productDto.getUserId();
        UserDto userDto = userClient.getUserById(id);
        if(userDto == null) try {
            throw new ResourceNotFoundException("User With User Id :"+id+  " not found");
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public List<UserDto> getUsers() {
        return userClient.getUsers();
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
