package com.example.HotelManagement.Events;

public class ParticipantDTO {
    private int participantId;
    private String firstName;
    private String lastName;

    public ParticipantDTO(int participantId, String firstName, String lastName) {
        this.participantId = participantId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
