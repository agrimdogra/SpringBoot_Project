package com.ecomerce.user_new.repository;

import com.ecomerce.user_new.model.Session;
import com.ecomerce.user_new.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> getSessionsByUser(User user);
    Optional<Session> getSessionsByToken(String token);
}