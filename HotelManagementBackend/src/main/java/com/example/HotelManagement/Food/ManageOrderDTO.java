package com.example.HotelManagement.Food;

public class ManageOrderDTO {

    private int orderId;

    private int guestId;

    private String guestName;

    private int managerId;

    private int housekeeperId;

    private int guestRoomNo;

    private String guestBuildingName;

    private String guestLocationName;

    private String restaurantName;

    private String restaurantLocationName;

    public ManageOrderDTO(int orderId, int guestId, String guestName, int managerId, int housekeeperId, int guestRoomNo,
                          String guestBuildingName, String guestLocationName, String restaurantName, String restaurantLocationName) {
        this.orderId = orderId;
        this.guestId = guestId;
        this.guestName = guestName;
        this.managerId = managerId;
        this.housekeeperId = housekeeperId;
        this.guestRoomNo = guestRoomNo;
        this.guestBuildingName = guestBuildingName;
        this.guestLocationName = guestLocationName;
        this.restaurantName = restaurantName;
        this.restaurantLocationName = restaurantLocationName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getHousekeeperId() {
        return housekeeperId;
    }

    public void setHousekeeperId(int housekeeperId) {
        this.housekeeperId = housekeeperId;
    }

    public int getGuestRoomNo() {
        return guestRoomNo;
    }

    public void setGuestRoomNo(int guestRoomNo) {
        this.guestRoomNo = guestRoomNo;
    }

    public String getGuestBuildingName() {
        return guestBuildingName;
    }

    public void setGuestBuildingName(String guestBuildingName) {
        this.guestBuildingName = guestBuildingName;
    }

    public String getGuestLocationName() {
        return guestLocationName;
    }

    public void setGuestLocationName(String guestLocationName) {
        this.guestLocationName = guestLocationName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantLocationName() {
        return restaurantLocationName;
    }

    public void setRestaurantLocationName(String restaurantLocationName) {
        this.restaurantLocationName = restaurantLocationName;
    }
}
