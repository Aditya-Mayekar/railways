package com.railway.user_service.utility;

import com.railway.user_service.dto.UserRequestDTO;
import com.railway.user_service.dto.UserResponseDTO;
import com.railway.user_service.entity.User;

public class UserMapper {
    private UserMapper(){}

    public static User mapToEntity(UserRequestDTO userRequestDTO){
        return User.builder()
                .userName(userRequestDTO.getUserName())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .build();
    }

    public static UserResponseDTO mapToDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
