package com.railway.train_service.service;

import com.railway.train_service.dto.*;
import com.railway.train_service.entity.Train;
import com.railway.train_service.entity.TrainClass;
import com.railway.train_service.repository.ITrainRepository;
import com.railway.train_service.utility.TrainMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements ITrainService {
    private final ITrainRepository trainRepository;

    public TrainServiceImpl(ITrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public ResponseStructureDTO<TrainDTO> addTrain(TrainRequestDTO trainRequestDTO) {
        Optional<Train> existingTrain = trainRepository.findByTrainNumber(trainRequestDTO.getTrainNumber());

        if(existingTrain.isPresent()){
            throw new IllegalArgumentException("Train with number " + trainRequestDTO.getTrainNumber() + " already exists!");
        }

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

    @Override
    public ResponseStructureDTO<List<TrainDTO>> getAllTrains() {
        List<Train> allTrains = trainRepository.findAll();

        List<TrainDTO> trainDTO = allTrains.stream().map(TrainMapper::mapToDTO).toList();

        ResponseStructureDTO<List<TrainDTO>> response = new ResponseStructureDTO<>(
                LocalDateTime.now(),
                "All trains fetched successfully!",
                trainDTO
        );
        return response;
    }

    @Override
    public ResponseStructureDTO<List<TrainDTO>> getAllTrainsByName(String trainName) {
        List<Train> allTrains = trainRepository.findAllByTrainName(trainName);

        if(allTrains.isEmpty()){
            throw new IllegalArgumentException("Trains with name " + trainName + " does not exist!");
        }

        List<TrainDTO> trainResponse = allTrains.stream().map(TrainMapper::mapToDTO).toList();

        ResponseStructureDTO<List<TrainDTO>> response = new ResponseStructureDTO<>(
                LocalDateTime.now(),
                "Train details fetched successfully!",
                trainResponse
        );
        return response;
    }

    @Override
    public ResponseStructureDTO<TrainDTO> updateTrain(String trainNumber, UpdatedTrainDTO updatedTrainDTO){
        Train existingTrain = trainRepository.findByTrainNumber(trainNumber)
                .orElseThrow(() -> new IllegalArgumentException("Train with number " + trainNumber + " not found"));

        existingTrain.setTrainName(updatedTrainDTO.getTrainName());
        existingTrain.setTrainType(updatedTrainDTO.getTrainType());
        existingTrain.setSource(updatedTrainDTO.getSource());
        existingTrain.setDestination(updatedTrainDTO.getDestination());
        existingTrain.setDepartureTime(updatedTrainDTO.getDepartureTime());
        existingTrain.setArrivalTime(updatedTrainDTO.getArrivalTime());
        existingTrain.setRunningDays(updatedTrainDTO.getRunningDays());
        existingTrain.setAvailability(updatedTrainDTO.isAvailability());

        existingTrain.getTrainClasses().clear();

        List<TrainClass> updatedTrainClasses = updatedTrainDTO.getTrainClasses().stream()
                .map(dto -> TrainClass.builder()
                        .classType(dto.getClassType())
                        .capacity(dto.getCapacity())
                        .price(dto.getPrice())
                        .train(existingTrain)
                        .build())
                .toList();

        existingTrain.getTrainClasses().addAll(updatedTrainClasses);

        Train savedTrain = trainRepository.save(existingTrain);

        TrainDTO updatedTrainResponse = TrainMapper.mapToDTO(savedTrain);
        ResponseStructureDTO<TrainDTO> response = new ResponseStructureDTO<>(
                LocalDateTime.now(),
                "Train details updated successfully!",
                updatedTrainResponse);
        return response;
    }

    @Transactional
    @Override
    public ResponseStructureDTO<String> deleteTrainByNumber(String trainNumber) {
        Optional<Train> train = trainRepository.findByTrainNumber(trainNumber);

        if(train.isEmpty()){
            throw new IllegalArgumentException("Train with number " + trainNumber + " does not exist!");
        }

        trainRepository.delete(train.get());
        ResponseStructureDTO<String> response = new ResponseStructureDTO<>(
                LocalDateTime.now(),
                "Train details deleted successfully!",
                trainNumber.toString()
        );
        return response;
    }
}
