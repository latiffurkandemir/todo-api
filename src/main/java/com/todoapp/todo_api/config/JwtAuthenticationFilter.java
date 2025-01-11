package com.todoapp.todo_api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        //we will check the token first
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String userEmail;
        final Long userId;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);//if we don't have these 2 conditions, do filter for next request and response
            return;
        }
        //take token from request(authorization) header
        token = authHeader.substring(7); //(Bearer ) count is 7
        userEmail = jwtService.extractUsername(token);
        userId = jwtService.extractClaim(token, claims -> claims.get("userId", Long.class));
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {//if user is not authenticated(connected) yet
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(token, userDetails)) {
                //if user is valid, we need to update SecurityContextHolder, and send request to our DispatcherServlet
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,//when we create user we don't have credentials
                        userDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                authToken.setDetails(userId);//added user id to authentication details
                //update SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authToken);//authenticated user information
            }
        }
        filterChain.doFilter(request, response);//always call this method to pass the hand to the next filter
    }
}
