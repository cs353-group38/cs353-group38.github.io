package com.example.HotelManagement.SignUp;

public class RoomTypeDTO {
    private String roomType;
    private int reservationCount;
    private double avgMoneySpent;

    public RoomTypeDTO(String roomType, int reservationCount, double avgMoneySpent) {
        this.roomType = roomType;
        this.reservationCount = reservationCount;
        this.avgMoneySpent = avgMoneySpent;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(int reservationCount) {
        this.reservationCount = reservationCount;
    }

    public double getAvgMoneySpent() {
        return avgMoneySpent;
    }

    public void setAvgMoneySpent(double avgMoneySpent) {
        this.avgMoneySpent = avgMoneySpent;
    }
}
