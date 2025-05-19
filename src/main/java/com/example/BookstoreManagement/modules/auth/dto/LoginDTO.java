package com.example.BookstoreManagement.modules.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    @Size(max =60)
    private String email;

    @NotBlank
    private String password;
}
