package com.ecomerce.dograshopping.exceptions;

import com.ecomerce.dograshopping.dtos.exceptionDtos.CategoryAlreadyExistExceptionDto;
import com.ecomerce.dograshopping.dtos.ProductExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ProductExceptionDto> handleProductNotFoundException(ProductNotFound productNotFound){
        return new ResponseEntity<>(
                new ProductExceptionDto(HttpStatus.NOT_FOUND, productNotFound.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(InvalidProductExceptionError.class)
    public ResponseEntity<ProductExceptionDto> InvalidProductExceptionError(InvalidProductExceptionError invalidProductExceptionError){
        return new ResponseEntity<>(
                new ProductExceptionDto(HttpStatus.BAD_REQUEST, invalidProductExceptionError.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<CategoryAlreadyExistExceptionDto> CategoryAlreadyExistException(CategoryAlreadyExistException categoryAlreadyExistException){
        return new ResponseEntity<>(
                new CategoryAlreadyExistExceptionDto(categoryAlreadyExistException.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.OK
        );
    }



}
