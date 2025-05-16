package com.example.BookstoreManagement.modules.categories.dto;

import com.example.BookstoreManagement.database.entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CategoryResponseDTO {
    private Integer categoryId;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    public static CategoryResponseDTO fromEntity(CategoryEntity entity) {
        return new CategoryResponseDTO(
                entity.getCategoryId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static List<CategoryResponseDTO> fromEntities(List<CategoryEntity> entities) {
        List<CategoryResponseDTO> list = new ArrayList<>();

        for (CategoryEntity entity : entities) {
            list.add(fromEntity(entity));
        }

        return list;
    }
}
