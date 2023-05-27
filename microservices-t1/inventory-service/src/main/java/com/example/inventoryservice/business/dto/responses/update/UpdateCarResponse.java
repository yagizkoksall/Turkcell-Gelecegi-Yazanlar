package com.example.inventoryservice.business.dto.responses.update;

import com.example.commonpackage.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarResponse {
    private UUID id;
    private int modelId;
    private int modelYear;
    private String plate;
    private State state;
    private double dailyPrice;
}