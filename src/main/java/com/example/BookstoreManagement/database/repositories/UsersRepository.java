package com.example.BookstoreManagement.database.repositories;

import com.example.BookstoreManagement.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserId(Integer UserId);
    Optional<UserEntity> findByEmail(String email);
}
