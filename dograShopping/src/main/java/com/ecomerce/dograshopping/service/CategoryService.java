package com.ecomerce.dograshopping.service;

import com.ecomerce.dograshopping.dtos.responseDtos.CategoryResponse;
import com.ecomerce.dograshopping.dtos.responseDtos.ProductResponse;
import com.ecomerce.dograshopping.exceptions.CategoryAlreadyExistException;
import com.ecomerce.dograshopping.models.Category;
import com.ecomerce.dograshopping.models.Product;
import com.ecomerce.dograshopping.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@Setter
@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category){
        if(isCategoryPresent(category)){
            throw new CategoryAlreadyExistException("the category with name" +category.getName() + " already exist in DB");
        }

        return categoryRepository.save(category);
    }

    public Category getCategoryByName(String name){
        Optional<Category> category = categoryRepository.getCategoriesByName(name);
        return category.orElseThrow(()->new RuntimeException("Error"));
    }

    public Category getCategoryById(UUID id){
        Optional<Category> category = categoryRepository.getCategoryById(id);
        return category.orElseThrow(()->new RuntimeException("category Not Found"));
    }

    public List<CategoryResponse> getAllCategories(){
        List<Category> categories =  categoryRepository.findAll();
        List<CategoryResponse> resp = new ArrayList<>();
        for (Category category : categories){
            List<Product> prod =  category.getProducts();
            List<ProductResponse> prodResp = prod.stream()
                            .map(ProductResponse::SelfServiceProdtoProductResopnse)
                            .toList();
            resp.add(new CategoryResponse(category.getId(), prodResp));
        }

        //List<Product> products = categories.get(0).getProducts();
        return resp;
    }


    /*UTILITY METHODS*/
    private boolean isCategoryPresent(Category category) {
        Optional<Category> category1 = categoryRepository.getCategoriesByName(category.getName());
        return category1.isPresent();
    }

}
