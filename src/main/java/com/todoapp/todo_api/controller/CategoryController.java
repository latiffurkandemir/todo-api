package com.todoapp.todo_api.controller;

import com.todoapp.todo_api.common.Response;
import com.todoapp.todo_api.dto.CategoryDTO;
import com.todoapp.todo_api.factory.ResponseFactory;
import com.todoapp.todo_api.service.CategoryService;
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

@RestController
@RequestMapping("api/category")
@Tag(name = "Category Controller", description = "Handles operations related to categories such as creating and deleting categories.")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    @Operation(summary = "Create a new category", description = "Creates a new category for the authenticated user.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category created successfully", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content)
    })
    public ResponseEntity<Response<CategoryDTO>> createCategory(
            @Valid @RequestBody
            @Parameter(description = "Details of the category to be created", required = true) CategoryDTO categoryDTO) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        return ResponseFactory.createResponse(categoryService.createTask(categoryDTO, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a category by ID", description = "Deletes a category associated with the authenticated user by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    public ResponseEntity<Response<Void>> deleteCategoryById(
            @PathVariable
            @Parameter(description = "ID of the category to be deleted", required = true) Long id) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        categoryService.deleteCategoryById(id, userId);
        return ResponseFactory.createResponse(HttpStatus.NO_CONTENT);
    }
}