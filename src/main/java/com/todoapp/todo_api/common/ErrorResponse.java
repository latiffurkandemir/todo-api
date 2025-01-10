package com.todoapp.todo_api.common;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String apiPath;

}
