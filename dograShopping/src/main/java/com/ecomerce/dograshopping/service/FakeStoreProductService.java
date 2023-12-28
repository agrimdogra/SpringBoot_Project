package com.ecomerce.dograshopping.service;

import com.ecomerce.dograshopping.dtos.responseDtos.ProductResponse;
import com.ecomerce.dograshopping.thirdPartyClients.MyShoppingApiAdapter;
import com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient.FakeStoreProductDto;
import com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient.ProductRequestDto;
import com.ecomerce.dograshopping.exceptions.ProductNotFound;
import com.ecomerce.dograshopping.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{


    // COMMENTED AS IT IS NOW DONE IN API CLIENT (ADAPTER)
      RestTemplateBuilder restTemplateBuilder;
      private final String fakeStoreApiURL = "https://fakestoreapi.com/products/{id}";
      private final String fakeStoreCreateProductURL = "https://fakestoreapi.com/products";
      private final Random random = new Random();
//    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplateBuilder = restTemplateBuilder;
//    }

    // Injecting the 3rd party client to be used
    private MyShoppingApiAdapter myShoppingApiAdapter;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder, @Qualifier("fakeStoreApiAdapter") MyShoppingApiAdapter myShoppingApiAdapter) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.myShoppingApiAdapter = myShoppingApiAdapter;
    }

//    public Product fakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
//        Product product = new Product();
//        product.setId(fakeStoreProductDto.getId());
//        product.setTitle(fakeStoreProductDto.getTitle());
//        product.setCategory(Category.builder().id(UUID.randomUUID()).name(fakeStoreProductDto.getCategory()).build());
//        product.setPrice(fakeStoreProductDto.getPrice());
//        product.setImage(fakeStoreProductDto.getImage());
//        product.setDescription(fakeStoreProductDto.getDescription());
//        return product;
//    }

    @Override
    public List<ProductResponse> getAllProducts() {
        //RestTemplate restTemplate = restTemplateBuilder.build();

//        ResponseEntity<FakeStoreProductDto[]>response = restTemplate.getForEntity(fakeStoreCreateProductURL, FakeStoreProductDto[].class);
//        FakeStoreProductDto[] resultProds = response.getBody();
//
//        List<FakeStoreProductDto> products = Stream.of(resultProds).toList();
//        List<Product> answer = new ArrayList<>();
//        products.forEach(prod->{
//            answer.add(fakeStoreProductDtoToProduct(prod));
//        });

        /**
         *  BELOW CODE IS AFTER USING ADAPTER FOR 3RD PARTY CALL
         */
        List<FakeStoreProductDto> resultProducts = myShoppingApiAdapter.getAllProducts();
        List<ProductResponse> prodList=  resultProducts.stream()
                .map(ProductResponse::FakeStoreProdtoProductResopnse)
                .toList();
        return prodList;
    }

    @Override
    public ProductResponse getProductbyId(String id) {
        FakeStoreProductDto result = myShoppingApiAdapter.getProductbyId(id);
        return ProductResponse.FakeStoreProdtoProductResopnse(result);
    }

    @Override
    public ProductResponse createProduct(ProductRequestDto productRequestDto) {
        FakeStoreProductDto result = myShoppingApiAdapter.createProduct(productRequestDto);
        return ProductResponse.FakeStoreProdtoProductResopnse(result);
    }

    @Override
    public ProductResponse deleteProduct(String id) {
        FakeStoreProductDto fakeStoreProductDto = myShoppingApiAdapter.deleteProduct(Long.parseLong(id));
        if (fakeStoreProductDto == null){
            throw new ProductNotFound("the id " + id + " does not exist");
        }
        return ProductResponse.FakeStoreProdtoProductResopnse(fakeStoreProductDto);
    }

    @Override
    public ProductResponse updateProduct(ProductRequestDto productRequestDto, String id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productRequestDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.execute(fakeStoreApiURL, HttpMethod.PUT, requestCallback, responseExtractor, id);

        return ProductResponse.FakeStoreProdtoProductResopnse(response.getBody());
    }
}
