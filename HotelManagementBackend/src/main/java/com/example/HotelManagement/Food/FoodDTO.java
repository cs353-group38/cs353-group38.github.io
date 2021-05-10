package com.example.HotelManagement.Food;

public class FoodDTO {

    private int foodId;

    private String foodName;

    private String foodType;

    private double price;

    private int calorie;

    private String locationName;

    private String restaurantName;

    private String restaurantType;

    public FoodDTO(int foodId, String foodName, String foodType, double price, int calorie, String locationName, String restaurantName, String restaurantType) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodType = foodType;
        this.price = price;
        this.calorie = calorie;
        this.locationName = locationName;
        this.restaurantName = restaurantName;
        this.restaurantType = restaurantType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
