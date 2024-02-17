package com.ecomerce.user_new.controller;

import com.ecomerce.user_new.dto.requestDto.RoleDto;
import com.ecomerce.user_new.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto){
        RoleDto roleDto1 = roleService.createRole(roleDto);
        return new ResponseEntity<>(
                roleDto1,
                HttpStatus.OK
        );
    }

    @GetMapping()
    public ResponseEntity<Set<RoleDto>> getAllRoles(){
        Set<RoleDto> result = roleService.getAllRoles();
        return new ResponseEntity<>(
                result,
                HttpStatus.OK
        );
    }
}
