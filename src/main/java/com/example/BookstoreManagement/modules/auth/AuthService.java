package com.example.BookstoreManagement.modules.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.BookstoreManagement.database.entities.UserEntity;
import com.example.BookstoreManagement.database.repositories.UsersRepository;
import com.example.BookstoreManagement.modules.auth.dto.LoginDTO;
import com.example.BookstoreManagement.modules.auth.dto.LoginGoogleDTO;
import com.example.BookstoreManagement.modules.auth.dto.TokenResponseDTO;
import com.example.BookstoreManagement.security.BookstoreConfiguration;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(dto.getPassword(), user.getPassword())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
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
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return user;
    }

    public TokenResponseDTO loginGoogle(LoginGoogleDTO dto) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(bookstoreConfiguration.getGoogleClientId()))
                .build();
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(dto.getCredential());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            String email = payload.getEmail();
            Optional<UserEntity> result = usersRepository.findByEmail(email);
            if (result.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not register yet!");
            }
            UserEntity user = result.get();
            return new TokenResponseDTO(signAccessToken(user));
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ID Token is null");
        }
    }
}
