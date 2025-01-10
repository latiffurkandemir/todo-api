package com.todoapp.todo_api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private LocalDateTime timestamp;
    private int status;
    private T data;
    private String message;
}
