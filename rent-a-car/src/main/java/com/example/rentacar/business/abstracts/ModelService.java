package com.example.rentacar.business.abstracts;

import com.example.rentacar.business.dto.requests.create.CreateModelRequest;
import com.example.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.example.rentacar.business.dto.responses.create.CreateModelResponse;
import com.example.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.example.rentacar.business.dto.responses.get.GetModelResponse;
import com.example.rentacar.business.dto.responses.update.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    GetModelResponse getById(int id);
    CreateModelResponse add(CreateModelRequest request);
    UpdateModelResponse update(int id, UpdateModelRequest request);
    void delete(int id);
}
