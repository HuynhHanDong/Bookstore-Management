package com.example.BookstoreManagement.database.entities;

import com.example.BookstoreManagement.modules.books.dto.CreateBookDTO;
import com.example.BookstoreManagement.modules.books.dto.UpdateBookDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column
    private String description;

    @Column(length = 13, unique = true, nullable = false)
    private String isbn;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(nullable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant updatedAt;

    public BookEntity(CreateBookDTO dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.isbn = dto.getIsbn();
        this.author = dto.getAuthor();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public BookEntity(UpdateBookDTO dto) {
        this.bookId = dto.getBookId();
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.isbn = dto.getIsbn();
        this.author = dto.getAuthor();
    }
}
