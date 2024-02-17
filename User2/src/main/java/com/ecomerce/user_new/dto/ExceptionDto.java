package com.ecomerce.user_new.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Builder
@Getter
public class ExceptionDto {
    private String message;
    private HttpStatus httpStatus;
}
