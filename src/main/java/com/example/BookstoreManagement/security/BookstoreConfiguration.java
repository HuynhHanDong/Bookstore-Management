package com.example.BookstoreManagement.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BookstoreConfiguration {
    @Value("${BookstoreManagement.jwt.secret}")
    private String jwtSecret;

    @Value("${BookstoreManagement.jwt.issuer}")
    private String jwtIssuer;
}
