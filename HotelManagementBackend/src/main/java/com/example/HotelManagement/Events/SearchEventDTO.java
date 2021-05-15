package com.example.HotelManagement.Events;

public class SearchEventDTO {
    private String eventName;
    private Long lowerLimit;
    private Long upperLimit;

    public SearchEventDTO(String eventName, Long lowerLimit, Long upperLimit) {
        this.eventName = eventName;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Long lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Long getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Long upperLimit) {
        this.upperLimit = upperLimit;
    }
}
