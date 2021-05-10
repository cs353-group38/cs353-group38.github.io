package com.example.HotelManagement.Food;

public class HousekeeperDTO {

    private int housekeeper_id;

    private String name;

    public HousekeeperDTO(int housekeeper_id, String name) {
        this.housekeeper_id = housekeeper_id;
        this.name = name;
    }

    public int getHousekeeper_id() {
        return housekeeper_id;
    }

    public void setHousekeeper_id(int housekeeper_id) {
        this.housekeeper_id = housekeeper_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
