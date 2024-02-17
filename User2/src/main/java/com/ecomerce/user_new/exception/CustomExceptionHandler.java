package com.ecomerce.user_new.exception;

import com.ecomerce.user_new.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ExceptionDto> handleInvalidRole(InvalidRoleException invalidRoleException) {
        return new ResponseEntity<>(
                ExceptionDto.builder()
                        .message(invalidRoleException.getMessage())
                        .httpStatus(HttpStatus.FORBIDDEN)
                        .build(),
                HttpStatus.OK
        );
    }

    @ExceptionHandler(RoleAlraedyExistException.class)
    public ResponseEntity<ExceptionDto> handleRoleAlreadyExist(RoleAlraedyExistException roleAlraedyExistException) {
        return new ResponseEntity<>(
                ExceptionDto.builder()
                        .message(roleAlraedyExistException.getMessage())
                        .httpStatus(HttpStatus.FORBIDDEN)
                        .build(),
                HttpStatus.OK
        );
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionDto> handleUserAlreadyExist(UserAlreadyExistException userAlreadyExistException) {
        return new ResponseEntity<>(
                ExceptionDto.builder()
                        .message(userAlreadyExistException.getMessage())
                        .httpStatus(HttpStatus.FORBIDDEN)
                        .build(),
                HttpStatus.OK
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFound(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(
                ExceptionDto.builder()
                        .message(userNotFoundException.getMessage())
                        .httpStatus(HttpStatus.FORBIDDEN)
                        .build(),
                HttpStatus.OK
        );
    }

    @ExceptionHandler(MaxSessionLimitException.class)
    public ResponseEntity<ExceptionDto> handleMaxSessionLimit(MaxSessionLimitException maxSessionLimitException) {
        return new ResponseEntity<>(
                ExceptionDto.builder()
                        .message(maxSessionLimitException.getMessage())
                        .httpStatus(HttpStatus.FORBIDDEN)
                        .build(),
                HttpStatus.OK
        );
    }

}
