package com.example.BookstoreManagement.database.entities;

import com.example.BookstoreManagement.modules.bookCategories.dto.BookCategoryRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookCategoryEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer bookCategoryId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false, foreignKey = @ForeignKey(name = "fk_book_category_bookId"))
    private BookEntity bookId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_book_category_categoryId"))
    private CategoryEntity categoryId;

    public BookCategoryEntity(BookEntity book, CategoryEntity category) {
        this.bookId = book;
        this.categoryId = category;
    }
}
