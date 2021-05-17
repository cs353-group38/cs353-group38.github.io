package com.example.HotelManagement.SignUp;

import java.util.List;

public class RoomTypeReportDTO {
    private List<RoomTypeDTO> roomTypeDTOList;

    public RoomTypeReportDTO(List<RoomTypeDTO> roomTypeDTOList) {
        this.roomTypeDTOList = roomTypeDTOList;
    }

    public List<RoomTypeDTO> getRoomTypeDTOList() {
        return roomTypeDTOList;
    }

    public void setRoomTypeDTOList(List<RoomTypeDTO> roomTypeDTOList) {
        this.roomTypeDTOList = roomTypeDTOList;
    }
}
