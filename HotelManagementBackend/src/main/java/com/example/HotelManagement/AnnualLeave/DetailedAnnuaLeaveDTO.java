package com.example.HotelManagement.AnnualLeave;

public class DetailedAnnuaLeaveDTO {

    private Long leaveDate;

    private int days;

    private String managerName;

    private Long approveDate;

    private String status;

    private String employeeName;

    private int employeeId;

    public DetailedAnnuaLeaveDTO(Long leaveDate, int days, String managerName, Long approveDate, String status, String employeeName, int employeeId) {
        this.leaveDate = leaveDate;
        this.days = days;
        this.managerName = managerName;
        this.approveDate = approveDate;
        this.status = status;
        this.employeeName = employeeName;
        this.employeeId = employeeId;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
