package com.example.HotelManagement.Reserve;

public class MakeReservationDTO {

    private int roomNo;

    private int guestId;

    private String buildingNo;

    private Long checkInDate;

    private Long checkOutDate;

    public MakeReservationDTO(int roomNo, int guestId, String buildingNo, Long checkInDate, Long checkOutDate) {
        this.roomNo = roomNo;
        this.guestId = guestId;
        this.buildingNo = buildingNo;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
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

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
}
