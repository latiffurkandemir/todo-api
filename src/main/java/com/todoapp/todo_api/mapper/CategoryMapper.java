package com.todoapp.todo_api.mapper;

import com.todoapp.todo_api.dto.CategoryDTO;
import com.todoapp.todo_api.entity.CategoryEntity;

public class CategoryMapper {

    public static CategoryEntity toEntity(CategoryDTO categoryDTO) {

        CategoryEntity category = new CategoryEntity();

        category.setId(categoryDTO.getId());
        category.setCreatedAt(categoryDTO.getCreatedAt());
        category.setUpdatedAt(categoryDTO.getUpdatedAt());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return category;
    }

    public static CategoryDTO toDTO(CategoryEntity category) {
        if (category == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setCreatedAt(category.getCreatedAt());
        categoryDTO.setUpdatedAt(category.getUpdatedAt());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());

        return categoryDTO;
    }
}
