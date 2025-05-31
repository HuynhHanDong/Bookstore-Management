package com.example.BookstoreManagement.modules.reviews.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalyzeReviewDTO {
    @NotNull
    private String text;
}
