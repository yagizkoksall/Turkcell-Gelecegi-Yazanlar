package com.example.inventoryservice.business.dto.responses.get;

import com.example.inventoryservice.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarsResponse {
    private UUID id;
    private UUID modelId;
    private int modelYear;
    private String plate;
    private State state;
    private double dailyPrice;
    private String modelBrandName;
    private String modelName;
}