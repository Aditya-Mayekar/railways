package com.railway.train_service.repository;

import com.railway.train_service.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrainRepository extends JpaRepository<Train, Integer> {
}
