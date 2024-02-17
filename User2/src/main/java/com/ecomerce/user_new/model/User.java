package com.ecomerce.user_new.model;

import com.ecomerce.user_new.dto.requestDto.RoleDto;
import com.ecomerce.user_new.dto.responseDto.UserResponseDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel{
    private String email;
    private String password;
    @ManyToMany
    @Cascade(CascadeType.PERSIST)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user"),
               inverseJoinColumns = @JoinColumn(name = "role")
    )
    private Set<Role> roles = new HashSet<>();

    public UserResponseDto toUserResponse(){
        Set<RoleDto> roleDtos = new HashSet<>();
        roles.forEach(role->{
            roleDtos.add(role.toRoleDto());
        });

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .email(this.email)
                .roles(roleDtos)
                .build();
        return userResponseDto;
    }
}
