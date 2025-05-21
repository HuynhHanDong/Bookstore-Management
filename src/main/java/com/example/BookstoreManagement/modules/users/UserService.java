package com.example.BookstoreManagement.modules.users;

import com.example.BookstoreManagement.database.entities.UserEntity;
import com.example.BookstoreManagement.database.repositories.UsersRepository;
import com.example.BookstoreManagement.modules.users.dto.CreateUserDTO;
import com.example.BookstoreManagement.modules.users.dto.UpdateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public void addUser(CreateUserDTO dto) {
        Optional<UserEntity> checkEmail = usersRepository.findByEmail(dto.getEmail());
        if (checkEmail.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already existed");
        UserEntity user = new UserEntity(dto);
        usersRepository.save(user);
    }

    public UserEntity getUser(Integer userId) {
        Optional<UserEntity> user = usersRepository.findByUserId(userId);
        if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return user.get();
    }

    public void updateUser(Integer userId, UpdateUserDTO dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserEntity user = getUser(userId);
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setFname(dto.getFname());
        user.setLname(dto.getLname());
        user.setDob(dto.getDob());
        usersRepository.save(user);
    }

    public List<UserEntity> listUser() {
        List<UserEntity> userList = usersRepository.findAll();
        return userList;
    }
}
