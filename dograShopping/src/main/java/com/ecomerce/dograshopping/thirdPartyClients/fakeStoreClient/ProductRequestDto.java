package com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient;

import com.ecomerce.dograshopping.models.Category;
import com.ecomerce.dograshopping.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductRequestDto {
    private  String title;
    private  int price;
    private String description;
    private Category category;
    private String image;


    public Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(category);
        product.setImage(image);
        return product;
    }
}
