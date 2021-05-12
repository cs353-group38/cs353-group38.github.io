package com.example.HotelManagement.SignUp;

import java.util.List;

public class ViewAllCandidatesDTO {
    private List<ViewCandidateDTO> candidateDTOList;

    public ViewAllCandidatesDTO(List<ViewCandidateDTO> candidateDTOList) {
        this.candidateDTOList = candidateDTOList;
    }

    public List<ViewCandidateDTO> getCandidateDTOList() {
        return candidateDTOList;
    }

    public void setCandidateDTOList(List<ViewCandidateDTO> candidateDTOList) {
        this.candidateDTOList = candidateDTOList;
    }
}
