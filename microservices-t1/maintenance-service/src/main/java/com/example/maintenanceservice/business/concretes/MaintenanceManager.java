package com.example.maintenanceservice.business.concretes;


import com.example.commonpackage.events.inventory.MaintenanceCreatedEvent;
import com.example.commonpackage.events.inventory.MaintenanceDeletedEvent;
import com.example.commonpackage.utils.kafka.producer.KafkaProducer;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.maintenanceservice.business.abstracts.MaintenanceService;
import com.example.maintenanceservice.business.dto.requests.create.CreateMaintenanceRequest;
import com.example.maintenanceservice.business.dto.requests.update.UpdateMaintenanceRequest;
import com.example.maintenanceservice.business.dto.responses.create.CreateMaintenanceResponse;
import com.example.maintenanceservice.business.dto.responses.get.GetAllMaintenancesResponse;
import com.example.maintenanceservice.business.dto.responses.get.GetMaintenanceResponse;
import com.example.maintenanceservice.business.dto.responses.update.UpdateMaintenanceResponse;
import com.example.maintenanceservice.business.rules.MaintenanceBusinessRules;
import com.example.maintenanceservice.entities.Maintenance;
import com.example.maintenanceservice.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final MaintenanceBusinessRules rules;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances
                .stream()
                .map(maintenance -> mapper.forResponse().map(maintenance, GetAllMaintenancesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetMaintenanceResponse getById(UUID id) {
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.forResponse().map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(UUID carId) {
        Maintenance maintenance = repository.findByCarIdAndIsCompletedIsFalse(carId);
        rules.checkIfCarIsNotUnderMaintenance(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);


        GetMaintenanceResponse response = mapper.forResponse().map(maintenance, GetMaintenanceResponse.class);

        return response;
    }


    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        rules.checkIfCarUnderMaintenance(request.getCarId());
        rules.checkCarAvailabilityForMaintenance(request.getCarId());
        Maintenance maintenance = mapper.forRequest().map(request, Maintenance.class);
        maintenance.setId(null);
        maintenance.setCompleted(false);
        maintenance.setStartedDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        repository.save(maintenance);
      // -  carService.changeState(request.getCarId(), State.MAINTENANCE);

        sendKafkaMaintenanceCreatedEvent(request.getCarId());

        CreateMaintenanceResponse response = mapper.forResponse().map(maintenance, CreateMaintenanceResponse.class);

        return response;
    }



    @Override
    public UpdateMaintenanceResponse update(UUID id, UpdateMaintenanceRequest request) {
        rules.checkIfMaintenanceExists(id);
        Maintenance maintenance = mapper.forRequest().map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.forResponse().map(maintenance, UpdateMaintenanceResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfMaintenanceExists(id);
        makeCarAvailableIfIsCompletedFalse(id);

        var carId = repository.findById(id).orElseThrow().getCarId();
        sendKafkaMaintenanceDeletedEvent(carId);
        repository.deleteById(id);
    }

    private void sendKafkaMaintenanceCreatedEvent(UUID carId){
        producer.sendMessage(new MaintenanceCreatedEvent(carId),"maintenance-created");
    }

    private void sendKafkaMaintenanceDeletedEvent(UUID carId){
        producer.sendMessage(new MaintenanceDeletedEvent(carId),"maintenance-deleted");
    }


    private void makeCarAvailableIfIsCompletedFalse(UUID id) {
//        int carId = repository.findById(id).get().getCar().getId();
//        if (repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
//            carService.changeState(carId, State.AVAILABLE);
//        }
    }
}