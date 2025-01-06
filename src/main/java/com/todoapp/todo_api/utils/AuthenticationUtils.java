package com.todoapp.todo_api.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {

    private AuthenticationUtils() {
        throw new UnsupportedOperationException("This is a utility class cannot be instantiated");
    }


    public static String isAuthenticationName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return authentication.getName();
    }
}
