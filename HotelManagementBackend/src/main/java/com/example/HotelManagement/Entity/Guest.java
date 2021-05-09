package com.example.HotelManagement.Entity;

import java.math.BigDecimal;

public class Guest extends User{

    private BigDecimal money_spent;


    public Guest(int id, String firstname, String lastname, String email, String password, String phone, String address, String gender, Long date_of_birth, BigDecimal money_spent) {
        super(id, firstname, lastname, email, password, phone, address, gender, date_of_birth);
        this.money_spent = money_spent;
    }

    public BigDecimal getMoney_spent() {
        return money_spent;
    }

    public void setMoney_spent(BigDecimal money_spent) {
        this.money_spent = money_spent;
    }
}
