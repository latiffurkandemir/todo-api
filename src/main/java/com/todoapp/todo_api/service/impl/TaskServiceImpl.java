package com.todoapp.todo_api.service.impl;

import com.todoapp.todo_api.repository.TaskRepository;
import com.todoapp.todo_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
}
