package com.example.rentacar.api.controllers;

import com.example.rentacar.business.abstracts.MaintenanceService;
import com.example.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.example.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.example.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.example.rentacar.business.dto.responses.get.GetAllMaintenanceResponse;
import com.example.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.example.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/maintenance")
public class MaintenancesController {
    private final MaintenanceService service;

    @GetMapping
    public List<GetAllMaintenanceResponse> getAll(){
       return service.getAll();
    }

    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@RequestBody CreateMaintenanceRequest request){
        return service.add(request);
    }

    @PutMapping("/return")
    public GetMaintenanceResponse returnCarFromMaintenance(@RequestParam int carId){
       return service.returnCarFromMaintenance(carId);
    }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(@PathVariable int id, @RequestBody UpdateMaintenanceRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
