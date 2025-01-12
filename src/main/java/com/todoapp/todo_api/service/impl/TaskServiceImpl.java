package com.todoapp.todo_api.service.impl;

import com.todoapp.todo_api.dto.TaskDTO;
import com.todoapp.todo_api.dto.TaskResponseDTO;
import com.todoapp.todo_api.entity.CategoryEntity;
import com.todoapp.todo_api.entity.TaskEntity;
import com.todoapp.todo_api.entity.UserEntity;
import com.todoapp.todo_api.enums.TaskStatus;
import com.todoapp.todo_api.exception.CategoryNotFoundException;
import com.todoapp.todo_api.exception.UserNotFoundException;
import com.todoapp.todo_api.mapper.TaskMapper;
import com.todoapp.todo_api.mapper.TaskResponseMapper;
import com.todoapp.todo_api.repository.CategoryRepository;
import com.todoapp.todo_api.repository.TaskRepository;
import com.todoapp.todo_api.repository.UserRepository;
import com.todoapp.todo_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
//because we set a relation between tasks, category and user here. If there is a problem at saving, we can rollback relations
    public TaskResponseDTO createTask(TaskDTO taskDTO, Long userId) {
        TaskEntity task = TaskMapper.toEntity(taskDTO);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        CategoryEntity category = taskDTO.getCategoryId() != null
                ? categoryRepository.findById(taskDTO.getCategoryId()).orElse(null)
                : null;// If categoryId is null or category doesn't exist, task will still be created for the user. because category is not a must

        user.addTask(task);
        if (category != null) {
            category.addTask(task);
        }

        TaskEntity savedTask = taskRepository.save(task);
        return TaskResponseMapper.toResponseDTO(savedTask);
    }

    @Override
    public TaskResponseDTO fetchById(Long id, Long userId) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found with the given ID."));
        if (!task.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("User does not have permission to access this task.");
        }

        return TaskResponseMapper.toResponseDTO(task);
    }

    @Override
    public List<TaskResponseDTO> getAllTasks(Long userId) {
        List<TaskEntity> taskList = taskRepository.findByUserId(userId);
        return taskList.stream()
                .map(TaskResponseMapper::toResponseDTO)
                .toList();
    }


    @Override
    public List<TaskResponseDTO> getAllTasksByStatus(TaskStatus status, Long userId) {
        List<TaskEntity> taskList = taskRepository.findByUserId(userId);
        List<TaskEntity> responseList = new ArrayList<>();
        for (TaskEntity task : taskList) {
            if (task.getStatus() == status) {
                responseList.add(task);
            }
        }
        return responseList.stream()
                .map(TaskResponseMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<TaskResponseDTO> getAllTasksByCategory(String name, Long userId) {
        CategoryEntity categoryEntity = categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new CategoryNotFoundException());

        if (!categoryEntity.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("User does not have permission to access this category.");
        }
        List<TaskEntity> taskEntityList = categoryEntity.getTaskEntityList();

        return taskEntityList.stream()
                .map(TaskResponseMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public void moveToTrash(Long taskId, Long userId) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("This task doesn't exists"));

        if (!task.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("User does not have permission to access this task.");
        }
        task.setDeleted(true);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public TaskResponseDTO updateTaskById(TaskDTO taskDTO, Long userId) {


        TaskEntity task = taskRepository.findById(taskDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("This task doesn't exists"));

        if (!task.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("User does not have permission to access this task.");
        }

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setDueDate(taskDTO.getDueDate());
        task.setStatus(taskDTO.getStatus());
        TaskEntity savedEntity = taskRepository.save(task);
        return TaskResponseMapper.toResponseDTO(savedEntity);
    }


}

