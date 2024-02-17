package com.ecomerce.user_new.dto.responseDto;

import com.ecomerce.user_new.dto.requestDto.RoleDto;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String email;
    private Set<RoleDto> roles = new HashSet<>();
}
