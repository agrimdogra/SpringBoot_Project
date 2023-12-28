package com.ecomerce.dograshopping.dtos.responseDtos;

import com.ecomerce.dograshopping.models.Product;
import jakarta.persistence.Enumerated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class CategoryResponse {
    private UUID id;
    private List<ProductResponse> productResponses  = new ArrayList<>();

}
