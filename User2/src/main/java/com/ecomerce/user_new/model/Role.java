package com.ecomerce.user_new.model;

import com.ecomerce.user_new.dto.requestDto.RoleDto;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseModel{
    private String role;

    public RoleDto toRoleDto(){
        RoleDto roleDto = RoleDto.builder()
                .role(this.role)
                .build();
        return roleDto;
    }
}
