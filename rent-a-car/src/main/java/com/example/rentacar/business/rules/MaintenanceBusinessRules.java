package com.example.rentacar.business.rules;

import com.example.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.example.rentacar.common.constants.Messages;
import com.example.rentacar.core.exceptions.BusinessException;
import com.example.rentacar.entities.enums.State;
import com.example.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MaintenanceBusinessRules {
    private final MaintenanceRepository repository;

    public void checkIfMaintenanceExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Maintenance.NotExists);
        }
    }

    public void checkIfCarIsNotUnderMaintenance(int carId) {
        if (!repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new BusinessException(Messages.Maintenance.CarNotExists);
        }
    }


    public void checkIfCarUnderMaintenance(int carId) {
        if (repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new BusinessException(Messages.Maintenance.CarExists);
        }
    }


    public void checkCarAvailabilityForMaintenance(State state) {
        if (state.equals(State.RENTED)) {
            throw new BusinessException(Messages.Maintenance.CarIsRented);
        }
    }
}
