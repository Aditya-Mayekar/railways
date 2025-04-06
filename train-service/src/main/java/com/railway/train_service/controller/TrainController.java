package com.railway.train_service.controller;

import com.railway.train_service.dto.ResponseStructureDTO;
import com.railway.train_service.dto.TrainDTO;
import com.railway.train_service.dto.TrainRequestDTO;
import com.railway.train_service.dto.UpdatedTrainDTO;
import com.railway.train_service.service.ITrainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/allTrains")
    public ResponseEntity<ResponseStructureDTO<List<TrainDTO>>> getAllTrains() {
        ResponseStructureDTO<List<TrainDTO>> response = iTrainService.getAllTrains();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/getAllTrainsByName")
    public ResponseEntity<ResponseStructureDTO<List<TrainDTO>>> getAllTrainsByName(@RequestBody String trainName) {
        ResponseStructureDTO<List<TrainDTO>> response = iTrainService.getAllTrainsByName(trainName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updateTrain/{trainNumber}")
    public ResponseEntity<ResponseStructureDTO<TrainDTO>> updateTrain(@PathVariable String trainNumber, @Valid @RequestBody UpdatedTrainDTO updatedTrainDTO) {
        ResponseStructureDTO<TrainDTO> response = iTrainService.updateTrain(trainNumber, updatedTrainDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/deleteTrainByNumber")
    public ResponseEntity<ResponseStructureDTO<String>> deleteTrainByNumber(@RequestBody String trainNumber) {
        ResponseStructureDTO<String> response = iTrainService.deleteTrainByNumber(trainNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
