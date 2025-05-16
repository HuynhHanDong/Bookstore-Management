package com.example.BookstoreManagement.modules.categories.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank
    @Size(max = 30)
    private String name;
    private String description;
}
