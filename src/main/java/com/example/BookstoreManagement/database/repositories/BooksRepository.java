package com.example.BookstoreManagement.database.repositories;

import com.example.BookstoreManagement.database.entities.BookEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<BookEntity, Integer> {
    Optional<BookEntity> findByBookId(Integer bookId);
    List<BookEntity> findByTitle(String title);
    List<BookEntity> findByAuthor(String author);
    Optional<BookEntity> findByIsbn(String isbn);
}