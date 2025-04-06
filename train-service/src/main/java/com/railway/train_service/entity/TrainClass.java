package com.railway.train_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trainClass")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Integer classId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @Column(nullable = false)
    private String classType;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Double price;
}
