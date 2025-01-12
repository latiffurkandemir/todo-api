package com.todoapp.todo_api.service.impl;

import com.todoapp.todo_api.repository.UserRepository;
import com.todoapp.todo_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


}
