package com.railway.train_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseStructureDTO<T> {
    private LocalDateTime timestamp;
    private String message;
    private T data;
}
