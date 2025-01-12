package com.todoapp.todo_api.auth;


import com.todoapp.todo_api.common.Response;
import com.todoapp.todo_api.dto.LoginDTO;
import com.todoapp.todo_api.dto.UserDTO;
import com.todoapp.todo_api.factory.ResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Response<AuthenticationResponse>> register(@Valid @RequestBody UserDTO userDTO) {
        return ResponseFactory.createResponse(authenticationService.register(userDTO), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Response<AuthenticationResponse>> authenticate(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseFactory.createResponse(authenticationService.authenticate(loginDTO), HttpStatus.OK);
    }
}
