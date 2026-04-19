package com.trust.microservices.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="product",url = "http://USER:8081/")
public interface ProductClient {

}
