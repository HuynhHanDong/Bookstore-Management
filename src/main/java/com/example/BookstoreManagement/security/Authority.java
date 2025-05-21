package com.example.BookstoreManagement.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    private Authority(String authority) {
        this.authority = authority;
    }

    public static Authority of(Integer role) {
        return new Authority(role.toString());
    }
}
