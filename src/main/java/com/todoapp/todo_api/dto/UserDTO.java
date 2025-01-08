package com.todoapp.todo_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO extends BaseDTO {

    @NotEmpty(message = "{user.username.validation.constraints.NotEmpty.message}")
    @Size(min = 5, max = 15, message = "{user.username.validation.constraints.Size.message}")
    private String username;

    @NotEmpty(message = "{user.email.validation.constraints.NotEmpty.message}")
    @Email(message = "{user.email.validation.constraints.Email.message}")
    @Size(min = 5, max = 50, message = "{user.email.validation.constraints.Size.message}")
    private String email;

    @NotEmpty(message = "{user.password.validation.constraints.NotEmpty.message}")
    @Size(min = 8, max = 20, message = "{user.password.validation.constraints.Size.message}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "{user.password.validation.constraints.Pattern.message}")
    private String password;


}
