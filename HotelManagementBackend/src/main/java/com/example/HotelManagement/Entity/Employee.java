package com.example.HotelManagement.Entity;

import java.math.BigDecimal;

public class Employee extends User{

    private BigDecimal salary;

    private Long employment_date;

    private int annual_leave;


    public Employee(int id, String firstname, String lastname, String email, String password, String phone,
                    String address, String gender, Long date_of_birth, BigDecimal salary,
                    Long employment_date, int annual_leave) {
        super(id,firstname,lastname,email,password,phone,address,gender,date_of_birth);
        this.salary = salary;


        this.employment_date = employment_date;
        this.annual_leave = annual_leave;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Long getEmployment_date() {
        return employment_date;
    }

    public void setEmployment_date(Long employment_date) {
        this.employment_date = employment_date;
    }

    public int getAnnual_leave() {
        return annual_leave;
    }

    public void setAnnual_leave(int annual_leave) {
        this.annual_leave = annual_leave;
    }
}
