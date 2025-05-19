package com.example.BookstoreManagement.modules.auth;

import com.example.BookstoreManagement.database.entities.UserEntity;
import com.example.BookstoreManagement.database.repositories.UsersRepository;
import com.example.BookstoreManagement.modules.auth.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UsersRepository usersRepository;

    public boolean login(LoginDTO dto) {
        Optional<UserEntity> result = usersRepository.findByEmail(dto.getEmail());
        if (result.isEmpty()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username");
        UserEntity user = result.get();
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!dto.getPassword().equals(user.getPassword())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        return true;
    }
}
