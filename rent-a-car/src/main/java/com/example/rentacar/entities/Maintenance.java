package com.example.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "maintenances")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String information;
    private boolean isCompleted;
    private LocalDateTime startedDate;
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
