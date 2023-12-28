package com.ecomerce.dograshopping.thirdPartyClients;

import com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient.FakeStoreProductDto;
import com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient.ProductRequestDto;

import java.util.List;

public interface MyShoppingApiAdapter {
    public List<FakeStoreProductDto> getAllProducts();
    public FakeStoreProductDto getProductbyId(String id);
    public FakeStoreProductDto createProduct(ProductRequestDto productRequestDto);
    public  FakeStoreProductDto deleteProduct(Long id);
    public FakeStoreProductDto updateProduct(ProductRequestDto productRequestDto, Long id);
}
