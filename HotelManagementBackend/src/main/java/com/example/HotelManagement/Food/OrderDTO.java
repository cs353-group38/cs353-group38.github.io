package com.example.HotelManagement.Food;

import java.util.List;

public class OrderDTO {

    private List<Integer> foodIdList;

    private int guestId;

    public OrderDTO(List<Integer> foodIdList, int guestId) {
        this.foodIdList = foodIdList;
        this.guestId = guestId;
    }

    public List<Integer> getFoodIdList() {
        return foodIdList;
    }

    public void setFoodIdList(List<Integer> foodIdList) {
        this.foodIdList = foodIdList;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
}
