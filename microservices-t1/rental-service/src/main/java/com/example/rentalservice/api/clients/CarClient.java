package com.example.rentalservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.dto.GetCarResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "INVENTORY-SERVICE", fallback = CarClientFallback.class)
public interface CarClient {

    @Retry(name="retry-rental")
    @GetMapping(value = "/api/cars/check-car-available/{carId}")
    ClientResponse checkIfCarAvailable(@PathVariable UUID carId);

    @Retry(name = "retry-rental")
    @GetMapping(value = "/api/cars/{carId}")
    GetCarResponse getById(@PathVariable UUID carId);


}