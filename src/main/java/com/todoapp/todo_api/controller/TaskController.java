package com.todoapp.todo_api.controller;

import com.todoapp.todo_api.common.Response;
import com.todoapp.todo_api.dto.TaskDTO;
import com.todoapp.todo_api.dto.TaskResponseDTO;
import com.todoapp.todo_api.enums.TaskStatus;
import com.todoapp.todo_api.factory.ResponseFactory;
import com.todoapp.todo_api.service.TaskService;
import com.todoapp.todo_api.utils.AuthenticationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("create")
    public ResponseEntity<Response<TaskResponseDTO>> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        //get authenticated userId from jwt token to save authenticated user's list in database
        Long userId = AuthenticationUtils.getCurrentUserId(); //user is validated by jwt for per request
        return ResponseFactory.createResponse(taskService.createTask(taskDTO, userId), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<TaskResponseDTO>> fetchTaskById(@PathVariable Long id) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(taskService.fetchById(id, userId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Response<List<TaskResponseDTO>>> getAllTasks() {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(taskService.getAllTasks(userId), HttpStatus.OK);
    }

    @GetMapping("/all/{status}")
    public ResponseEntity<Response<List<TaskResponseDTO>>> getAllTasksByStatus(@PathVariable TaskStatus status) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(taskService.getAllTasksByStatus(status, userId), HttpStatus.OK);
    }

    @PutMapping("/trash/{id}")
    public ResponseEntity<Response<Void>> moveToTrash(@PathVariable Long id) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        taskService.moveToTrash(id, userId);
        return ResponseFactory.createResponse(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Response<TaskResponseDTO>> updateTaskById(@Valid @RequestBody TaskDTO taskDTO) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(taskService.updateTaskById(taskDTO, userId), HttpStatus.OK);
    }
}
