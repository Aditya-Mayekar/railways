package com.railway.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseStructureDTO<T> {
    private LocalDateTime timeStamp;
    private String message;
    private T data;
}
