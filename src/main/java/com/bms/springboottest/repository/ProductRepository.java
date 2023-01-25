package com.bms.springboottest.repository;

import com.bms.springboottest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByCategory_Id(String id);
    boolean existsByName(String name);

    Optional<Product> findByName(String name);
}