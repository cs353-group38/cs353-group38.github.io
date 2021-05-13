package com.example.HotelManagement.SecurityStaffOperations;

import java.util.List;

public class ViewAllSecurityStaffDTO {
    List<SecurityStaffDTO> securityStaffDTOList;

    public ViewAllSecurityStaffDTO(List<SecurityStaffDTO> securityStaffDTOList) {
        this.securityStaffDTOList = securityStaffDTOList;
    }

    public List<SecurityStaffDTO> getSecurityStaffDTOList() {
        return securityStaffDTOList;
    }

    public void setSecurityStaffDTOList(List<SecurityStaffDTO> securityStaffDTOList) {
        this.securityStaffDTOList = securityStaffDTOList;
    }
}
