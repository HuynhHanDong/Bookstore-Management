package com.example.BookstoreManagement.security;

import com.example.BookstoreManagement.database.entities.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class BookstoreAuthentication implements Authentication {
    private UserEntity user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user != null ? List.of(Authority.of(user.getRole())) : List.of();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return user != null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }

    @Override
    public String getName() {
        return UserEntity.class.getName();
    }

    public BookstoreAuthentication(UserEntity user) {
        this.user = user;
    }
}

