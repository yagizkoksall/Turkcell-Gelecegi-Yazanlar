package com.example.inventoryservice.business.rules;

import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.inventoryservice.entities.enums.State;
import com.example.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository repository;

    public void checkIfCarExists(UUID id) {
        if (!repository.existsById(id)) {
            // TODO: BusinessException
            throw new BusinessException("CAR_NOT_EXISTS");
        }
    }

    public void checkCarAvailability(UUID id){
        var car = repository.findById(id).orElseThrow();
        if(!car.getState().equals(State.AVAILABLE)){
            throw new BusinessException("CAR_NOT_AVAILABLE");
        }
    }
}