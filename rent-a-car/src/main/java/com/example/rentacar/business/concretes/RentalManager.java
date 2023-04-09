package com.example.rentacar.business.concretes;

import com.example.rentacar.business.abstracts.CarService;
import com.example.rentacar.business.abstracts.PaymentService;
import com.example.rentacar.business.abstracts.RentalService;
import com.example.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.example.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.example.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.example.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import com.example.rentacar.business.dto.responses.get.GetRentalResponse;
import com.example.rentacar.business.dto.responses.update.UpdateRentalResponse;
import com.example.rentacar.core.dto.CreateRentalPaymentRequest;
import com.example.rentacar.entities.Rental;
import com.example.rentacar.entities.enums.State;
import com.example.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;
    private final PaymentService paymentService;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = repository.findAll();
        List<GetAllRentalsResponse> response = rentals
                .stream()
                .map(rental -> mapper.map(rental, GetAllRentalsResponse.class)).toList();

        return response;

    }

    @Override
    public GetRentalResponse getById(int id) {
        checkIfRentalExists(id);
        Rental rental = repository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.map(rental, GetRentalResponse.class);
        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        checkCarAvailabilityForRent(request.getCarId());
        Rental rental = mapper.map(request,Rental.class);
        rental.setId(0);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setStartDate(LocalDateTime.now());

        CreateRentalPaymentRequest paymentRequest = new CreateRentalPaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(rental));
        paymentService.processRentalPayment(paymentRequest);

        repository.save(rental);
        carService.changeState(request.getCarId(),State.RENTED);
        CreateRentalResponse response = mapper.map(rental, CreateRentalResponse.class);

        return response;
    }



    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        checkIfRentalExists(id);
        Rental rental = mapper.map(request,Rental.class);
        rental.setId(id);
        rental.setTotalPrice(getTotalPrice(rental));
        repository.save(rental);
        UpdateRentalResponse response = mapper.map(rental, UpdateRentalResponse.class);
        return response;
    }

    @Override
    public void delete(int id)
    {
        checkIfRentalExists(id);
        int carId = repository.findById(id).get().getCar().getId();
        carService.changeState(carId,State.AVAILABLE);
        repository.deleteById(id);
    }

    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }

    private void checkIfRentalExists(int id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Böyle bir kiralama mevcut değil.");
        }
    }

    private void checkCarAvailabilityForRent(int carId){
        if(!carService.getById(carId).getState().equals(State.AVAILABLE)){
            throw new RuntimeException("Araç müsait değil.");
        }

    }

}
