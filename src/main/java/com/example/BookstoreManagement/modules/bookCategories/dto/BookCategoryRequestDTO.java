package com.example.BookstoreManagement.modules.bookCategories.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookCategoryRequestDTO {
    @NotNull
    @Min(1)
    private Integer bookId;

    @NotNull
    @Min(1)
    private Integer categoryId;
}