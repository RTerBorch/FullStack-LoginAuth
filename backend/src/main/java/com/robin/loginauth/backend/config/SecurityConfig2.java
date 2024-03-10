package com.robin.loginauth.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
@Configuration
@EnableWebSecurity
public class SecurityConfig2 {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/messages").permitAll()
                .anyRequest().authenticated();
        // Add any additional security configurations here

        return http.build();
    }
}

 */
