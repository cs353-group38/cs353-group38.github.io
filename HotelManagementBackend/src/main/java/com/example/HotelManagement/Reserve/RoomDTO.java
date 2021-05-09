package com.example.HotelManagement.Reserve;

public class RoomDTO {
    private int roomNo;

    private String buildingName;

    private String locationName;

    private String type;

    private String description;

    private double price;

    private int maxNoOfPeople;

    private int noOfRooms;

    private int noOfFloors;

    public RoomDTO(int roomNo, String buildingName, String locationName, String type, String description, double price,
                   int maxNoOfPeople, int noOfRooms, int noOfFloors) {
        this.roomNo = roomNo;
        this.buildingName = buildingName;
        this.locationName = locationName;
        this.type = type;
        this.description = description;
        this.price = price;
        this.maxNoOfPeople = maxNoOfPeople;
        this.noOfRooms = noOfRooms;
        this.noOfFloors = noOfFloors;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }
}
