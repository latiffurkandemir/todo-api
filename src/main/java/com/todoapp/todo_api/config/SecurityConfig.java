package com.todoapp.todo_api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    //at the application startup, spring security will try to look for bean of type SecurityFilterChain
    //SecurityFilterChain is the bean that is responsible for configuring all HTTP Security of our application
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**",
                                "/",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**").permitAll() //no need authentication for these request
                        .anyRequest().authenticated() // any other requests need authentication
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//we are creating session as STATELESS, spring will create new session for each request
                )
                .authenticationProvider(authenticationProvider) // Custom AuthenticationProvider ekleniyor
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); //we are using jwtFilter here before UsernamePasswordAuthenticationFilter

        return httpSecurity.build();
    }
}
