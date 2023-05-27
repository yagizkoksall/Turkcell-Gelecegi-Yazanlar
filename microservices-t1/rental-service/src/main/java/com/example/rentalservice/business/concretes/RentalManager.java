package com.example.rentalservice.business.concretes;


import com.example.commonpackage.events.inventory.InvoiceCreatedEvent;
import com.example.commonpackage.events.inventory.RentalCreatedEvent;
import com.example.commonpackage.events.inventory.RentalDeletedEvent;
import com.example.commonpackage.utils.dto.GetCarResponse;
import com.example.commonpackage.utils.kafka.producer.KafkaProducer;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.rentalservice.api.clients.CarClient;
import com.example.rentalservice.business.abstracts.RentalService;
import com.example.rentalservice.business.dto.requests.CreateRentalRequest;
import com.example.rentalservice.business.dto.requests.UpdateRentalRequest;
import com.example.rentalservice.business.dto.responses.CreateRentalResponse;
import com.example.rentalservice.business.dto.responses.GetAllRentalsResponse;
import com.example.rentalservice.business.dto.responses.GetRentalResponse;
import com.example.rentalservice.business.dto.responses.UpdateRentalResponse;
import com.example.rentalservice.business.rules.RentalBusinessRules;
import com.example.rentalservice.entities.Rental;
import com.example.rentalservice.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapperService mapper;
    private final RentalBusinessRules rules;
    private final CarClient carClient;
    private final KafkaProducer producer;


    @Override
    public List<GetAllRentalsResponse> getAll() {
        var rentals = repository.findAll();
        var response = rentals
                .stream()
                .map(rental -> mapper.forResponse().map(rental, GetAllRentalsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        rules.checkIfRentalExists(id);
        var rental = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(rental, GetRentalResponse.class);

        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        rules.ensureCarIsAvailable(request.getCarId());
        // * araç müsait mi değil mi kontrol - id
        rules.processRentalPayment(request.getRentalPaymentRequest());

        var car = carClient.getById(request.getCarId());

        var rental = mapper.forRequest().map(request, Rental.class);

        rental.setId(UUID.randomUUID());
        rental.setDailyPrice(getTotalPrice(rental));
        rental.setRentedAt(LocalDate.now());
        repository.save(rental);
        sendKafkaInvoiceCreatedEvent(rental,car,request.getRentalPaymentRequest().getCardHolder());

        sendKafkaRentalCreatedEvent(request.getCarId());
        var response = mapper.forResponse().map(rental, CreateRentalResponse.class);

        return response;
    }

    private void sendKafkaRentalCreatedEvent(UUID carId) {
        producer.sendMessage(new RentalCreatedEvent(carId), "rental-created");
    }

    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(id);
        repository.save(rental);
        var response = mapper.forResponse().map(rental, UpdateRentalResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfRentalExists(id);
        sendKafkaRentalDeletedEvent(id);
        repository.deleteById(id);
    }

    private void sendKafkaRentalDeletedEvent(UUID id) {
        var carId = repository.findById(id).orElseThrow().getCarId();
        producer.sendMessage(new RentalDeletedEvent(carId), "rental-deleted");
    }


    private void sendKafkaInvoiceCreatedEvent(Rental rental, GetCarResponse carResponse, String cardHolder){
        InvoiceCreatedEvent invoiceCreatedEvent = new InvoiceCreatedEvent();
        invoiceCreatedEvent.setCardHolder(cardHolder);
        invoiceCreatedEvent.setModelName(carResponse.getModelName());
        invoiceCreatedEvent.setBrandName(carResponse.getBrandName());
        invoiceCreatedEvent.setPlate(carResponse.getPlate());
        invoiceCreatedEvent.setModelYear(carResponse.getModelYear());
        invoiceCreatedEvent.setDailyPrice(rental.getDailyPrice());
        invoiceCreatedEvent.setTotalPrice(rental.getTotalPrice());
        invoiceCreatedEvent.setRentedForDays(rental.getRentedForDays());
        invoiceCreatedEvent.setRentedAt(LocalDateTime.now());

        producer.sendMessage(invoiceCreatedEvent,"invoice-created");
    }

    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }



}
