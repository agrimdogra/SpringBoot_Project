package com.ecomerce.user_new.service;

import com.ecomerce.user_new.dto.requestDto.RoleDto;
import com.ecomerce.user_new.exception.RoleAlraedyExistException;
import com.ecomerce.user_new.model.BaseModel;
import com.ecomerce.user_new.model.Role;
import com.ecomerce.user_new.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RoleService {
    RoleRepository roleRepository;
    public RoleDto createRole(RoleDto roleDto){
        if(isExistingRole(roleDto.getRole())){
            throw new RoleAlraedyExistException("Role with name "+roleDto.getRole()+ " already exist.");
        }
        Role role = roleDto.toRole();
        Role role1 = roleRepository.save(role);
        return role1.toRoleDto();
    }

        public boolean isExistingRole(String roleName) {
        Optional<Role> role = roleRepository.findByRole(roleName);
        return role.isPresent();
    }

    public Set<RoleDto> getAllRoles(){
        Set<RoleDto> roles = new HashSet<>();
        List<Role> existingRoles = roleRepository.findAll();
        existingRoles.forEach(
                role -> roles.add(role.toRoleDto())
        );

        return roles;
    }

    public Optional<Role> getRoleByName(String roleName) {
        Optional<Role> role = roleRepository.findByRole(roleName);
        return role;
    }
}
