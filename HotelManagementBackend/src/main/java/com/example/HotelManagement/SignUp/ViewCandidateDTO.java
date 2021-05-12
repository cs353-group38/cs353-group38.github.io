package com.example.HotelManagement.SignUp;

import com.example.HotelManagement.Entity.Candidate;

public class ViewCandidateDTO extends Candidate {
    private String position;
    private String status;

    public ViewCandidateDTO(int id, String firstname, String lastname, String email, String password, String phone,
                            String address, String gender, Long date_of_birth, String coverLetter, String position,
                            String status) {
        super(id, firstname, lastname, email, password, phone, address, gender, date_of_birth, coverLetter);
        this.position = position;
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
