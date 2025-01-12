package com.todoapp.todo_api.dto;

import com.todoapp.todo_api.entity.CategoryEntity;
import com.todoapp.todo_api.enums.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO extends BaseDTO{

    @NotEmpty(message = "{task.title.validation.constraints.NotEmpty.message}")
    @Size(min = 3, max = 100, message = "{task.title.validation.constraints.Size.message}")
    private String title;

    @Size(max = 500, message = "{task.description.validation.constraints.Size.message}")
    private String description;

    @Future(message = "{task.dueDate.validation.constraints.Future.message}")
    private LocalDateTime dueDate;

//    @NotNull(message = "{task.status.validation.constraints.NotNull.message}")
    private TaskStatus status;

    private Long categoryId;

    private boolean isDeleted;
}
