package com.railway.train_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainClassDTO {
    private Integer classId;
    private String classType;
    private Integer capacity;
    private Double price;
}
