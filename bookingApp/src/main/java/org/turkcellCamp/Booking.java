package org.turkcellCamp;

import java.util.Date;

public class Booking {
    private int id;
    private Date startDate;
    private Date endDate;
    private double dailyPrice;
    private Customer customer;
    private Hotel hotel;

    public Booking() {
    }

    public Booking(int id, Date startDate, Date endDate, double dailyPrice, Customer customer, Hotel hotel) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyPrice = dailyPrice;
        this.customer = customer;
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
