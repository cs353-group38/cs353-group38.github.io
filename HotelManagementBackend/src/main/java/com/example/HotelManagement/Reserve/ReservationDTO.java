package com.example.HotelManagement.Reserve;

public class ReservationDTO {

    private int reservationId;

    private String buildingName;

    private String locationName;

    private Long checkInDate;

    private Long checkOutDate;

    private String type;

    private String description;

    private double price;

    private int maxNoOfPeople;

    private String guestName;

    private double moneySpent;

    public ReservationDTO(int reservationId, String buildingName, String locationName, Long checkInDate,
                          Long checkOutDate, String type, String description, double price,
                          int maxNoOfPeople, String guestName, double moneySpent) {
        this.reservationId = reservationId;
        this.buildingName = buildingName;
        this.locationName = locationName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.type = type;
        this.description = description;
        this.price = price;
        this.maxNoOfPeople = maxNoOfPeople;
        this.guestName = guestName;
        this.moneySpent = moneySpent;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Long checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxNoOfPeople() {
        return maxNoOfPeople;
    }

    public void setMaxNoOfPeople(int maxNoOfPeople) {
        this.maxNoOfPeople = maxNoOfPeople;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
