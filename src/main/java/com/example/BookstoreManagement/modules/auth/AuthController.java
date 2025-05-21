package com.example.BookstoreManagement.modules.auth;

import com.example.BookstoreManagement.database.entities.UserEntity;
import com.example.BookstoreManagement.modules.auth.dto.LoginDTO;
import com.example.BookstoreManagement.modules.auth.dto.ProfileDTO;
import com.example.BookstoreManagement.modules.auth.dto.TokenResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO dto) {
        TokenResponseDTO token = authService.login(dto);
        return new ResponseEntity(token, HttpStatus.OK);
    }

    @GetMapping("/profile")
    protected ResponseEntity getProfile() {
        UserEntity user = authService.getProfile();
        return new ResponseEntity(ProfileDTO.fromEntity(user), HttpStatus.OK);
    }
}
