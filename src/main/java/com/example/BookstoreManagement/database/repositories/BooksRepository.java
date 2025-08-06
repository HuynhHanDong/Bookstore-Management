package com.example.BookstoreManagement.database.repositories;

import com.example.BookstoreManagement.database.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<BookEntity, Integer> {
    Optional<BookEntity> findByBookId(Integer bookId);
    List<BookEntity> findByTitleContainingIgnoreCase(String title);
    List<BookEntity> findByAuthorContainingIgnoreCase(String author);
    Optional<BookEntity> findByIsbn(String isbn);
}