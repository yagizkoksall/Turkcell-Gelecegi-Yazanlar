package com.example.inventoryservice.business.concretes;

import com.example.commonpackage.events.inventory.CarCreatedEvent;
import com.example.commonpackage.events.inventory.CarDeletedEvent;
import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.commonpackage.utils.kafka.producer.KafkaProducer;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.inventoryservice.business.abstracts.CarService;
import com.example.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetCarResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import com.example.inventoryservice.business.rules.CarBusinessRules;
import com.example.inventoryservice.entities.Car;
import com.example.inventoryservice.entities.enums.State;
import com.example.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapperService mapper;
    private final CarBusinessRules rules;
    private final KafkaProducer producer;

    @Override
    public List<GetAllCarsResponse> getAll() {
        var cars = repository.findAll();
        var response = cars
                .stream()
                .map(car -> mapper.forResponse().map(car, GetAllCarsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCarResponse getById(UUID id) {
        rules.checkIfCarExists(id);
        var car = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(car, GetCarResponse.class);

        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        var car = mapper.forRequest().map(request, Car.class);
        car.setId(UUID.randomUUID());
        car.setState(State.AVAILABLE);

        var createdCar = repository.save(car);
        sendCreatedCarMessage(createdCar);

        var response = mapper.forResponse().map(createdCar, CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(UUID id, UpdateCarRequest request) {
        rules.checkIfCarExists(id);

        var car = mapper.forRequest().map(request, Car.class);
        car.setId(id);
        repository.save(car);

        var response = mapper.forResponse().map(car, UpdateCarResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfCarExists(id);
        repository.deleteById(id);

        sendDeletedCarMessage(id);
    }

    @Override
    public ClientResponse checkIfCarAvailable(UUID id) {
        var response= new ClientResponse();
        validateCarAvailability(id, response);

        return response;
    }

    @Override
    public void changeStateByCarId(State state, UUID id) {
        repository.changeStateByCarId(state, id);
    }

    private void sendDeletedCarMessage(UUID carId){
        producer.sendMessage(new CarDeletedEvent(carId),"car-deleted");
    }

    private void sendCreatedCarMessage(Car createdCar){
        var event = mapper.forResponse().map(createdCar, CarCreatedEvent.class);
        producer.sendMessage(event,"car-created");
    }

    private void validateCarAvailability(UUID id, ClientResponse response) {
        try {
            rules.checkIfCarExists(id);
            rules.checkCarAvailability(id);
            response.setSuccess(true);
        } catch (BusinessException exception) {
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
        }
    }
}