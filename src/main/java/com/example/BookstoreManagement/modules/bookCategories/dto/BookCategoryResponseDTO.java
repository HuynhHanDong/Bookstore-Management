package com.example.BookstoreManagement.modules.bookCategories.dto;

import com.example.BookstoreManagement.database.entities.BookCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class BookCategoryResponseDTO {
    private Integer bookCategoryId;
    private Integer bookId;
    private String bookTitle;
    private Integer categoryId;
    private String categoryName;

    public static BookCategoryResponseDTO fromEntity(BookCategoryEntity entity) {
        return new BookCategoryResponseDTO(
                entity.getBookCategoryId(),
                entity.getBookId().getBookId(),
                entity.getBookId().getTitle(),
                entity.getCategoryId().getCategoryId(),
                entity.getCategoryId().getName()
        );
    }

    public static List<BookCategoryResponseDTO> fromEntities(List<BookCategoryEntity> entities) {
        List<BookCategoryResponseDTO> list = new ArrayList<>();

        for (BookCategoryEntity entity : entities) {
            list.add(fromEntity(entity));
        }

        return list;
    }
}
