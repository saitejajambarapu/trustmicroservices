package com.trust.controller;

import com.trust.dto.ProductDto;
import com.trust.entity.Product;
import com.trust.service.ProductService;
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
}


