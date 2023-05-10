package com.example.inventoryservice.api.controllers;

import com.example.inventoryservice.business.abstracts.ModelService;
import com.example.inventoryservice.business.dto.requests.create.CreateModelRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateModelRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateModelResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllModelsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetModelResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService service;

    @GetMapping
    public List<GetAllModelsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@PathVariable UUID id, @RequestBody UpdateModelRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }

}
