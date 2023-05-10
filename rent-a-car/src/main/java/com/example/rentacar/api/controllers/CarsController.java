package com.example.rentacar.api.controllers;

import com.example.rentacar.business.abstracts.CarService;
import com.example.rentacar.business.dto.requests.create.CreateCarRequest;
import com.example.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.example.rentacar.business.dto.responses.create.CreateCarResponse;
import com.example.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.example.rentacar.business.dto.responses.get.GetCarResponse;
import com.example.rentacar.business.dto.responses.update.UpdateCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarsController {
    private final CarService service;

    @GetMapping
    public List<GetAllCarsResponse> getAll(@RequestParam(defaultValue = "true") boolean includeMaintenance){
        return service.getAll(includeMaintenance);
    }

    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable int id){
       return service.getById(id);
    }

    @PostMapping
    public CreateCarResponse add(@Valid @RequestBody CreateCarRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCarResponse update(@PathVariable int id, @RequestBody UpdateCarRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
