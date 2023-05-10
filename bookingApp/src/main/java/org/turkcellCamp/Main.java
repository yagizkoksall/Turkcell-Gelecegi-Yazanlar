package org.turkcellCamp;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        CorporateCustomer corporateCustomer1 = new CorporateCustomer(1,"1231231421","Kodlamaio");

        IndividualCustomer individualCustomer1 = new IndividualCustomer();
        individualCustomer1.setId(2);
        individualCustomer1.setFirstName("Engin");
        individualCustomer1.setLastName("Demiroğ");
        individualCustomer1.setNationalityId("09876543210");

        Hotel hotel1 = new Hotel(1,"Hilton");

        Booking booking = new Booking();
        booking.setId(1);
        booking.setStartDate(new Date());
        booking.setEndDate(new Date());
        booking.setDailyPrice(3000);
        booking.setCustomer(individualCustomer1);
        booking.setHotel(hotel1);

        //Yolla gitsin yazsın dbye
    }
}