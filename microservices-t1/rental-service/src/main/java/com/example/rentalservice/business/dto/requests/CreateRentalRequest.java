package com.example.rentalservice.business.dto.requests;

import com.example.commonpackage.utils.dto.CreateRentalPaymentRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
    @NotNull
    private UUID carId;
    @Min(1)
    private double dailyPrice;
    @Min(1)
    private int rentedForDays;
    // TODO: Card Information
    private CreateRentalPaymentRequest rentalPaymentRequest;

}


