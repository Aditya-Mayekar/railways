package com.railway.train_service.service;

import com.railway.train_service.dto.ResponseStructureDTO;
import com.railway.train_service.dto.TrainDTO;
import com.railway.train_service.dto.TrainRequestDTO;

public interface ITrainService {
    public ResponseStructureDTO<TrainDTO> addTrain(TrainRequestDTO trainRequestDTO);
}
