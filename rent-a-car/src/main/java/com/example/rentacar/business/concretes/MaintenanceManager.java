package com.example.rentacar.business.concretes;

import com.example.rentacar.business.abstracts.CarService;
import com.example.rentacar.business.abstracts.MaintenanceService;
import com.example.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.example.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.example.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.example.rentacar.business.dto.responses.get.GetAllMaintenanceResponse;
import com.example.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.example.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import com.example.rentacar.entities.Maintenance;
import com.example.rentacar.entities.enums.State;
import com.example.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;

    @Override
    public List<GetAllMaintenanceResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenanceResponse> response = maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenanceResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        Maintenance maintenance = repository.findByCarIdAndIsCompletedIsFalse(carId);
        checkIfCarIsNotUnderMaintenance(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);
        carService.changeState(carId, State.AVAILABLE);
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }


    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        checkIfCarUnderMaintenance(request);
        checkCarAvailabilityForMaintenance(request);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartedDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        repository.save(maintenance);
        carService.changeState(request.getCarId(), State.MAINTENANCE);
        CreateMaintenanceResponse response = mapper.map(maintenance, CreateMaintenanceResponse.class);

        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExists(id);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExists(id);
        makeCarAvailableIfIsCompletedFalse(id);

        repository.deleteById(id);
    }

    private void checkIfCarIsNotUnderMaintenance(int carId) {
        if (!repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new RuntimeException("Bakımda böyle bir araç bulunamadı!");
        }
    }


    private void checkIfCarUnderMaintenance(CreateMaintenanceRequest request) {
        if (repository.existsByCarIdAndIsCompletedIsFalse(request.getCarId())) {
            throw new RuntimeException("Araç şuanda bakımda!");
        }
    }

    private void checkIfMaintenanceExists(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Böyle bir bakım bilgisine ulaşılamadı.");
        }
    }

    private void checkCarAvailabilityForMaintenance(CreateMaintenanceRequest request) {
        if (carService.getById(request.getCarId()).getState().equals(State.RENTED)) {
            throw new RuntimeException("Araç kirada olduğu için bakıma alınamaz!");
        }
    }

    private void makeCarAvailableIfIsCompletedFalse(int id) {
        int carId = repository.findById(id).get().getCar().getId();
        if (repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            carService.changeState(carId, State.AVAILABLE);
        }
    }
}
