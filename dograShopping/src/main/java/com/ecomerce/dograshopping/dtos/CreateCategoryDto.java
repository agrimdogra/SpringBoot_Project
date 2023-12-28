package com.ecomerce.dograshopping.dtos;

import com.ecomerce.dograshopping.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryDto {
    private String categoryName;
    public Category toCategory(){
        Category category = new Category(categoryName);
        return category;
    }
}
