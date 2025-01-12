package com.todoapp.todo_api.service;

import com.todoapp.todo_api.dto.CategoryDTO;
import jakarta.validation.Valid;

public interface CategoryService {
    CategoryDTO createTask(@Valid CategoryDTO categoryDTO, Long userId);

    void deleteCategoryById(Long id, Long userId);
}
