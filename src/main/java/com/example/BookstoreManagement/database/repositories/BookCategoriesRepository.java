package com.example.BookstoreManagement.database.repositories;

import com.example.BookstoreManagement.database.entities.BookCategoryEntity;
import com.example.BookstoreManagement.database.entities.BookEntity;
import com.example.BookstoreManagement.database.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCategoriesRepository extends JpaRepository<BookCategoryEntity, Integer> {
    Optional<BookCategoryEntity> findByBookCategoryId(Integer bookCategoryId);
    List<BookCategoryEntity> findByBookId(BookEntity bookId);
    List<BookCategoryEntity> findByCategoryId(CategoryEntity categoryId);
}
