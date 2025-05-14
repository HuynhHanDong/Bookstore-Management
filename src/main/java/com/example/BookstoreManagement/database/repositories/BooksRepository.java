package com.example.BookstoreManagement.database.repositories;

import com.example.BookstoreManagement.database.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<BookEntity, Integer> {
}
