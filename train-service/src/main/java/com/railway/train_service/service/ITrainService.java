package com.railway.train_service.service;

import com.railway.train_service.dto.ResponseStructureDTO;
import com.railway.train_service.dto.TrainDTO;
import com.railway.train_service.dto.TrainRequestDTO;
import com.railway.train_service.dto.UpdatedTrainDTO;

import java.util.List;

public interface ITrainService {
    public ResponseStructureDTO<TrainDTO> addTrain(TrainRequestDTO trainRequestDTO);

    public ResponseStructureDTO<List<TrainDTO>> getAllTrains();

    public ResponseStructureDTO<List<TrainDTO>> getAllTrainsByName(String trainName);

    public ResponseStructureDTO<TrainDTO> updateTrain(String trainNumber, UpdatedTrainDTO updatedTrainDTO);

    public ResponseStructureDTO<String> deleteTrainByNumber(String trainNumber);
}
