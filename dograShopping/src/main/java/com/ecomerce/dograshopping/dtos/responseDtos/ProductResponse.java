package com.ecomerce.dograshopping.dtos.responseDtos;

import com.ecomerce.dograshopping.models.Category;
import com.ecomerce.dograshopping.models.Product;
import com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient.FakeStoreProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Builder
public class ProductResponse {
    private String id;
    private String title;
    private String description;
    private int price;
    private String image;
    private  String categoryName;
    private String categoryId;

    public static ProductResponse SelfServiceProdtoProductResopnse(Product product){
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId().toString())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .categoryName(product.getCategory().getName())
                .categoryId(product.getCategory().getId().toString())
                .build();
        return productResponse;
    }

    public static ProductResponse FakeStoreProdtoProductResopnse(FakeStoreProductDto product){
        ProductResponse productResponse = ProductResponse.builder()
                .id(String.valueOf(product.getId()))
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .categoryName(product.getCategory())
                .categoryId("")
                .build();
        return productResponse;
    }

}
