package com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient;

import com.ecomerce.dograshopping.exceptions.ProductNotFound;
import com.ecomerce.dograshopping.thirdPartyClients.MyShoppingApiAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Component
@Qualifier("fakeStoreApiAdapter")
@Primary
public class FakeStoreApiAdapter implements MyShoppingApiAdapter {

    public RestTemplateBuilder restTemplateBuilder;
    private final String fakeStoreApiURL = "https://fakestoreapi.com/products/{id}";
    private final String fakeStoreCreateProductURL = "https://fakestoreapi.com/products";
    private Random random = new Random();

    public FakeStoreApiAdapter(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<FakeStoreProductDto> getAllProducts() {

        RestTemplate restTemplate = getRestTemplate();

        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(fakeStoreCreateProductURL, FakeStoreProductDto[].class);
        FakeStoreProductDto[] resultProds = response.getBody();

        return Stream.of(resultProds).toList();
    }

    @Override
    public FakeStoreProductDto getProductbyId(String vid) {
        Long id = Long.parseLong(vid);
        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<FakeStoreProductDto>  response = restTemplate.getForEntity(fakeStoreApiURL, FakeStoreProductDto.class, id);
        FakeStoreProductDto res = response.getBody();
        return res;
    }

    @Override
    public FakeStoreProductDto createProduct(ProductRequestDto productRequestDto) {
        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(fakeStoreCreateProductURL, productRequestDto, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    @Override
    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = getRestTemplate();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.execute(fakeStoreApiURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return response.getBody();
    }

    @Override
    public FakeStoreProductDto updateProduct(ProductRequestDto productRequestDto, Long id) {
        return null;
    }


    private RestTemplate getRestTemplate() {
        return restTemplateBuilder.build();
    }
}
