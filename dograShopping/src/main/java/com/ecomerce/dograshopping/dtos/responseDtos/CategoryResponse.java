package com.ecomerce.dograshopping.dtos.responseDtos;

import com.ecomerce.dograshopping.models.Product;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponse {
    private UUID id;
    private List<ProductResponse> productResponses  = new ArrayList<>();

}
