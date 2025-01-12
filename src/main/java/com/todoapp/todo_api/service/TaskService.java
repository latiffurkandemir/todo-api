package com.todoapp.todo_api.service;

import com.todoapp.todo_api.dto.TaskDTO;
import com.todoapp.todo_api.dto.TaskResponseDTO;
import com.todoapp.todo_api.enums.TaskStatus;
import jakarta.validation.Valid;

import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(@Valid TaskDTO taskDTO, Long userId);

    List<TaskResponseDTO> getAllTasks(Long userId);

    List<TaskResponseDTO> getAllTasksByStatus(TaskStatus status, Long userId);

    void moveToTrash(Long taskId, Long userId);

    TaskResponseDTO updateTaskById(@Valid TaskDTO taskDTO, Long userId);

    TaskResponseDTO fetchById(Long id, Long userId);
}
