package com.example.HotelManagement.SignUp;

public class EvaluateJobApplicationDTO extends JobApplicationDTO{
    private int recruiterId;
    private String status;  //"APPROVED" or "REJECTED"

    public EvaluateJobApplicationDTO(int candidateId, String position, int recruiterId, String status) {
        super(candidateId, position);
        this.recruiterId = recruiterId;
        this.status = status;
    }

    public int getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(int recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
