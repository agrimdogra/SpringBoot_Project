package com.ecomerce.dograshopping.dtos;

import com.ecomerce.dograshopping.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
public class ProductExceptionDto {
    private HttpStatus status;
    private String message;
}
