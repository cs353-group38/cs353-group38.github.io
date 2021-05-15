package com.example.HotelManagement.Events;

import java.util.List;

public class ViewTrainingProgramDTO extends CreateEventDTO{
    private List<ParticipantDTO> applicantList;
    private List<ParticipantDTO> participantList;

    public ViewTrainingProgramDTO(String eventName, String locationName, Long startDate, Long endDate, int minAge,
                                  int quota, String description, int mgrId, List<ParticipantDTO> applicantList,
                                  List<ParticipantDTO> participantList) {
        super(eventName, locationName, startDate, endDate, minAge, quota, description, mgrId);
        this.applicantList = applicantList;
        this.participantList = participantList;
    }

    public List<ParticipantDTO> getApplicantList() {
        return applicantList;
    }

    public void setApplicantList(List<ParticipantDTO> applicantList) {
        this.applicantList = applicantList;
    }

    public List<ParticipantDTO> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<ParticipantDTO> participantList) {
        this.participantList = participantList;
    }
}
