package com.example.BookstoreManagement.modules.users.dto;

import com.example.BookstoreManagement.database.entities.UserEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserResponeDTO {
    private Integer userId;
    private String email;
    private String password;
    private String fname;
    private String lname;
    private LocalDate dob;
    private Integer role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserResponeDTO fromEntity(UserEntity entity) {
        return new UserResponeDTO(
                entity.getUserId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getFname(),
                entity.getLname(),
                entity.getDob(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static List<UserResponeDTO> fromEntities(List<UserEntity> entities) {
        List<UserResponeDTO> list = new ArrayList<>();

        for (UserEntity entity : entities) {
            list.add(fromEntity(entity));
        }

        return list;
    }
}
