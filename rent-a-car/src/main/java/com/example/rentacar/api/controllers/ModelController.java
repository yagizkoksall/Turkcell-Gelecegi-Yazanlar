package com.example.rentacar.api.controllers;

import com.example.rentacar.business.abstracts.ModelService;
import com.example.rentacar.business.dto.requests.create.CreateModelRequest;
import com.example.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.example.rentacar.business.dto.responses.create.CreateModelResponse;
import com.example.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.example.rentacar.business.dto.responses.get.GetModelResponse;
import com.example.rentacar.business.dto.responses.update.UpdateModelResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/models")
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public List<GetAllModelsResponse> getAll(){
        return modelService.getAll();
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable int id){
        return modelService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest request){
        return modelService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest request){
        return modelService.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        modelService.delete(id);
    }
}
