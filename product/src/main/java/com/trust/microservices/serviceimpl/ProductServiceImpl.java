package com.trust.microservices.serviceimpl;

import com.trust.microservices.Exception.ResourceNotFoundException;
import com.trust.microservices.clients.UserClient;
import com.trust.microservices.dto.*;
import com.trust.microservices.dto.BuyerDto;
import com.trust.microservices.dto.ProductDto;
import com.trust.microservices.dto.ProductUserDetailsDto;
import com.trust.microservices.dto.UserDto;
import com.trust.microservices.entity.Product;
import com.trust.microservices.entity.ProductBuyerRelation;
import com.trust.microservices.repository.ProductBuyerRelationRepository;
import com.trust.microservices.repository.ProductRepository;
import com.trust.microservices.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;

    private final UserClient userClient;

    private final ProductRepository productRepository;
    private final ProductBuyerRelationRepository productBuyerRelationRepository;

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
    public ProductDto getProductById(Long id) {

        Optional<Product> product = productRepository.findById(id);

        ProductDto productDto = modelMapper.map(product.get(),ProductDto.class);

        return productDto;
    }

    @Override
    public List<UserDto> getUsers() {
        return userClient.getUsers();
    }

    @Override
    public Boolean buyProduct(Long productId, Long buyerId) {
        Optional<Product> product = productRepository.findById(productId);
        UserDto buyer=userClient.getUserById(buyerId);
        if(!product.isEmpty() && buyer!=null){
            ProductBuyerRelation productBuyerRelation=new ProductBuyerRelation();
            productBuyerRelation.setBuyerId(buyerId);
            productBuyerRelation.setProductId(productId);
            productBuyerRelation.setActive(true);
            productBuyerRelation.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
            productBuyerRelationRepository.save(productBuyerRelation);
            return true;
        }
        return false;
    }

    @Override
    public ProductUserDetailsDto productInfo(Long productId) {
        Optional<List<ProductBuyerRelation>> productBuyerRelation = productBuyerRelationRepository.findByProductId(productId);
        Optional<Product> productDetails = productRepository.findById(productId);
        List<Long> buyerIds= new ArrayList<>();
        for(ProductBuyerRelation p : productBuyerRelation.get()){
            buyerIds.add(p.getBuyerId());
        }
        List<UserDto> buyerDetails = userClient.getUsers(buyerIds);
        UserDto sellerDetails = userClient.getUserById(productDetails.get().getUserId());

        ProductUserDetailsDto productUserDetailsDto = new ProductUserDetailsDto();
        if (!productBuyerRelation.isEmpty() && !productDetails.isEmpty() && buyerDetails != null && sellerDetails != null) {
            productUserDetailsDto.setProductId(productId);
            productUserDetailsDto.setProductName(productDetails.get().getProductName());
            productUserDetailsDto.setSellerId(sellerDetails.getUserId());
            productUserDetailsDto.setSellerName(sellerDetails.getUserName());
            List<BuyerDto> buyersList = new ArrayList<>();
            for(UserDto buyer: buyerDetails){
                BuyerDto buyerDto = new BuyerDto();
                buyerDto.setBuyerId(buyer.getUserId());
                buyerDto.setBuyerName(buyer.getUserName());
                for(ProductBuyerRelation pbr : productBuyerRelation.get()){
                    if(buyer.getUserId()==pbr.getBuyerId())
                        buyerDto.setBoughtRequest(pbr.getCreatedOn());
                }
                buyersList.add(buyerDto);
            }
            productUserDetailsDto.setBuyers(buyersList);
        }
        return productUserDetailsDto;
    }

    @Override
    public Boolean productDelete(Long productID,Long userId) {
        Optional<Product> product =productRepository.findById(productID);
        if(!product.isEmpty()){
            if (product.get().getUserId()==userId) {
                product.get().setIsActive(false);
                productRepository.save(product.get());
                return true;
            }
        }
        return false;

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
