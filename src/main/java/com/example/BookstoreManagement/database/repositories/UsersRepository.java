package com.example.BookstoreManagement.database.repositories;

import com.example.BookstoreManagement.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
}
