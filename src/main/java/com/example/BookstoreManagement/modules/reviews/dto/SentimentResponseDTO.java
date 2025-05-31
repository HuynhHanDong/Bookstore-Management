package com.example.BookstoreManagement.modules.reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SentimentResponseDTO {
    private String sentiment;
    private double polarity;
    private String reason;
}
