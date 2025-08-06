package com.example.BookstoreManagement.database.repositories;

import com.example.BookstoreManagement.database.entities.BookEntity;
import com.example.BookstoreManagement.database.entities.ReviewEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<ReviewEntity, Integer> {
    Optional<ReviewEntity> findByReviewId(Integer reviewId);
    List<ReviewEntity> findByRate(Integer rate, Sort sort);
    List<ReviewEntity> findByBookId(BookEntity bookId, Sort sort);
}
