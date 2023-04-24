package com.example.rentacar.repository;

import com.example.rentacar.entities.Car;
import com.example.rentacar.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    List<Car> findAllByStateIsNot(State state);
    boolean existsByPlate(String plate);

}
