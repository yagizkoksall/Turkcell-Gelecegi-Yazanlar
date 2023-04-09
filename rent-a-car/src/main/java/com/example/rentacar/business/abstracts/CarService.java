package com.example.rentacar.business.abstracts;

import com.example.rentacar.business.dto.requests.create.CreateCarRequest;
import com.example.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.example.rentacar.business.dto.responses.create.CreateCarResponse;
import com.example.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.example.rentacar.business.dto.responses.get.GetCarResponse;
import com.example.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.example.rentacar.entities.enums.State;

import java.util.List;

public interface CarService {

     List<GetAllCarsResponse> getAll(boolean includeMaintenance);
     GetCarResponse getById(int id);
     CreateCarResponse add(CreateCarRequest request);
     UpdateCarResponse update(int id, UpdateCarRequest request);
     void changeState(int carId, State state);
     void delete(int id);


}
