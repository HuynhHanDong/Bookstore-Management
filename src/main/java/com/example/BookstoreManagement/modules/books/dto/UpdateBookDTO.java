package com.example.BookstoreManagement.modules.books.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateBookDTO {
    private Integer bookId;

    @NotBlank
    @Size(max = 50)
    private String title;

    private String description;

    @NotBlank
    @Size(min = 1, max = 13)
    private String isbn;

    @NotBlank
    @Size(max = 100)
    private String author;
}
