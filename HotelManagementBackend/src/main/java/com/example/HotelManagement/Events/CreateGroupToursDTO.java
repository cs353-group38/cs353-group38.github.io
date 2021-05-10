package com.example.HotelManagement.Events;

public class CreateGroupToursDTO extends CreateGuestActivityDTO{

    private String organizerName;
    private String tourVehicle;
    private int distanceToCover;

    public CreateGroupToursDTO(String eventName, String locationName, Long startDate, Long endDate, int minAge, int quota,
                               String description, int mgrId, double price, String organizerName, String tourVehicle,
                               int distanceToCover) {
        super(eventName, locationName, startDate, endDate, minAge, quota, description, mgrId, price);
        this.organizerName = organizerName;
        this.tourVehicle = tourVehicle;
        this.distanceToCover = distanceToCover;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getTourVehicle() {
        return tourVehicle;
    }

    public void setTourVehicle(String tourVehicle) {
        this.tourVehicle = tourVehicle;
    }

    public int getDistanceToCover() {
        return distanceToCover;
    }

    public void setDistanceToCover(int distanceToCover) {
        this.distanceToCover = distanceToCover;
    }
}
