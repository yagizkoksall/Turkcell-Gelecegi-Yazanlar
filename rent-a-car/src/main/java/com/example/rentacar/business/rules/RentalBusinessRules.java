package com.example.rentacar.business.rules;

import com.example.rentacar.common.constants.Messages;
import com.example.rentacar.core.exceptions.BusinessException;
import com.example.rentacar.entities.enums.State;
import com.example.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;

    public void checkIfRentalExists(int id){
        if(!repository.existsById(id)){
            throw new BusinessException(Messages.Car.NotExists);
        }
    }

    public void checkIfCarAvailable(State state){
        if(!state.equals(State.AVAILABLE)){
            throw new BusinessException(Messages.Car.NotAvailable);
        }

    }

}
