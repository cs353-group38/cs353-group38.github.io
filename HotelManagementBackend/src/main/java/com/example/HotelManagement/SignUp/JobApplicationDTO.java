package com.example.HotelManagement.SignUp;

public class JobApplicationDTO {

    private int candidateId;
    private String position;

    public JobApplicationDTO(int candidateId, String position) {
        this.candidateId = candidateId;
        this.position = position;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
