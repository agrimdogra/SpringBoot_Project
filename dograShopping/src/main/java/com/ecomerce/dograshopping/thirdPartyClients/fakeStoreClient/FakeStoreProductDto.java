package com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient;

import com.ecomerce.dograshopping.models.Category;
import com.ecomerce.dograshopping.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.SpringApplication;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private  String title;
    private  int price;
    private String description;
    private String category;
    private String image;

    Random random = new Random();
//    public Product toProduct() {
//
//        Product product =  Product.builder()
//                .title(title)
//                .description(description)
//                .image(image)
//                .price(price)
//                .category(Category.builder().id(UUID.randomUUID()).name(category).build())
//                .build();
//        product.setId(String.valueOf(id));
//        return product;
//    }

}
