package com.example.rentalservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalResponse {
    private UUID id;
    private UUID carId;
    private double dailyPrice;
    private int rentedForDays;
    private double totalPrice;
    private LocalDate rentedAt;
}
