package com.example.HotelManagement.SignUp;

public class UserAgeDTO {
    private String userType;
    private int avgAge;
    private int minAge;
    private int maxAge;

    public UserAgeDTO(String userType, int avgAge, int minAge, int maxAge) {
        this.userType = userType;
        this.avgAge = avgAge;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getAvgAge() {
        return avgAge;
    }

    public void setAvgAge(int avgAge) {
        this.avgAge = avgAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}
