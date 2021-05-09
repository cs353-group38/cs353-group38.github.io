package com.example.HotelManagement.Entity;

import java.math.BigDecimal;

public class SecurityStaff extends Employee {

    private String securityRank;

    private String weapon;

    public SecurityStaff(int id, String firstname, String lastname, String email, String password, String phone, String address, String gender, Long date_of_birth, BigDecimal salary, Long employment_date, int annual_leave, String securityRank, String weapon) {
        super(id, firstname, lastname, email, password, phone, address, gender, date_of_birth, salary, employment_date, annual_leave);
        this.securityRank = securityRank;
        this.weapon = weapon;
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
