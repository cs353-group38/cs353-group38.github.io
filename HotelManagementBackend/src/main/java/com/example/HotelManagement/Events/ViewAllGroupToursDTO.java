package com.example.HotelManagement.Events;

import java.util.List;

public class ViewAllGroupToursDTO {
    private List<ViewGroupTourDTO> viewGroupTourDTOList;

    public ViewAllGroupToursDTO(List<ViewGroupTourDTO> viewGroupTourDTOList) {
        this.viewGroupTourDTOList = viewGroupTourDTOList;
    }

    public List<ViewGroupTourDTO> getViewGroupTourDTOList() {
        return viewGroupTourDTOList;
    }

    public void setViewGroupTourDTOList(List<ViewGroupTourDTO> viewGroupTourDTOList) {
        this.viewGroupTourDTOList = viewGroupTourDTOList;
    }
}
