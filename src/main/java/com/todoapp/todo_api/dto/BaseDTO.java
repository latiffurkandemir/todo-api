package com.todoapp.todo_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
