package com.example.BookstoreManagement.database.repositories;

import com.example.BookstoreManagement.database.entities.BookCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoriesRepository extends JpaRepository<BookCategoryEntity, Integer> {
}
