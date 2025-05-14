package com.example.BookstoreManagement.database.repositories;

import com.example.BookstoreManagement.database.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<ReviewEntity, Integer> {
}
