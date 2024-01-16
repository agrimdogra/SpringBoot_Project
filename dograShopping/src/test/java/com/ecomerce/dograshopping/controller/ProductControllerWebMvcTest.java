package com.ecomerce.dograshopping.controller;

import com.ecomerce.dograshopping.dtos.responseDtos.ProductResponse;
import com.ecomerce.dograshopping.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerWebMvcTest {
    @Autowired
    ProductController productController;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;


    @MockBean(name = "FakeStoreProductService")
    ProductService productService;

    //Test Positive Test Case
    @Test
    void testGetProductByIdShouldReturnWhateverIsReturnedByProductService() throws Exception {
        ProductResponse productResponseExpected = new ProductResponse(
                "10", "Pixel8", "Google Pixel 8",
                65000,"phone.image", "Electronics","100");


        when(
                productService.getProductbyId("11")
        ).thenReturn(productResponseExpected);

        mockMvc.perform(get("/api/v1/products/10"))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(productResponseExpected)));

    }

}
