package com.example.HotelManagement.Events;

import java.util.List;

public class ViewGroupTourDTO extends CreateGroupToursDTO{
    private List<ParticipantDTO> participantDTOList;

    public ViewGroupTourDTO(String eventName, String locationName, Long startDate, Long endDate, int minAge, int quota,
                            String description, int mgrId, double price, String organizerName, String tourVehicle,
                            int distanceToCover, List<ParticipantDTO> participantDTOList) {
        super(eventName, locationName, startDate, endDate, minAge, quota, description, mgrId, price, organizerName, tourVehicle, distanceToCover);
        this.participantDTOList = participantDTOList;
    }

    public List<ParticipantDTO> getParticipantDTOList() {
        return participantDTOList;
    }

    public void setParticipantDTOList(List<ParticipantDTO> participantDTOList) {
        this.participantDTOList = participantDTOList;
    }
}
