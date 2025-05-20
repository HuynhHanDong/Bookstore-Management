package com.example.BookstoreManagement.modules.bookCategories.dto;

import com.example.BookstoreManagement.database.entities.BookCategoryEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookCategoryResponseDTO {
    private Integer bookCategoryId;
    private Integer bookId;
    private String bookTitle;
    private Integer categoryId;
    private String categoryName;

    public BookCategoryResponseDTO(BookCategoryEntity entity) {
        this.bookCategoryId = entity.getBookCategoryId();
        this.bookId = entity.getBookId().getBookId();
        this.bookTitle = entity.getBookId().getTitle(); // assumes getTitle() exists
        this.categoryId = entity.getCategoryId().getCategoryId();
        this.categoryName = entity.getCategoryId().getName(); // assumes getName() exists
    }

    public static List<BookCategoryResponseDTO> fromEntities(List<BookCategoryEntity> entities) {
        List<BookCategoryResponseDTO> list = new ArrayList<>();

        for (BookCategoryEntity entity : entities) {
            BookCategoryResponseDTO dto = new BookCategoryResponseDTO(entity);
            list.add(dto);
        }

        return list;
    }
}
