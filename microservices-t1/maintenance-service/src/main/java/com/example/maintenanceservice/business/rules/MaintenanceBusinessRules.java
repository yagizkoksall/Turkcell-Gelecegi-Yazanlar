package com.example.maintenanceservice.business.rules;

import com.example.commonpackage.utils.constants.Messages;
import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.maintenanceservice.api.clients.CarClient;
import com.example.maintenanceservice.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@AllArgsConstructor
public class MaintenanceBusinessRules {
    private final MaintenanceRepository repository;
    private final CarClient client;

    public void checkIfMaintenanceExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Maintenance.NotExists);
        }
    }

    public void checkIfCarIsNotUnderMaintenance(UUID carId) {
        if (!repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new BusinessException(Messages.Maintenance.CarNotExists);
        }
    }


    public void checkIfCarUnderMaintenance(UUID carId) {
        if (repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new BusinessException(Messages.Maintenance.CarExists);
        }
    }

    public void checkCarAvailabilityForMaintenance(UUID carId){
        var response = client.checkIfCarAvailable(carId);
        if((!response.isSuccess())){
            throw new BusinessException(response.getMessage());
        }
    }


//    public void checkCarAvailabilityForMaintenance(State state) {
//        if (state.equals(State.RENTED)) {
//            throw new BusinessException(Messages.Maintenance.CarIsRented);
//        }
//    }
}