package com.example.rentacar.business.dto.requests.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
    @NotBlank
    private String cardHolder;
    @NotBlank
    private String modelName;
    @NotBlank
    private String brandName;
    @NotBlank
    private String plate;
    @Min(2006)
    private int modelYear;
    @Min(0)
    private double dailyPrice;
    @Min(0)
    private int rentedForDays;
    private LocalDateTime rentedAt;

}
