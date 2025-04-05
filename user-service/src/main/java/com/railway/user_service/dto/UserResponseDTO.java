package com.railway.user_service.dto;

import com.railway.user_service.entity.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private UUID userId;
    private String userName;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}
