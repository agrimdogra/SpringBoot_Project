package com.ecomerce.dograshopping.service;

import com.ecomerce.dograshopping.dtos.responseDtos.ProductResponse;
import com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient.ProductRequestDto;
import com.ecomerce.dograshopping.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public List<ProductResponse> getAllProducts();
    public ProductResponse getProductbyId(String id);
    public ProductResponse createProduct(ProductRequestDto productRequestDto);
    public  ProductResponse deleteProduct(String id);
    public ProductResponse updateProduct(ProductRequestDto productRequestDto, String id);

}
