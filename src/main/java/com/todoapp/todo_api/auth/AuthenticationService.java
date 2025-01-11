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

import java.util.HashMap;
import java.util.Map;

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
        UserEntity savedUser = userRepository.save(user);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", savedUser.getId());
        String jwtToken = jwtService.generateToken(extraClaims, user);

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
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", user.getId());
        String jwtToken = jwtService.generateToken(extraClaims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
