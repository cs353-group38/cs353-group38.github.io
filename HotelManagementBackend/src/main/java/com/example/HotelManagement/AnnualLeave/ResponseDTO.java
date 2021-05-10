package com.example.HotelManagement.AnnualLeave;

public class ResponseDTO {
    private int id;

    private Long leaveDate;

    private int managerId;

    private String status;

    public ResponseDTO(int id, Long leaveDate, int managerId, String status) {
        this.id = id;
        this.leaveDate = leaveDate;
        this.managerId = managerId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Long leaveDate) {
        this.leaveDate = leaveDate;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
