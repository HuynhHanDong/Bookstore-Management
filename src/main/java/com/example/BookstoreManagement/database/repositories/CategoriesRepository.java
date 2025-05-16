package com.example.BookstoreManagement.database.repositories;

import com.example.BookstoreManagement.database.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findByCategoryId(Integer categoryId);
    Optional<CategoryEntity> findByName(String name);
}
