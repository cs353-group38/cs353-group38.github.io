package com.example.HotelManagement.SignUp;

import com.example.HotelManagement.Entity.Candidate;

public class ViewCandidateDTO extends Candidate {
    private String position;
    private String status;
    private String recruiterFirstName;
    private String recruiterLastName;

    public ViewCandidateDTO(int id, String firstname, String lastname, String email, String password, String phone,
                            String address, String gender, Long date_of_birth, String coverLetter, String position,
                            String status, String recruiterFirstName, String recruiterLastName) {
        super(id, firstname, lastname, email, password, phone, address, gender, date_of_birth, coverLetter);
        this.position = position;
        this.status = status;
        this.recruiterFirstName = recruiterFirstName;
        this.recruiterLastName = recruiterLastName;
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

    public String getRecruiterFirstName() {
        return recruiterFirstName;
    }

    public void setRecruiterFirstName(String recruiterFirstName) {
        this.recruiterFirstName = recruiterFirstName;
    }

    public String getRecruiterLastName() {
        return recruiterLastName;
    }

    public void setRecruiterLastName(String recruiterLastName) {
        this.recruiterLastName = recruiterLastName;
    }
}
