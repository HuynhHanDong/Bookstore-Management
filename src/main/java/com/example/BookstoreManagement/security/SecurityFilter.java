package com.example.BookstoreManagement.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.BookstoreManagement.database.entities.UserEntity;
import com.example.BookstoreManagement.modules.auth.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        BookstoreAuthentication authentication = null;
        try {
            String accessToken = getAccessToken(request);
            Optional<UserEntity> result = authService.verifyAccessToken(accessToken);
            authentication = new BookstoreAuthentication(result.get());
        } catch (JWTVerificationException | NullPointerException e) {
            // Do nothing
        }
        context.setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(HttpServletRequest request) {
        String at = null;
        String authorization = request.getHeader("Authorization");
        String[] parts = authorization.split(" ");
        if (parts.length == 2 && parts[0].equals("Bearer")) {
            at = parts[1];
        }
        return at;
    }
}

