package com.ecomerce.dograshopping.dtos;

import com.ecomerce.dograshopping.models.Category;
import com.ecomerce.dograshopping.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {
    private  String title;
    private  int price;
    private String description;
    private Category category;
    private String image;

    private Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(category);
        product.setImage(image);
        return product;
    }
}
