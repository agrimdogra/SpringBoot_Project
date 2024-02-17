package com.ecomerce.user_new.service;

import com.ecomerce.user_new.dto.requestDto.RoleDto;
import com.ecomerce.user_new.dto.requestDto.UserRequestDto;
import com.ecomerce.user_new.dto.responseDto.UserResponseDto;
import com.ecomerce.user_new.exception.InvalidRoleException;
import com.ecomerce.user_new.exception.UserAlreadyExistException;
import com.ecomerce.user_new.exception.UserNotFoundException;
import com.ecomerce.user_new.model.Role;
import com.ecomerce.user_new.model.User;
import com.ecomerce.user_new.repository.RoleRepository;
import com.ecomerce.user_new.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResponseDto createUser(UserRequestDto userRequestDto){
        // check if user with same email does not exist
        if(isExistingUser(userRequestDto)){
            throw new UserAlreadyExistException("User with email "+userRequestDto.getEmail()+" already exist in DB");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(userRequestDto.getPassword());
        userRequestDto.setPassword(encodedPassword);
        User user = userRequestDto.toUser();
        User user1 = userRepository.save(user);
        return user1.toUserResponse();
    }

    public boolean isExistingUser(UserRequestDto userRequestDto) {
        Optional<User> user = getUserFromDb(userRequestDto.getEmail());
        return user.isPresent();
    }

    public Optional<User> getUserFromDb(String email) {
        return userRepository.getUsersByEmail(email);
    }


    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> result = new ArrayList<>();
        List<User> users = userRepository.findAll();
        users.forEach(user->{
            result.add(user.toUserResponse());
        });
        return result;
    }

    public UserResponseDto addRole(Long id, String role) {
        Optional<Role> role1 = getRoleByName(role);
        if(role1.isEmpty()){
            throw new InvalidRoleException("The role provided "+role+" is invalid");
        }
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new UserNotFoundException("User with id "+id+" does not exist");
        }
        User user1 = user.get();
        Role role2 = role1.get();
        Set<Role> roles = user1.getRoles();
        roles.add(role2);
        user1.setRoles(roles);
        User persistedUser =  userRepository.save(user1);
        return persistedUser.toUserResponse();
    }

    private Optional<Role> getRoleByName(String roleName) {
        return roleService.getRoleByName(roleName);
    }
}
