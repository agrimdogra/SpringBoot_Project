package com.ecomerce.dograshopping.service;

import com.ecomerce.dograshopping.dtos.responseDtos.ProductResponse;
import com.ecomerce.dograshopping.models.Category;
import com.ecomerce.dograshopping.models.Product;
import com.ecomerce.dograshopping.repository.CategoryRepository;
import com.ecomerce.dograshopping.repository.ProductRepository;
import com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient.ProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@AllArgsConstructor
@Service("SelfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> results = new ArrayList<>();
        products.forEach(prod->{
            results.add(ProductResponse.SelfServiceProdtoProductResopnse(prod));
        });
        return results;
    }

    @Override
    public ProductResponse getProductbyId(String id) {
//        Optional<Product> product = productRepository.findById(id)
        return null;
    }

    @Override
    public ProductResponse createProduct(ProductRequestDto productRequestDto) {
        Product product = productRequestDto.toProduct();
        Product product1 = productRepository.save(product);
        ProductResponse productResponse = ProductResponse.SelfServiceProdtoProductResopnse(product1);
        return productResponse;
    }


    @Override
    public ProductResponse deleteProduct(String id) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(ProductRequestDto productRequestDto, String id) {
        return null;
    }


    /*  UTILITY METHODS  */
//    private boolean isCategoryPresent(Category category) {
//    }

}
