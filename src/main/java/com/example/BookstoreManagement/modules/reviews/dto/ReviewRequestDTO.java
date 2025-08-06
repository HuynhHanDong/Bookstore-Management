package com.example.BookstoreManagement.modules.reviews.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequestDTO {
    @NotNull
    @Min(1)
    @Max(10)
    private Integer rate;

    @NotNull
    @Min(1)
    private Integer bookId;

    @NotNull
    @Min(1)
    private Integer userId;

    @NotNull
    private String content;
}
