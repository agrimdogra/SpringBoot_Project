package com.ecomerce.dograshopping.controller;

import com.ecomerce.dograshopping.dtos.responseDtos.ProductResponse;
import com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient.ProductRequestDto;
import com.ecomerce.dograshopping.exceptions.InvalidProductExceptionError;
import com.ecomerce.dograshopping.exceptions.ProductNotFound;
import com.ecomerce.dograshopping.models.Product;
import com.ecomerce.dograshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class

   ProductController {
    ProductService productService;

    public ProductController(@Qualifier("SelfProductService") ProductService productService) {     //FakeStoreProductService
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        List<ProductResponse> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable String id){
        ProductResponse product = productService.getProductbyId(id);
        return product;
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponse product = productService.createProduct(productRequestDto);
        //ProductResponse response =  ProductResponse.toProductResopnse(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable String id) throws ProductNotFound {
        ProductResponse product = productService.deleteProduct(id);
        return new ResponseEntity<>(
                product,
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequestDto productRequestDto, @PathVariable String id){
        ProductResponse product = productService.updateProduct(productRequestDto, id);
        return new ResponseEntity<>(
                product,
                HttpStatus.OK
        );
    }

/**
 *      ---------HANDLING EXCEPTION IN CONTROLLER INSTEAD OF CONTROLLER ADVISE---------
 */
/*    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ProductExceptionDto> handleProductNotFoundException(ProductNotFound productNotFound){
        return new ResponseEntity<>(
                new ProductExceptionDto(HttpStatus.NOT_FOUND, productNotFound.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }*/



}
