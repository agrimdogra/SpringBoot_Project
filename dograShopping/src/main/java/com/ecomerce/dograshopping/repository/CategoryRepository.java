package com.ecomerce.dograshopping.repository;

import com.ecomerce.dograshopping.models.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> getCategoriesByName(String name);
    Optional<Category> getCategoryById(UUID id);
}