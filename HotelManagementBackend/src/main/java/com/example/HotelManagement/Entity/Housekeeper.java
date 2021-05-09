package com.example.HotelManagement.Entity;

import java.math.BigDecimal;

public class Housekeeper extends Employee {
    public Housekeeper(int id, String firstname, String lastname, String email, String password, String phone, String address, String gender, Long date_of_birth, BigDecimal salary, Long employment_date, int annual_leave) {
        super(id, firstname, lastname, email, password, phone, address, gender, date_of_birth, salary, employment_date, annual_leave);
    }
}
