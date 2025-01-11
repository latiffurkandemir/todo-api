package com.todoapp.todo_api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationUtils {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationUtils.class);

    private AuthenticationUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            logger.warn("No authenticated user found");
            return null;
        }
        return authentication.getName();
    }

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Unauthorized access");
        }
        return (Long) authentication.getDetails();
    }
}

