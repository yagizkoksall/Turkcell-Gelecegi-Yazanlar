package com.example.inventoryservice.business.abstracts;

import com.example.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetCarResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import com.example.inventoryservice.entities.enums.State;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<GetAllCarsResponse> getAll();
    GetCarResponse getById(UUID id);
    CreateCarResponse add(CreateCarRequest request);
    UpdateCarResponse update(UUID id, UpdateCarRequest request);
    void delete(UUID id);
    void checkIfCarAvailable(UUID id);
    void changeStateByCarId(State state,UUID id);
}