package com.example.HotelManagement.Food;

public class OrderDetailsDTO {

    private String guestName;

    private String foodName;

    private double foodPrice;

    private String restaurantName;

    private String buildingName;

    private int roomNo;

    private int housekeeperId;

    public OrderDetailsDTO(String guestName, String foodName, double foodPrice, String restaurantName, String buildingName, int roomNo, int housekeeperId) {
        this.guestName = guestName;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.restaurantName = restaurantName;
        this.buildingName = buildingName;
        this.roomNo = roomNo;
        this.housekeeperId = housekeeperId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getHousekeeperId() {
        return housekeeperId;
    }

    public void setHousekeeperId(int housekeeperId) {
        this.housekeeperId = housekeeperId;
    }
}
