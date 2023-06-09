package com.example.maintenanceservice.api.controllers;

import com.example.maintenanceservice.business.abstracts.MaintenanceService;
import com.example.maintenanceservice.business.dto.requests.create.CreateMaintenanceRequest;
import com.example.maintenanceservice.business.dto.requests.update.UpdateMaintenanceRequest;
import com.example.maintenanceservice.business.dto.responses.create.CreateMaintenanceResponse;
import com.example.maintenanceservice.business.dto.responses.get.GetAllMaintenancesResponse;
import com.example.maintenanceservice.business.dto.responses.get.GetMaintenanceResponse;
import com.example.maintenanceservice.business.dto.responses.update.UpdateMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/maintenance")
public class MaintenancesController {
    private final MaintenanceService service;

    @GetMapping
    public List<GetAllMaintenancesResponse> getAll(){
       return service.getAll();
    }

    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@RequestBody CreateMaintenanceRequest request){
        return service.add(request);
    }

    @PutMapping("/return")
    public GetMaintenanceResponse returnCarFromMaintenance(@RequestParam UUID carId){
       return service.returnCarFromMaintenance(carId);
    }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(@PathVariable UUID id, @RequestBody UpdateMaintenanceRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
}