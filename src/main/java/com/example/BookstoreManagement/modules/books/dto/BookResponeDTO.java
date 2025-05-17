package com.example.BookstoreManagement.modules.books.dto;

import com.example.BookstoreManagement.database.entities.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class BookResponeDTO {
    private Integer bookId;
    private String title;
    private String description;
    private String isbn;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BookResponeDTO fromEntity(BookEntity entity) {
        return new BookResponeDTO(
                entity.getBookId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getIsbn(),
                entity.getAuthor(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static List<BookResponeDTO> fromEntities(List<BookEntity> entities) {
        List<BookResponeDTO> list = new ArrayList<>();

        for (BookEntity entity : entities) {
            list.add(fromEntity(entity));
        }

        return list;
    }
}