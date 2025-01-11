package com.todoapp.todo_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todoapp.todo_api.enums.TaskStatus;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponseDTO extends BaseDTO {

    private String title;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

    private TaskStatus status;

    private CategoryDTO category;

    private boolean isDeleted;
}
