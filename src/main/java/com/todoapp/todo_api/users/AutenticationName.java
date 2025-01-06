package com.todoapp.todo_api.users;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AutenticationName {

    private AutenticationName() {
        throw new UnsupportedOperationException("This utility class and cannot be instantiated");
    }


    public static String isAuthenticationName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return authentication.getName();
    }
}
