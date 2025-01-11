package com.todoapp.todo_api.controller;

import com.todoapp.todo_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
}
