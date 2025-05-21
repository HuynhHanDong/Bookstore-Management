package com.example.BookstoreManagement.database.entities;

import com.example.BookstoreManagement.modules.users.dto.CreateUserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 60, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String fname;

    @Column(length = 30, nullable = false)
    private String lname;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer role;

    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public UserEntity(CreateUserDTO dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.email = dto.getEmail();
        this.password = encoder.encode(dto.getPassword());
        this.fname = dto.getFname();
        this.lname = dto.getLname();
        this.dob = dto.getDob();
        this.role = 0;
    }
}
