package com.todoapp.todo_api.exception;

import com.todoapp.todo_api.common.ErrorResponse;
import com.todoapp.todo_api.constants.ErrorMessages;
import com.todoapp.todo_api.factory.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();
        return ResponseFactory.createErrorResponse(
                ErrorMessages.INTERNAL_SERVER_ERROR,
                ErrorMessages.INTERNAL_SERVER_ERROR_MESSAGE,
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String message = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + ", " + msg2)
                .orElse("Validation failed");
        return ResponseFactory.createErrorResponse(
                "Bad Request",
                message,
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException exception, HttpServletRequest request) {
        return ResponseFactory.createErrorResponse(
                ErrorMessages.AUTHENTICATION_ERROR,
                ErrorMessages.AUTHENTICATION_MESSAGE,
                request.getRequestURI(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request) {
        return ResponseFactory.createErrorResponse(
                ErrorMessages.USER_NOT_FOUND_ERROR,
                ErrorMessages.USER_NOT_FOUND_MESSAGE,
                request.getRequestURI(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception, HttpServletRequest request) {
        return ResponseFactory.createErrorResponse(
                ErrorMessages.USER_ALREADY_EXISTS_ERROR,
                ErrorMessages.USER_ALREADY_EXISTS_MESSAGE,
                request.getRequestURI(),
                HttpStatus.CONFLICT
        );
    }


}
