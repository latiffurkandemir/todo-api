package com.todoapp.todo_api.controller;

import com.todoapp.todo_api.common.Response;
import com.todoapp.todo_api.dto.TaskDTO;
import com.todoapp.todo_api.dto.TaskResponseDTO;
import com.todoapp.todo_api.enums.TaskStatus;
import com.todoapp.todo_api.factory.ResponseFactory;
import com.todoapp.todo_api.service.TaskService;
import com.todoapp.todo_api.utils.AuthenticationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
@Tag(name = "Task Controller", description = "Handles all operations related to tasks, such as creating, updating, fetching, and deleting tasks.")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("create")
    @Operation(summary = "Create a new task", description = "Creates a new task for the authenticated user.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Task created successfully", content = @Content(schema = @Schema(implementation = TaskResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<Response<TaskResponseDTO>> createTask(
            @Valid @RequestBody
            @Parameter(description = "Task data for the new task", required = true) TaskDTO taskDTO) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(taskService.createTask(taskDTO, userId), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetch a task by ID", description = "Fetches a task for the authenticated user by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task fetched successfully", content = @Content(schema = @Schema(implementation = TaskResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    public ResponseEntity<Response<TaskResponseDTO>> fetchTaskById(
            @PathVariable
            @Parameter(description = "ID of the task to fetch", required = true) Long id) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(taskService.fetchById(id, userId), HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all tasks", description = "Fetches all tasks for the authenticated user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tasks fetched successfully", content = @Content(schema = @Schema(implementation = TaskResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    public ResponseEntity<Response<List<TaskResponseDTO>>> getAllTasks() {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(taskService.getAllTasks(userId), HttpStatus.OK);
    }

    @GetMapping("/all/{status}")
    @Operation(summary = "Get tasks by status", description = "Fetches tasks for the authenticated user by their status.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tasks fetched successfully", content = @Content(schema = @Schema(implementation = TaskResponseDTO.class)))
    })
    public ResponseEntity<Response<List<TaskResponseDTO>>> getAllTasksByStatus(
            @PathVariable
            @Parameter(description = "Status of the tasks to fetch", required = true) TaskStatus status) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(taskService.getAllTasksByStatus(status, userId), HttpStatus.OK);
    }

    @GetMapping("/category/{name}")
    @Operation(summary = "Get tasks by category", description = "Fetches tasks for the authenticated user by category name.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tasks fetched successfully", content = @Content(schema = @Schema(implementation = TaskResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    public ResponseEntity<Response<List<TaskResponseDTO>>> getlAllTasksByCategory(
            @PathVariable
            @Parameter(description = "Name of the category to filter tasks", required = true) String name) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(taskService.getAllTasksByCategory(name, userId), HttpStatus.OK);
    }

    @PutMapping("/trash/{id}")
    @Operation(summary = "Move a task to trash", description = "Soft-deletes a task by moving it to the trash.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task moved to trash successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    public ResponseEntity<Response<Void>> moveToTrash(
            @PathVariable
            @Parameter(description = "ID of the task to move to trash", required = true) Long id) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        taskService.moveToTrash(id, userId);
        return ResponseFactory.createResponse(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    @Operation(summary = "Update a task", description = "Updates a task for the authenticated user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task updated successfully", content = @Content(schema = @Schema(implementation = TaskResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    public ResponseEntity<Response<TaskResponseDTO>> updateTaskById(
            @Valid @RequestBody
            @Parameter(description = "Updated task data", required = true) TaskDTO taskDTO) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(taskService.updateTaskById(taskDTO, userId), HttpStatus.OK);
    }
}
