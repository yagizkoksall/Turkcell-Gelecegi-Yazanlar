package com.example.maintenanceservice.business.abstracts;

import com.example.maintenanceservice.business.dto.requests.create.CreateMaintenanceRequest;
import com.example.maintenanceservice.business.dto.requests.update.UpdateMaintenanceRequest;
import com.example.maintenanceservice.business.dto.responses.create.CreateMaintenanceResponse;
import com.example.maintenanceservice.business.dto.responses.get.GetAllMaintenancesResponse;
import com.example.maintenanceservice.business.dto.responses.get.GetMaintenanceResponse;
import com.example.maintenanceservice.business.dto.responses.update.UpdateMaintenanceResponse;

import java.util.List;
import java.util.UUID;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();
    GetMaintenanceResponse getById(UUID id);
    GetMaintenanceResponse returnCarFromMaintenance(UUID carId);
    CreateMaintenanceResponse add(CreateMaintenanceRequest request);
    UpdateMaintenanceResponse update(UUID id, UpdateMaintenanceRequest request);
    void delete(UUID id);
}