package com.example.HotelManagement.Events;

public class CreateEventDTO {
    private String eventName;
    private String locationName;
    private Long startDate;
    private Long endDate;
    private int minAge;
    private int quota;
    private String description;
    private int mgrId;

    public CreateEventDTO(String eventName, String locationName, Long startDate, Long endDate, int minAge, int quota,
                          String description, int mgrId) {
        this.eventName = eventName;
        this.locationName = locationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minAge = minAge;
        this.quota = quota;
        this.description = description;
        this.mgrId = mgrId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMgrId() {
        return mgrId;
    }

    public void setMgrId(int mgrId) {
        this.mgrId = mgrId;
    }
}
