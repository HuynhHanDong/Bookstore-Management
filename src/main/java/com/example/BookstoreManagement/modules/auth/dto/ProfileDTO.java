package com.example.BookstoreManagement.modules.auth.dto;

import com.example.BookstoreManagement.database.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDTO {
    private Integer UserId;
    private String email;
    private Integer role;

    public static ProfileDTO fromEntity(UserEntity entity) {
        return new ProfileDTO(entity.getUserId(), entity.getEmail(), entity.getRole());
    }
}
