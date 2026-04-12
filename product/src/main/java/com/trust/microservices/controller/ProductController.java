package com.trust.microservices.controller;

import com.trust.microservices.dto.ProductDto;
import com.trust.microservices.dto.ProductUserDetailsDto;
import com.trust.microservices.dto.UserDto;
import com.trust.microservices.entity.Product;
import com.trust.microservices.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<Long> createProduct(@RequestBody ProductDto productDto){
            return new ResponseEntity<>(productService.createProduct(productDto), HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductDto> editProduct(@RequestBody ProductDto productDto,@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.editProduct(productDto, id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/getUsers")
    public List<UserDto> getUsers(){
        return productService.getUsers();
    }

    @PostMapping("/buyProduct/{productId}/{buyerId}")
    public Boolean buyProduct(@PathVariable Long productId, @PathVariable Long buyerId){
        return productService.buyProduct(productId,buyerId);
    }

    @GetMapping("/productInfo/{productId}")
    public ProductUserDetailsDto productInfo(@PathVariable Long productId){
        ProductUserDetailsDto productUserDetailsDto=productService.productInfo(productId);
        return productUserDetailsDto;
    }

    @DeleteMapping("/deleteProduct/{productId}/{userId}")
    public Boolean productDelete (@PathVariable Long productId, @PathVariable Long userId){
        return productService.productDelete(productId,userId);
    }
}


