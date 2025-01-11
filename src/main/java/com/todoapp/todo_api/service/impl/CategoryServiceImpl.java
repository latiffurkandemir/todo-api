package com.todoapp.todo_api.service.impl;

import com.todoapp.todo_api.repository.CategoryRepository;
import com.todoapp.todo_api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
}
