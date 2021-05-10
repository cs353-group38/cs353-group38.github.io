package com.example.HotelManagement.AnnualLeave;

public class AnnualLeaveDTO {
    private Long leaveDate;

    private int days;

    private String managerName;

    private Long approveDate;

    private String status;

    public AnnualLeaveDTO(Long leaveDate, int days, String managerName, Long approveDate, String status) {
        this.leaveDate = leaveDate;
        this.days = days;
        this.managerName = managerName;
        this.approveDate = approveDate;
        this.status = status;
    }

    public Long getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Long leaveDate) {
        this.leaveDate = leaveDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Long approveDate) {
        this.approveDate = approveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
