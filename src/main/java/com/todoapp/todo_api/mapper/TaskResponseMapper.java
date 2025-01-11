package com.todoapp.todo_api.mapper;

import com.todoapp.todo_api.dto.TaskResponseDTO;
import com.todoapp.todo_api.entity.TaskEntity;

public class TaskResponseMapper {

    public static TaskResponseDTO toResponseDTO(TaskEntity task) {
        if (task == null) {
            return null;
        }
        CategoryMapper categoryMapper = new CategoryMapper();

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setDueDate(task.getDueDate());
        taskResponseDTO.setStatus(task.getStatus());
        taskResponseDTO.setCategory(categoryMapper.toDTO(task.getCategory()));
        taskResponseDTO.setDeleted(task.isDeleted());
        return taskResponseDTO;
    }

//    private static CategoryDTO toCategoryDTO(CategoryEntity categoryEntity) {
//        if (categoryEntity == null) {
//            return null;
//        }
//
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setId(categoryEntity.getId());
//        categoryDTO.setName(categoryEntity.getName());
//        categoryDTO.setDescription(categoryEntity.getDescription());
//        return categoryDTO;
//    }
}

