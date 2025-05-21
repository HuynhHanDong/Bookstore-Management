package com.example.BookstoreManagement.modules.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.BookstoreManagement.database.entities.UserEntity;
import com.example.BookstoreManagement.database.repositories.UsersRepository;
import com.example.BookstoreManagement.modules.auth.dto.LoginDTO;
import com.example.BookstoreManagement.modules.auth.dto.TokenResponseDTO;
import com.example.BookstoreManagement.security.BookstoreConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BookstoreConfiguration bookstoreConfiguration;

    public TokenResponseDTO login(LoginDTO dto) {
        Optional<UserEntity> result = usersRepository.findByEmail(dto.getEmail());
        if (result.isEmpty()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong email");
        UserEntity user = result.get();
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!dto.getPassword().equals(user.getPassword())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        return new TokenResponseDTO(signAccessToken(user));
    }

    private String signAccessToken(UserEntity user) {
        Instant now = Instant.now();
        Algorithm algorithm = Algorithm.HMAC256(bookstoreConfiguration.getJwtSecret());
        return JWT.create()
                .withSubject(user.getUserId().toString())
                .withIssuedAt(now)
                .withExpiresAt(now.plus(1, ChronoUnit.DAYS))
                .sign(algorithm);
    }

    public Optional<UserEntity> verifyAccessToken(String accessToken) {
        Algorithm algorithm = Algorithm.HMAC256(bookstoreConfiguration.getJwtSecret());
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(accessToken);
        Integer userId = Integer.valueOf(decodedJWT.getSubject());
        return usersRepository.findByUserId(userId);
    }

    public UserEntity getProfile() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserEntity user = (UserEntity)  authentication.getPrincipal();
        return user;
    }
}
