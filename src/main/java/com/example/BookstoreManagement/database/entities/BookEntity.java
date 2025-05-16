package com.example.BookstoreManagement.database.entities;

import com.example.BookstoreManagement.modules.books.dto.BookRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 100)
    private String description;

    @Column(length = 13, unique = true, nullable = false)
    private String isbn;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(updatable = false, nullable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant updatedAt;

    public BookEntity(BookRequestDTO dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.isbn = dto.getIsbn();
        this.author = dto.getAuthor();
    }
}
