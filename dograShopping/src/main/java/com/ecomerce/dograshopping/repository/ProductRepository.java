package com.ecomerce.dograshopping.repository;

import com.ecomerce.dograshopping.dtos.responseDtos.ProductResponse;
import com.ecomerce.dograshopping.models.Product;
import com.ecomerce.dograshopping.thirdPartyClients.fakeStoreClient.ProductRequestDto;
import org.hibernate.annotations.Where;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    Product save(Product product);

    @Modifying
    @Query("UPDATE Product p " +
            "SET p.title = :title, p.price = :price " +
            "WHERE p.id = :uuid")
    int updateProductById(String title, int price, UUID uuid);
}
