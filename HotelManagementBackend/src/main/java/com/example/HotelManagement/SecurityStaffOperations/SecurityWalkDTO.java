package com.example.HotelManagement.SecurityStaffOperations;

public class SecurityWalkDTO {
    private int mgrId;
    private int ssId;
    private String buildingNo;
    private Long startDate;
    private Long endDate;

    public SecurityWalkDTO(int mgrId, int ssId, String buildingNo, Long startDate, Long endDate) {
        this.mgrId = mgrId;
        this.ssId = ssId;
        this.buildingNo = buildingNo;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getMgrId() {
        return mgrId;
    }

    public void setMgrId(int mgrId) {
        this.mgrId = mgrId;
    }

    public int getSsId() {
        return ssId;
    }

    public void setSsId(int ssId) {
        this.ssId = ssId;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
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
}
