package com.todoapp.todo_api.auth;


import com.todoapp.todo_api.common.Response;
import com.todoapp.todo_api.dto.LoginDTO;
import com.todoapp.todo_api.dto.UserDTO;
import com.todoapp.todo_api.factory.ResponseFactory;
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
@RequestMapping("api/auth")
@Tag(name = "Authentication Controller", description = "Handles user authentication including registration and login.")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a new user and returns a JWT token upon successful registration.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User registered successfully", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content)
    })
    public ResponseEntity<Response<AuthenticationResponse>> register(
            @Valid @RequestBody
            @Parameter(description = "Details of the user to be registered", required = true) UserDTO userDTO) {
        return ResponseFactory.createResponse(authenticationService.register(userDTO), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate a user", description = "Authenticates a user using email and password and returns a JWT token upon successful authentication.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User authenticated successfully", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication failed", content = @Content)
    })
    public ResponseEntity<Response<AuthenticationResponse>> authenticate(
            @Valid @RequestBody
            @Parameter(description = "Login credentials of the user", required = true) LoginDTO loginDTO) {
        return ResponseFactory.createResponse(authenticationService.authenticate(loginDTO), HttpStatus.OK);
    }
}
