package com.example.inventoryservice.business.concretes;


import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.inventoryservice.business.abstracts.ModelService;
import com.example.inventoryservice.business.dto.requests.create.CreateModelRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateModelRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateModelResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllModelsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetModelResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateModelResponse;
import com.example.inventoryservice.business.rules.ModelBusinessRules;
import com.example.inventoryservice.entities.Model;
import com.example.inventoryservice.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {

    private final ModelRepository repository;
    private final ModelMapperService mapper;
    private final ModelBusinessRules rules;

    @Override
    public List<GetAllModelsResponse> getAll() {
        var models = repository.findAll();
        var response = models
                .stream()
                .map(model -> mapper.forResponse().map(model, GetAllModelsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetModelResponse getById(UUID id) {
        rules.checkIfModelExists(id);
        var model = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(model, GetModelResponse.class);

        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        var model = mapper.forRequest().map(request, Model.class);
        model.setId(null);
        repository.save(model);
        var response = mapper.forResponse().map(model, CreateModelResponse.class);

        return response;
    }

    @Override
    public UpdateModelResponse update(UUID id, UpdateModelRequest request) {
        rules.checkIfModelExists(id);
        var model = mapper.forRequest().map(request, Model.class);
        model.setId(id);
        repository.save(model);
        var response = mapper.forResponse().map(model, UpdateModelResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfModelExists(id);
        repository.deleteById(id);
    }
}
