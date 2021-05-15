package com.example.HotelManagement.SecurityStaffOperations;

public class ViewSecurityWalkDTO extends SecurityWalkDTO{
    private String ssFirstName;
    private String ssLastName;
    private String mgrFirstName;
    private String mgrLastName;
    private String securityRank;
    private String weapon;

    public ViewSecurityWalkDTO(int mgrId, int ssId, String buildingNo, Long startDate, Long endDate, String ssFirstName,
                               String ssLastName, String mgrFirstName, String mgrLastName, String securityRank, String weapon) {
        super(mgrId, ssId, buildingNo, startDate, endDate);
        this.ssFirstName = ssFirstName;
        this.ssLastName = ssLastName;
        this.mgrFirstName = mgrFirstName;
        this.mgrLastName = mgrLastName;
        this.securityRank = securityRank;
        this.weapon = weapon;
    }

    public String getSsFirstName() {
        return ssFirstName;
    }

    public void setSsFirstName(String ssFirstName) {
        this.ssFirstName = ssFirstName;
    }

    public String getSsLastName() {
        return ssLastName;
    }

    public void setSsLastName(String ssLastName) {
        this.ssLastName = ssLastName;
    }

    public String getMgrFirstName() {
        return mgrFirstName;
    }

    public void setMgrFirstName(String mgrFirstName) {
        this.mgrFirstName = mgrFirstName;
    }

    public String getMgrLastName() {
        return mgrLastName;
    }

    public void setMgrLastName(String mgrLastName) {
        this.mgrLastName = mgrLastName;
    }

    public String getSecurityRank() {
        return securityRank;
    }

    public void setSecurityRank(String securityRank) {
        this.securityRank = securityRank;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}
