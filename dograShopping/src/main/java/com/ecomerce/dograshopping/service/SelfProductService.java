package com.ecomerce.dograshopping.service;

import com.ecomerce.dograshopping.dtos.responseDtos.ProductResponse;
import com.ecomerce.dograshopping.exceptions.ProductNotFound;
import com.ecomerce.dograshopping.models.Category;
import com.ecomerce.dograshopping.models.Product;
import com.ecomerce.dograshopping.repository.CategoryRepository;
import com.ecomerce.dograshopping.repository.ProductRepository;
import com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient.ProductRequestDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.beans.Transient;
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
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        if (! product.isPresent()){
            throw new ProductNotFound("Product with id " + id +" does not exist");
        }
        ProductResponse productResponse = ProductResponse.SelfServiceProdtoProductResopnse(product.get());
        return productResponse;
    }

    @Override
    public ProductResponse createProduct(ProductRequestDto productRequestDto) {
        Optional<Category> cat= categoryRepository.getCategoryById(productRequestDto.getCategory().getId());
        cat.ifPresent(productRequestDto::setCategory);

        Product product = productRequestDto.toProduct();
        Product product1 = productRepository.save(product);
        ProductResponse productResponse = ProductResponse.SelfServiceProdtoProductResopnse(product1);
        return productResponse;
    }


    @Override
    public ProductResponse deleteProduct(String id) {
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        if (! product.isPresent()){
            throw new ProductNotFound("Product with id " + id +" does not exist");
        }
        productRepository.delete(product.get());
        ProductResponse productResponse = ProductResponse.SelfServiceProdtoProductResopnse(product.get());
        return productResponse;
    }
    @Transactional
    @Override
    public ProductResponse updateProduct(ProductRequestDto productRequestDto, String id) {
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        if (!product.isPresent()){
            throw new ProductNotFound("Product with id " + id +" does not exist");
        }
        int affectedRows = productRepository.updateProductById(productRequestDto.getTitle(), productRequestDto.getPrice(), UUID.fromString(id));
        if (affectedRows > 0){
            ProductResponse productResponse= ProductResponse.builder()
                    .price(product.get().getPrice())
                    .title(productRequestDto.getTitle())
                    .categoryId(product.get().getCategory().getId().toString())
                    .categoryName(product.get().getCategory().getName())
                    .image(productRequestDto.getImage())
                    .description(productRequestDto.getDescription())
                    .id(product.get().getId().toString())
                    .build();
            return productResponse;
        }

        return null;
    }


    /*  UTILITY METHODS  */
//    private boolean isCategoryPresent(Category category) {
//    }

}
