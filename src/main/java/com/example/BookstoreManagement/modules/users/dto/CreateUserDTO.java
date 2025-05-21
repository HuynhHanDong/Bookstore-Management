package com.example.BookstoreManagement.modules.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserDTO {
    @NotBlank
    @Size(max = 60)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Size(max = 30)
    private String fname;

    @NotBlank
    @Size(max = 30)
    private String lname;

    @NotNull
    private LocalDate dob;
}
