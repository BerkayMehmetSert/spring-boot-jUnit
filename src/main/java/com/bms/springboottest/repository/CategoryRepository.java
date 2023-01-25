package com.bms.springboottest.repository;

import com.bms.springboottest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsByName(String name);

    Optional<Category> findByName(String name);
}