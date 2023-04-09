package com.example.rentacar.business.dto.requests.create;

import com.example.rentacar.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    private int modelId;
    private int modelYear;
    private String plate;
    private double dailyPrice;
}
