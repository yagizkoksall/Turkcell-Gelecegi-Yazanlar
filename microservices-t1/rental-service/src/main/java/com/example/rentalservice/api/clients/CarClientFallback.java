package com.example.rentalservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.dto.GetCarResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;


import java.util.UUID;



@Component
public class CarClientFallback implements CarClient {
    @Override
    public ClientResponse checkIfCarAvailable(UUID carId) {
        throw new RuntimeException("INVENTORY SERVICE IS DOWN!");
    }

    @Override
    public GetCarResponse getById(UUID carId) {
        throw new RuntimeException("INVENTORY SERVICE IS DOWN!");
    }
}
