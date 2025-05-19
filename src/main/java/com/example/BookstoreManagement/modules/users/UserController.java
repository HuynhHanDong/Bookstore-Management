package com.example.BookstoreManagement.modules.users;

import com.example.BookstoreManagement.database.entities.UserEntity;
import com.example.BookstoreManagement.modules.users.dto.CreateUserDTO;
import com.example.BookstoreManagement.modules.users.dto.UpdateUserDTO;
import com.example.BookstoreManagement.modules.users.dto.UserResponeDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity addUser(@RequestBody @Valid CreateUserDTO dto) {
        userService.addUser(dto);
        return new ResponseEntity("User added", HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId, @RequestBody @Valid UpdateUserDTO dto) {
        userService.updateUser(userId, dto);
        return new ResponseEntity("User updated", HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUser(@PathVariable Integer userId) {
        UserEntity user = userService.getUser(userId);
        return new ResponseEntity(UserResponeDTO.fromEntity(user), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity listUser() {
        List<UserEntity> userList = userService.listUser();
        return new ResponseEntity(UserResponeDTO.fromEntities(userList), HttpStatus.OK);
    }
}
