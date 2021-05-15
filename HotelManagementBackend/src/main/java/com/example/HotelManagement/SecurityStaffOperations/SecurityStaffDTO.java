package com.example.HotelManagement.SecurityStaffOperations;

public class SecurityStaffDTO {
    private int ssId;
    private String firstName;
    private String lastName;
    private String securityRank;
    private String weapon;

    public SecurityStaffDTO(int ssId, String firstName, String lastName, String securityRank, String weapon) {
        this.ssId = ssId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.securityRank = securityRank;
        this.weapon = weapon;
    }

    public int getSsId() {
        return ssId;
    }

    public void setSsId(int ssId) {
        this.ssId = ssId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
