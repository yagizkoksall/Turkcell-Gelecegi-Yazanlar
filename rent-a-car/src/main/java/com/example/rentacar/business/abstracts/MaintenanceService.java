package com.example.rentacar.business.abstracts;

import com.example.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.example.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.example.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.example.rentacar.business.dto.responses.get.GetAllMaintenanceResponse;
import com.example.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.example.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    List<GetAllMaintenanceResponse> getAll();
    GetMaintenanceResponse getById(int id);
    GetMaintenanceResponse returnCarFromMaintenance(int carId);
    CreateMaintenanceResponse add(CreateMaintenanceRequest request);
    UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request);
    void delete(int id);
}
