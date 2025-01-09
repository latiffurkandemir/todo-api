package com.todoapp.todo_api.mapper;

import com.todoapp.todo_api.dto.UserDTO;
import com.todoapp.todo_api.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(UserDTO userDTO, String encodedPassword) {
        if (userDTO == null) {
            return null;
        }

        UserEntity user = new UserEntity();

        user.setId(userDTO.getId());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encodedPassword);

        return user;
    }

    public static UserDTO toDTO(UserEntity user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());

        return userDTO;

    }

}
