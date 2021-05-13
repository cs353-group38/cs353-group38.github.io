package com.example.HotelManagement.SecurityStaffOperations;

import java.util.List;

public class ViewAllSecurityWalksDTO {
    private List<ViewSecurityWalkDTO> viewSecurityWalkDTOList;

    public ViewAllSecurityWalksDTO(List<ViewSecurityWalkDTO> viewSecurityWalkDTOList) {
        this.viewSecurityWalkDTOList = viewSecurityWalkDTOList;
    }

    public List<ViewSecurityWalkDTO> getViewSecurityWalkDTOList() {
        return viewSecurityWalkDTOList;
    }

    public void setViewSecurityWalkDTOList(List<ViewSecurityWalkDTO> viewSecurityWalkDTOList) {
        this.viewSecurityWalkDTOList = viewSecurityWalkDTOList;
    }
}
