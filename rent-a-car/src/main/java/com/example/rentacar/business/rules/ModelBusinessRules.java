package com.example.rentacar.business.rules;

import com.example.rentacar.common.constants.Messages;
import com.example.rentacar.core.exceptions.BusinessException;
import com.example.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ModelBusinessRules {
    private final ModelRepository repository;

    public void checkIfModelExistsById(int id){
        if(!repository.existsById(id)){
            throw new BusinessException(Messages.Model.NotExists);
        }
    }

    public void checkIfModelExistsByName(String name){
        if(repository.existsByNameIgnoreCase(name)){
            throw new BusinessException(Messages.Model.Exists);
        }
    }
}
