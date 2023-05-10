package com.example.rentacar.business.dto.requests.create;

import com.example.rentacar.common.constants.Regex;
import com.example.rentacar.common.utils.annotations.NotFutureYear;
import com.example.rentacar.entities.Model;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Min(1996)
    @NotFutureYear
    private int modelYear;
    @Pattern(regexp = Regex.Plate, message = "Plate number must match the pattern")
    private String plate;
    @Min(1)
    @Max(100000)
    private double dailyPrice;
}
