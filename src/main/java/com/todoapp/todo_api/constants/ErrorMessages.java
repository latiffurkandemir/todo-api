package com.todoapp.todo_api.constants;

public class ErrorMessages {
    private ErrorMessages() {
    }

    public static final String USER_ALREADY_EXISTS_ERROR = "Conflict";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "User with the given email already exists.";

    public static final String USER_NOT_FOUND_ERROR = "Not Found";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found with the given email";

    public static final String AUTHENTICATION_ERROR = "Unauthorized";
    public static final String AUTHENTICATION_MESSAGE = "Invalid email or password";

    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "An unexpected error occurred. Please try again later.";
}
