package com.todoapp.todo_api.service.impl;

import com.todoapp.todo_api.dto.CategoryDTO;
import com.todoapp.todo_api.entity.CategoryEntity;
import com.todoapp.todo_api.entity.UserEntity;
import com.todoapp.todo_api.exception.CategoryAlreadyExistsException;
import com.todoapp.todo_api.exception.ResourceNotFoundException;
import com.todoapp.todo_api.exception.UserNotFoundException;
import com.todoapp.todo_api.mapper.CategoryMapper;
import com.todoapp.todo_api.repository.CategoryRepository;
import com.todoapp.todo_api.repository.UserRepository;
import com.todoapp.todo_api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CategoryDTO createTask(CategoryDTO categoryDTO, Long userId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        if (categoryRepository.findByNameIgnoreCase(categoryDTO.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }

        CategoryEntity category = CategoryMapper.toEntity(categoryDTO);
        user.addCategory(category);

        CategoryEntity savedCategory = categoryRepository.save(category);

        return CategoryMapper.toDTO(savedCategory);
    }

    @Override
    public void deleteCategoryById(Long id, Long userId) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        categoryRepository.delete(category);
    }
}
