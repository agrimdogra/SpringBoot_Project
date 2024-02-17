package com.ecomerce.user_new.controller;

import com.ecomerce.user_new.dto.requestDto.UserRequestDto;
import com.ecomerce.user_new.dto.responseDto.UserResponseDto;
import com.ecomerce.user_new.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto1 = userService.createUser(userRequestDto);
        return new ResponseEntity<>(
                userResponseDto1,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserByEmail(UserResponseDto userResponseDto){

        return null;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> users = userService.getAllUsers();

        return new ResponseEntity<>(
                users,
                HttpStatus.OK
        );
    }

    @PostMapping("/{id}/addRole")
    public  ResponseEntity<UserResponseDto> addRole(@PathVariable Long id, @RequestParam String role){
        UserResponseDto userResponseDto = userService.addRole(id, role);
        return new ResponseEntity<>(
                userResponseDto,
                HttpStatus.CREATED
        );
    }

    @PostMapping("/{id}/addUser")
    public  ResponseEntity<UserResponseDto> addRole1(@PathVariable Long id, @RequestParam String name){
        return null;
    }


}
