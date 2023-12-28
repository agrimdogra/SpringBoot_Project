package com.ecomerce.dograshopping.dtos.exceptionDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public class CategoryAlreadyExistExceptionDto {
    private String message;
    private HttpStatus httpStatus;
}
