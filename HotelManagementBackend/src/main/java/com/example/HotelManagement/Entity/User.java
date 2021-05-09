package com.example.HotelManagement.Entity;

public class User {

    private int id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String phone;

    private String address;

    private String gender;

    private Long date_of_birth;

    public User(int id, String firstname, String lastname, String email, String password, String phone,
                String address, String gender, Long date_of_birth) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Long date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
