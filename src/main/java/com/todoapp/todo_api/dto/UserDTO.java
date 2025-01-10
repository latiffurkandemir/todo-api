package com.todoapp.todo_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.todoapp.todo_api.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO extends BaseDTO {

    @NotEmpty(message = "{user.firstName.validation.constraints.NotEmpty.message}")
    @Size(min = 2, max = 50, message = "{user.firstName.validation.constraints.Size.message}")
    private String firstName;

    @NotEmpty(message = "{user.lastName.validation.constraints.NotEmpty.message}")
    @Size(min = 2, max = 50, message = "{user.lastName.validation.constraints.Size.message}")
    private String lastName;

    @NotEmpty(message = "{user.email.validation.constraints.NotEmpty.message}")
    @Email(message = "{user.email.validation.constraints.Email.message}")
    @Size(min = 5, max = 50, message = "{user.email.validation.constraints.Size.message}")
    private String email;

    @NotEmpty(message = "{user.password.validation.constraints.NotEmpty.message}")
    @Size(min = 8, max = 20, message = "{user.password.validation.constraints.Size.message}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "{user.password.validation.constraints.Pattern.message}")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

//    @NotNull(message = "{user.role.validation.constraints.NotNull.message}")
    private Role role;

}
