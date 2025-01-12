package com.todoapp.todo_api.controller;

import com.todoapp.todo_api.common.Response;
import com.todoapp.todo_api.dto.CategoryDTO;
import com.todoapp.todo_api.factory.ResponseFactory;
import com.todoapp.todo_api.service.CategoryService;
import com.todoapp.todo_api.utils.AuthenticationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Response<CategoryDTO>> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(categoryService.createTask(categoryDTO, userId), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<Void>> deleteCategoryById(@PathVariable Long id) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        categoryService.deleteCategoryById(id, userId);
        return ResponseFactory.createResponse(HttpStatus.NO_CONTENT);
    }

}
