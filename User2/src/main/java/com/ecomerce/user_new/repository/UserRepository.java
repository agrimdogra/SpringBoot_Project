package com.ecomerce.user_new.repository;

import com.ecomerce.user_new.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUsersByEmail(String email);
}