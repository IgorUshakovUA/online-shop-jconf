package com.study.shop.web.config;

import com.study.shop.service.ProductService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {
    @Bean
    public ProductService productService() {
        return Mockito.mock(ProductService.class);
    }
}
