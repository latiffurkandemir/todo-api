package com.todoapp.todo_api.auth;

import com.todoapp.todo_api.config.JwtService;
import com.todoapp.todo_api.dto.LoginDTO;
import com.todoapp.todo_api.dto.UserDTO;
import com.todoapp.todo_api.entity.UserEntity;
import com.todoapp.todo_api.exception.AuthenticationException;
import com.todoapp.todo_api.exception.UserAlreadyExistsException;
import com.todoapp.todo_api.exception.UserNotFoundException;
import com.todoapp.todo_api.mapper.UserMapper;
import com.todoapp.todo_api.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthenticationResponse register(@Valid UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        UserEntity user = UserMapper.toEntity(userDTO, encodedPassword);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(@Valid LoginDTO loginDTO) {
        UserEntity user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException());

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new AuthenticationException();
        }
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
