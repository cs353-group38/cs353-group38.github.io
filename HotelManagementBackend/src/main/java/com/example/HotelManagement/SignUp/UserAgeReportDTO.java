package com.example.HotelManagement.SignUp;

import java.util.List;

public class UserAgeReportDTO {

    private List<UserAgeDTO> userAgeDTOList;

    public UserAgeReportDTO(List<UserAgeDTO> userAgeDTOList) {
        this.userAgeDTOList = userAgeDTOList;
    }

    public List<UserAgeDTO> getUserAgeDTOList() {
        return userAgeDTOList;
    }

    public void setUserAgeDTOList(List<UserAgeDTO> userAgeDTOList) {
        this.userAgeDTOList = userAgeDTOList;
    }
}
