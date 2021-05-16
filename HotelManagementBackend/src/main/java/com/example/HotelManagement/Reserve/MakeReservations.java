package com.example.HotelManagement.Reserve;

import com.example.HotelManagement.Comment.CommentDTO;
import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.SignUp.UserInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class MakeReservations {

    private final DatabaseConnection databaseConnection;
    private final UserInsertion userInsertion;

    @Autowired
    public MakeReservations(DatabaseConnection databaseConnection, UserInsertion userInsertion) {
        this.databaseConnection = databaseConnection;
        this.userInsertion = userInsertion;
    }

    /**
     * Makes a reservation
     * @param makeReservationDTO
     * @return
     */
    public MessageResponse makeReservation(MakeReservationDTO makeReservationDTO){

        if( makeReservationDTO.getCheckOutDate() < makeReservationDTO.getCheckInDate()) {
            return new MessageResponse("Checkout date must be later than checkin",MessageType.ERROR);
        }

        Integer roomId = null;

        String buildingNo = "";

        String query = "SELECT * FROM ( Room as r NATURAL JOIN Room_Type rt NATURAL JOIN Reservation res)" +
                " WHERE r.type = '"+ makeReservationDTO.getRoomType() +"' AND (r.room_no,r.building_no) NOT IN ("+
                "SELECT res1.room_no,res1.building_no FROM Reservation as res1 " +
                "WHERE (res1.check_out_date >= " + makeReservationDTO.getCheckInDate() +
                " AND res1.check_in_date <= " + makeReservationDTO.getCheckInDate() + " ) OR (res1.check_out_date >= " + makeReservationDTO.getCheckOutDate() +
                " AND res1.check_in_date <= " + makeReservationDTO.getCheckOutDate() + " ) OR (res1.check_out_date <= " + makeReservationDTO.getCheckOutDate() +
                " AND res1.check_in_date >= " + makeReservationDTO.getCheckInDate() + ") );";


        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query,DatabaseConnection.FETCH);

        ResultSet rs = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            while ( rs.next()){
                roomId = rs.getInt("r.room_no");
                buildingNo = rs.getString("r.building_no");
            }
            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                return new MessageResponse("Cannot close connection",MessageType.ERROR);
            }
            return new MessageResponse("cannot do the whole fetching process",MessageType.ERROR);
        }
        if( roomId == null ){
            return new MessageResponse("No empty rooms",MessageType.ERROR);
        }
        int id = generateId();

        query = "INSERT INTO Reservation VALUES (" + id + ", " + makeReservationDTO.getGuestId()
                + ", " + roomId + ", '" + buildingNo + "', " +
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
                "WHERE reservation_id = " + id + ";";

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


    public LoginDTO login(String type, String email, String password) throws Exception {

        String query = "";

        if(type.equals("GUEST")){
            query = "SELECT * " +
                    "FROM Guests s NATURAL JOIN Users u " +
                    " WHERE u.password = '" + password + "' AND u.email = '" + email + "';";
        }
        else if( type.equals("MANAGER")){
            query = "SELECT * " +
                    "FROM Manager s NATURAL JOIN Users u" +
                    " WHERE u.password = '" + password + "' AND u.email = '" + email + "';";
        }
        else if( type.equals("HOUSEKEEPER")){
            query = "SELECT * " +
                    "FROM Housekeeper s NATURAL JOIN Users u" +
                    " WHERE u.password = '" + password + "' AND u.email = '" + email + "';";
        }
        else if( type.equals("SECURITY_STAFF")){
            query = "SELECT * " +
                    "FROM Security_Staff s NATURAL JOIN Users u" +
                    " WHERE u.password = '" + password + "' AND u.email = '" + email + "';";
        }

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);

        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            resultSet.next();
            LoginDTO loginDTO = new LoginDTO(resultSet.getInt("id"),"");
            connection.close();
            return loginDTO;
        } catch (Exception e) {
            try {
                connection.close();
            } catch (Exception e1) {
                throw new Exception("No such user");
            }
            throw new Exception("No such user");
        }
    }
}
