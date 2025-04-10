package com.railway.train_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainDTO {
    private Integer trainId;
    private String trainNumber;
    private String trainName;
    private String trainType;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private List<String> runningDays;
    private boolean availability;

    private List<TrainClassDTO> trainClasses;
}
