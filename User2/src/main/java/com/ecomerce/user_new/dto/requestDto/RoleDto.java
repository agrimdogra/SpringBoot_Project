package com.ecomerce.user_new.dto.requestDto;

import com.ecomerce.user_new.model.Role;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private String role;

    public Role toRole(){
        return Role.builder()
                .role(this.role)
                .build();
    }
}
