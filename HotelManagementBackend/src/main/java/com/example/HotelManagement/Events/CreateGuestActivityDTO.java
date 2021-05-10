package com.example.HotelManagement.Events;

public class CreateGuestActivityDTO extends CreateEventDTO{

    private double price;

    public CreateGuestActivityDTO(String eventName, String locationName, Long startDate, Long endDate, int minAge, int quota, String description, int mgrId, double price) {
        super(eventName, locationName, startDate, endDate, minAge, quota, description, mgrId);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
