package com.bank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;


@Configuration
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for APIs (enable as needed)
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll() // Allow all requests (customize as needed)
            );
        return http.build();
    }
}
