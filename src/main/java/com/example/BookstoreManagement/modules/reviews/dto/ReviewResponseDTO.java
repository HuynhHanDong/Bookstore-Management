package com.example.BookstoreManagement.modules.reviews.dto;

import com.example.BookstoreManagement.database.entities.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ReviewResponseDTO {
    private Integer reviewId;
    private Integer rate;
    private Integer bookId;
    private String bookTitle;
    private Integer userId;
    private String userEmail;
    private String content;
    private LocalDateTime createdAt;

    public static ReviewResponseDTO fromEntity(ReviewEntity entity) {
        return new ReviewResponseDTO(
                entity.getReviewId(),
                entity.getRate(),
                entity.getBookId().getBookId(),
                entity.getBookId().getTitle(),
                entity.getUserId().getUserId(),
                entity.getUserId().getEmail(),
                entity.getContent(),
                entity.getCreatedAt()
        );
    }

    public static List<ReviewResponseDTO> fromEntities(List<ReviewEntity> entities) {
        List<ReviewResponseDTO> list = new ArrayList<>();

        for (ReviewEntity entity : entities) {
            list.add(fromEntity(entity));
        }

        return list;
    }
}
