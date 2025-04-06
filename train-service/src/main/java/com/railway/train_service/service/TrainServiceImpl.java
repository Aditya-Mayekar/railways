package com.railway.train_service.service;

import com.railway.train_service.dto.ResponseStructureDTO;
import com.railway.train_service.dto.TrainDTO;
import com.railway.train_service.dto.TrainRequestDTO;
import com.railway.train_service.entity.Train;
import com.railway.train_service.repository.ITrainRepository;
import com.railway.train_service.utility.TrainMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TrainServiceImpl implements ITrainService {
    private final ITrainRepository trainRepository;

    public TrainServiceImpl(ITrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public ResponseStructureDTO<TrainDTO> addTrain(TrainRequestDTO trainRequestDTO) {
        Train train = TrainMapper.mapToEntity(trainRequestDTO);

        train.getTrainClasses().forEach(tc -> tc.setTrain(train));

        Train savedTrain = trainRepository.save(train);

        TrainDTO trainResponse = TrainMapper.mapToDTO(savedTrain);

        ResponseStructureDTO<TrainDTO> response = new ResponseStructureDTO<>(
                LocalDateTime.now(),
                "Train details added successfully!",
                trainResponse
        );

        return response;
    }
}
