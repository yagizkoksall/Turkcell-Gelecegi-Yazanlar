package com.example.rentalservice.business.rules;


import com.example.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.rentalservice.api.clients.CarClient;
import com.example.rentalservice.api.clients.PaymentClient;
import com.example.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;
    private final CarClient carClient;
    private final PaymentClient paymentClient;

    public void checkIfRentalExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("RENTAL_NOT_EXISTS");
        }
    }

    public void ensureCarIsAvailable(UUID carId){
       var response = carClient.checkIfCarAvailable(carId);
        if((!response.isSuccess())){
            throw new BusinessException(response.getMessage());
        }
    }

    public void processRentalPayment(CreateRentalPaymentRequest request){
        var response = paymentClient.processRentalPayment(request);

        if(!response.isSuccess()){
            throw new BusinessException(response.getMessage());
        }
    }


}