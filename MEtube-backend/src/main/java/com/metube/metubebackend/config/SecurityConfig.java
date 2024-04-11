package com.metube.metubebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(registry->{
            registry.requestMatchers("/create/users").permitAll();
            registry.requestMatchers("/users").hasRole("ADMIN");
            registry.requestMatchers("/swagger-ui/**").hasRole("ADMIN");
            registry.anyRequest().authenticated();
        }).formLogin(formLogin -> formLogin.permitAll())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails normalUser = User.builder()
                .username("Paul")
                .password("$2a$12$FcGNAjDpSlf32j.RHUktReD07LqgqVyYoOK4CH7qUDb.Pr4DwdDha")
                .roles("USER")
                .build();
        UserDetails adminUser = User.builder()
                .username("admin")
                .password("$2a$12$ppuWZNMI8wkVPxHgTXWYTeIeQX9ZzzmhofLCY7Y2y8LGXT70UpzkK")
                .roles("ADMIN","USER")
                .build();
        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}