package com.example.BookstoreManagement.database.entities;

import com.example.BookstoreManagement.modules.categories.dto.CategoryRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(length = 30, unique = true, nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    @CreatedDate
    private Instant createdAt;

    @Column
    @LastModifiedDate
    private Instant updatedAt;

    public CategoryEntity(CategoryRequestDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
