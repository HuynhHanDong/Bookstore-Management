package com.example.BookstoreManagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilter securityFilter() {
        return new SecurityFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Admin only
                        .requestMatchers(HttpMethod.POST, "/books/", "/categories/", "/book-categories/", "/reviews/analyze")
                        .hasAnyAuthority(Authority.of(1).getAuthority())
                        .requestMatchers(HttpMethod.PUT, "/books/*", "/categories/*")
                        .hasAnyAuthority(Authority.of(1).getAuthority())
                        .requestMatchers(HttpMethod.DELETE, "/books/*", "/categories/*", "/book-categories/*")
                        .hasAnyAuthority(Authority.of(1).getAuthority())
                        .requestMatchers(HttpMethod.GET, "/users/", "/book-categories/")
                        .hasAnyAuthority(Authority.of(1).getAuthority())
                        // Both admin and registered users
                        .requestMatchers(HttpMethod.GET, "/users/*", "/auth/profile")
                        .authenticated()
                        .requestMatchers(HttpMethod.PUT, "/users/*")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/reviews/")
                        .authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/reviews/*")
                        .authenticated()
                        // Public endpoints
                        .anyRequest()
                        .permitAll())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .addFilterBefore(securityFilter(), AuthorizationFilter.class);
        return http.build();
    }
}
