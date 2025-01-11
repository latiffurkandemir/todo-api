package com.todoapp.todo_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO extends BaseDTO {

    @NotEmpty(message = "{category.name.validation.constraints.NotEmpty.message}")
    @Size(min = 3, max = 25, message = "{category.name.validation.constraints.Size.message}")
    private String name;

    @Size(max = 100, message = "{category.description.validation.constraints.Size.message}")
    private String description;

}
