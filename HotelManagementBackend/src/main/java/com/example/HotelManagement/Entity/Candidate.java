package com.example.HotelManagement.Entity;

public class Candidate extends User{

    private String coverLetter;

    public Candidate(int id, String firstname, String lastname, String email, String password, String phone, String address, String gender, Long date_of_birth, String coverLetter) {
        super(id, firstname, lastname, email, password, phone, address, gender, date_of_birth);
        this.coverLetter = coverLetter;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}
