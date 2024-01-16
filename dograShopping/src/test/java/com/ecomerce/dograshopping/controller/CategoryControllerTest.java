package com.ecomerce.dograshopping.controller;

import com.ecomerce.dograshopping.dtos.responseDtos.ProductResponse;
import com.ecomerce.dograshopping.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryControllerTest {
    @Autowired
    ProductController productController;
    @MockBean(name = "FakeStoreProductService")
    ProductService productService;

    @Test
    public void getAllProductsShouldReturnSameProductAsReturnedByService(){
        List<ProductResponse> products = List.of( new ProductResponse("1", "Pixel8", "Google Pixel 8",65000,"phone.image", "Electronics","100"),
                                                  new ProductResponse("2", "iPhone 12", "iPhone 12 old",65000,"phone.image", "Electronics","100"),
                                                  new ProductResponse("3", "Pixel8", "Google Pixel 5",35000,"phone.image", "Electronics","100")
                                                );

        when(
                productService.getAllProducts()
        ).thenReturn(products);

        List<ProductResponse> productResponse = productController.getAllProducts();

        assertEquals(products.get(0).getId(), productResponse.get(0).getId());


    }
}