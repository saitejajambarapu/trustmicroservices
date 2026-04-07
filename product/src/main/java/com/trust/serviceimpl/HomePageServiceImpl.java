package com.trust.serviceimpl;

import com.trust.dto.HomePageCardDto;
import com.trust.dto.HomePageDto;
import com.trust.entity.Product;
import com.trust.repository.ProductRepository;
import com.trust.service.HomePageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HomePageServiceImpl implements HomePageService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public HomePageDto getHomePageDetails() {

        List<Product> productList = productRepository.findAll();
        List<String> productTypes = productRepository.getProductTypes();
        Map<String, List<HomePageCardDto>> homePageCardDtoMap = new HashMap<>();
        for(String s : productTypes){
            homePageCardDtoMap.put(s,new ArrayList<HomePageCardDto>());
        }
        for(Product p : productList){
            HomePageCardDto h = modelMapper.map(p,HomePageCardDto.class);

            homePageCardDtoMap.get(p.getType()).add(h);
        }
        List homePage =  new ArrayList();
        homePage.add(homePageCardDtoMap);
        HomePageDto homePageDto = new HomePageDto();
        homePageDto.setHomePageDetails(homePage);
        return homePageDto;
    }
}
