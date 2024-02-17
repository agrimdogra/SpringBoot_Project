package com.ecomerce.user_new.dto.requestDto;

import com.ecomerce.user_new.model.Role;
import com.ecomerce.user_new.model.User;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String email;
    private String password;
    private Set<RoleDto> roles = new HashSet<>();

    public User toUser(){
        Set<Role> roles1 = new HashSet<>();
        roles.forEach(r->{
            roles1.add(r.toRole());
        });

        User user = User.builder()
                .email(this.email)
                .password(this.password)
                .roles(roles1)
                .build();
        return user;
    }
}
