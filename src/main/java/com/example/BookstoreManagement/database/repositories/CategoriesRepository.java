package com.example.BookstoreManagement.database.repositories;

import com.example.BookstoreManagement.database.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<CategoryEntity, Integer> {
}
