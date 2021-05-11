package com.example.HotelManagement.Events;

public class EvaluateEmployeeDTO {
    private int employeeId;
    private int eventId;
    private int mgrId;
    private String status;

    public EvaluateEmployeeDTO(int employeeId, int eventId, int mgrId, String status) {
        this.employeeId = employeeId;
        this.eventId = eventId;
        this.mgrId = mgrId;
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getMgrId() {
        return mgrId;
    }

    public void setMgrId(int mgrId) {
        this.mgrId = mgrId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
