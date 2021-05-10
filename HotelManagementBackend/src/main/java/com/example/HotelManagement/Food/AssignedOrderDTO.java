package com.example.HotelManagement.Food;

import java.util.List;

public class AssignedOrderDTO {
    private String guestName;

    private int guestRoomNo;

    private String guestBuildingNo;

    private String guestLocationName;

    private String restaurantName;

    private String restaurantLocationName;

    private String managerName;

    private List<String> foodNames;

    public AssignedOrderDTO(String guestName, int guestRoomNo, String guestBuildingNo, String guestLocationName,
                            String restaurantName, String restaurantLocationName, String managerName, List<String> foodNames) {
        this.guestName = guestName;
        this.guestRoomNo = guestRoomNo;
        this.guestBuildingNo = guestBuildingNo;
        this.guestLocationName = guestLocationName;
        this.restaurantName = restaurantName;
        this.restaurantLocationName = restaurantLocationName;
        this.managerName = managerName;
        this.foodNames = foodNames;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getGuestRoomNo() {
        return guestRoomNo;
    }

    public void setGuestRoomNo(int guestRoomNo) {
        this.guestRoomNo = guestRoomNo;
    }

    public String getGuestBuildingNo() {
        return guestBuildingNo;
    }

    public void setGuestBuildingNo(String guestBuildingNo) {
        this.guestBuildingNo = guestBuildingNo;
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

    public List<String> getFoodNames() {
        return foodNames;
    }

    public void setFoodNames(List<String> foodNames) {
        this.foodNames = foodNames;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
