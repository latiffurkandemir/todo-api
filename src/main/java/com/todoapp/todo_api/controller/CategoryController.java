package com.todoapp.todo_api.controller;

import com.todoapp.todo_api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
}
