package com.example.rentalservice.business.abstracts;



import com.example.rentalservice.business.dto.requests.CreateRentalRequest;
import com.example.rentalservice.business.dto.requests.UpdateRentalRequest;
import com.example.rentalservice.business.dto.responses.CreateRentalResponse;
import com.example.rentalservice.business.dto.responses.GetAllRentalsResponse;
import com.example.rentalservice.business.dto.responses.GetRentalResponse;
import com.example.rentalservice.business.dto.responses.UpdateRentalResponse;

import java.util.List;
import java.util.UUID;

public interface RentalService {

    List<GetAllRentalsResponse> getAll();


    GetRentalResponse getById(UUID id);

    CreateRentalResponse add(CreateRentalRequest request);



    UpdateRentalResponse update(UUID id, UpdateRentalRequest request);


    void delete(UUID id);
}