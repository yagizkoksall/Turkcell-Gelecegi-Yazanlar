package com.example.commonpackage.events.inventory;

import com.example.commonpackage.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCreatedEvent implements Event {
    private String cardHolder;
    private String modelName;
    private String brandName;
    private String plate;
    private int modelYear;
    private double dailyPrice;
    private double totalPrice;
    private int rentedForDays;
    private LocalDateTime rentedAt;
}
