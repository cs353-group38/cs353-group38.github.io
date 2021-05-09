package com.example.HotelManagement.Reserve;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.Entity.User;
import com.example.HotelManagement.SignUp.UserInsertion;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;

@Service
public class MakeReservations {

    private final DatabaseConnection databaseConnection;
    private final UserInsertion userInsertion;

    @Autowired
    public MakeReservations(DatabaseConnection databaseConnection, UserInsertion userInsertion) {
        this.databaseConnection = databaseConnection;
        this.userInsertion = userInsertion;
    }

    public MessageResponse makeReservation(MakeReservationDTO makeReservationDTO) {
        String query;

        int id = generateId();

        if( makeReservationDTO.getCheckOutDate() < makeReservationDTO.getCheckInDate()) {
            return new MessageResponse("Checkout date must be later than checkin",MessageType.ERROR);
        }

        query = "INSERT INTO Reservation VALUES (" + id + ", " + makeReservationDTO.getGuestId()
                + ", " + makeReservationDTO.getRoomNo() + ", '" + makeReservationDTO.getBuildingNo() + "', " +
                makeReservationDTO.getCheckInDate() + ", " + makeReservationDTO.getCheckOutDate() + ");";

        if( userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Insertion into Reservation failed",MessageType.ERROR);
        }

        return new MessageResponse("Insertion successful",MessageType.SUCCESS);
    }

    private int generateId() {
        int id;

        //find an unused id
        do {
            id = (int) (Math.random() * 100000);
        } while (selectReservationById(id));
        return id;
    }

    private boolean selectReservationById(int id) {
        String query = "SELECT *\n" +
                "FROM Reservation\n" +
                "WHERE id = " + id + ";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);

        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            resultSet.next();
            resultSet.getString("reservation_id");
            connection.close();
            return true;
        } catch (Exception e) {
            try {
                connection.close();
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
                return false;
            }
            System.out.println(e.getMessage());
            return false;
        }
    }
}
