package com.example.commonpackage.events.inventory;

import com.example.commonpackage.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalDeletedEvent implements Event {
    private UUID carId;

}
