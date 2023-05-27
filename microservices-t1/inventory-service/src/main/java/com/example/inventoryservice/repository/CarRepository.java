package com.example.inventoryservice.repository;

import com.example.inventoryservice.entities.Car;
import com.example.commonpackage.utils.enums.State;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    @Transactional
    @Modifying
    @Query(value = "update Car set state = :state where id = :id")
    void changeStateByCarId(State state, UUID id);
}
