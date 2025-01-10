package com.todoapp.todo_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    @NotEmpty(message = "{user.email.validation.constraints.NotEmpty.message}")
    @Email(message = "{user.email.validation.constraints.Email.message}")
    @Size(min = 5, max = 50, message = "{user.email.validation.constraints.Size.message}")
    private String email;

    @NotEmpty(message = "{user.password.validation.constraints.NotEmpty.message}")
    @Size(min = 8, max = 20, message = "{user.password.validation.constraints.Size.message}")
    private String password;
}
