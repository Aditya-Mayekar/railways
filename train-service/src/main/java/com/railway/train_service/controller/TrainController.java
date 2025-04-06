package com.railway.train_service.controller;

import com.railway.train_service.dto.ResponseStructureDTO;
import com.railway.train_service.dto.TrainDTO;
import com.railway.train_service.dto.TrainRequestDTO;
import com.railway.train_service.service.ITrainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trains")
public class TrainController {
    private final ITrainService iTrainService;

    public TrainController(ITrainService iTrainService) {
        this.iTrainService = iTrainService;
    }

    @PostMapping("/addTrain")
    public ResponseEntity<ResponseStructureDTO<TrainDTO>> addTrain(@Valid @RequestBody TrainRequestDTO trainRequestDTO) {
        ResponseStructureDTO<TrainDTO> response = iTrainService.addTrain(trainRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
