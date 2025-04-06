package com.railway.train_service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainRequestDTO {
    @NotBlank(message = "Train name is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Train name must contain only letters and spaces")
    private String trainName;

    @NotBlank(message = "Train type is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Train type must contain only letters and spaces")
    private String trainType;

    @NotBlank(message = "Source is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Source name must contain only letters and spaces")
    private String source;

    @NotBlank(message = "Destination is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Destination name must contain only letters and spaces")
    private String destination;

    @NotNull(message = "Departure time is required")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    private LocalDateTime arrivalTime;

    @AssertTrue(message = "Arrival time must be after departure time")
    public boolean isArrivalAfterDeparture() {
        if (arrivalTime == null || departureTime == null) {
            return true;
        }
        return arrivalTime.isAfter(departureTime);
    }

    @NotEmpty(message = "Running days must not be empty")
    private List<String> runningDays;

    private boolean availability;

    @NotEmpty(message = "Train classes must be provided")
    @Size(min = 1, message = "At least one train class is required")
    private List<TrainClassDTO> trainClasses;
}
