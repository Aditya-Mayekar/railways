package com.railway.train_service.utility;

import com.railway.train_service.dto.TrainDTO;
import com.railway.train_service.dto.TrainRequestDTO;
import com.railway.train_service.entity.Train;
import com.railway.train_service.entity.TrainClass;
import com.railway.train_service.dto.TrainClassDTO;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TrainMapper {

    public static Train mapToEntity(TrainRequestDTO dto) {
        return Train.builder()
                .trainName(dto.getTrainName())
                .trainType(dto.getTrainType())
                .source(dto.getSource())
                .destination(dto.getDestination())
                .departureTime(dto.getDepartureTime())
                .arrivalTime(dto.getArrivalTime())
                .runningDays(dto.getRunningDays())
                .availability(dto.isAvailability())
                .trainClasses(dto.getTrainClasses() != null ?
                        dto.getTrainClasses().stream()
                                .map(tc -> TrainClass.builder()
                                        .classType(tc.getClassType())
                                        .capacity(tc.getCapacity())
                                        .price(tc.getPrice())
                                        .build())
                                .collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }

    public static TrainDTO mapToDTO(Train train) {
        return TrainDTO.builder()
                .trainId(train.getTrainId())
                .trainName(train.getTrainName())
                .trainType(train.getTrainType())
                .source(train.getSource())
                .destination(train.getDestination())
                .departureTime(train.getDepartureTime())
                .arrivalTime(train.getArrivalTime())
                .runningDays(train.getRunningDays())
                .availability(train.isAvailability())
                .trainClasses(train.getTrainClasses().stream()
                        .map(tc -> TrainClassDTO.builder()
                                .classId(tc.getClassId())
                                .classType(tc.getClassType())
                                .capacity(tc.getCapacity())
                                .price(tc.getPrice())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
