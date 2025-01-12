package com.todoapp.todo_api.mapper;

import com.todoapp.todo_api.dto.TaskDTO;
import com.todoapp.todo_api.entity.TaskEntity;

public class TaskMapper {

    public static TaskEntity toEntity(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        }

        TaskEntity task = new TaskEntity();
        task.setId(taskDTO.getId());
        task.setCreatedAt(taskDTO.getCreatedAt());
        task.setUpdatedAt(taskDTO.getUpdatedAt());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setStatus(taskDTO.getStatus());
        task.setDeleted(taskDTO.isDeleted());

        return task;
    }
    //for response but we craated TaskResponseDTO that's why we dont need this(i am not sure)
    public static TaskDTO toDTO(TaskEntity task) {

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setUpdatedAt(task.getUpdatedAt());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setDeleted(task.isDeleted());

        taskDTO.setCategoryId(task.getCategory() != null ? task.getCategory().getId() : null);

        return taskDTO;
    }
}
