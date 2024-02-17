package com.ecomerce.user_new.controller;

import com.ecomerce.user_new.dto.requestDto.UserRequestDto;
import com.ecomerce.user_new.dto.responseDto.SessionResponseDto;
import com.ecomerce.user_new.dto.responseDto.UserResponseDto;
import com.ecomerce.user_new.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Queue;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<SessionResponseDto> login(@RequestBody UserRequestDto userRequestDto){
        return authService.login(userRequestDto);
    }
    @PostMapping("/logout/{token}")
    public ResponseEntity<SessionResponseDto> logout(@PathVariable String token){
        SessionResponseDto sessionResponseDto = authService.logout(token);
        return new ResponseEntity<>(
                sessionResponseDto,
                HttpStatus.OK
        );
    }

}
