package com.railway.train_service.repository;

import com.railway.train_service.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ITrainRepository extends JpaRepository<Train, Integer> {
    Optional<Train> findByTrainNumber(String trainNumber);

    List<Train> findAllByTrainName(String trainName);

}
