package com.example.rentalservice.business.rules;


import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.rentalservice.api.clients.CarClient;
import com.example.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;
    private final CarClient client;

    public void checkIfRentalExists(UUID id) {
        if (!repository.existsById(id)) {
            // TODO: BusinessException
            throw new RuntimeException("RENTAL_NOT_EXISTS");
        }
    }

    public void ensureCarIsAvailable(UUID carId){
       var response = client.checkIfCarAvailable(carId);
        if((!response.isSuccess())){
            throw new BusinessException(response.getMessage());
        }
    }
}