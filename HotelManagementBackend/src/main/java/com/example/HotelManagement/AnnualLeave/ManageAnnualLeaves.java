package com.example.HotelManagement.AnnualLeave;

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
public class ManageAnnualLeaves {

    private final DatabaseConnection databaseConnection;
    private final UserInsertion userInsertion;

    @Autowired
    public ManageAnnualLeaves(DatabaseConnection databaseConnection, UserInsertion userInsertion) {
        this.databaseConnection = databaseConnection;
        this.userInsertion = userInsertion;
    }

    public List<AnnualLeaveDTO> viewLeaves(int id) throws Exception {
        List<AnnualLeaveDTO> leaves = new ArrayList<>();

        String query = "SELECT * FROM Leave_Request_Form lrf LEFT JOIN Users u  ON lrf.manager_id = u.id WHERE lrf.id = "+id+";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query,DatabaseConnection.FETCH);

        ResultSet rs = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            while ( rs.next()){
                AnnualLeaveDTO annualLeaveDTO =
                        new AnnualLeaveDTO(
                                rs.getLong("lrf.leave_date"),
                                rs.getInt("lrf.days"),
                                rs.getString("u.firstname") + " " + rs.getString("u.lastname"),
                                rs.getLong("lrf.approve_date"),
                                rs.getString("lrf.status")
                        );

                try{
                    leaves.add(annualLeaveDTO);
                }catch (Exception e){
                    throw new Exception("Result set read problem");
                }
            }
            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                throw new Exception("Cannot close connection");
            }
            throw new Exception("cannot do the whole fetching process");
        }

        return leaves;
    }

    public MessageResponse leaveRequest(int id, AnnualLeaveDTO annualLeaveDTO) throws Exception {

        int days = getAnnualDays(id);
        if( days < annualLeaveDTO.getDays()){
            return new MessageResponse("You do not have that much days left",MessageType.ERROR);
        }

        String query;

        query = "INSERT INTO Leave_Request_Form VALUES ( " + id + ", " + annualLeaveDTO.getLeaveDate() + ", " + annualLeaveDTO.getDays()
                + ", null, -1, 'WAITING' );";

        if( userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Inserting failed",MessageType.ERROR);
        }

        String query2 = "UPDATE Employee e SET e.annual_leave = " + ( days-annualLeaveDTO.getDays() ) + " WHERE e.id = "+ id +";";

        if( userInsertion.executeUpdate(query2).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Updating failed",MessageType.ERROR);
        }

        return new MessageResponse("success",MessageType.SUCCESS);

    }

    public int getAnnualDays( int id ) throws Exception {
        String query = "SELECT e.annual_leave FROM Employee e WHERE e.id = " + id + ";";
        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);

        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];
        int result = -1;
        try {
            resultSet.next();
            result = resultSet.getInt("e.annual_leave");
            connection.close();
            return result;
        } catch (Exception e) {
            try {
                connection.close();
            } catch (Exception e1) {
                throw new Exception("Connection cannot be closed");
            }
            throw new Exception("Cannot fetch days");
        }
    }


    public List<DetailedAnnuaLeaveDTO> viewAllLeaveRequests() throws Exception {
        List<DetailedAnnuaLeaveDTO> leaves = new ArrayList<>();

        String query = "SELECT * FROM ( Leave_Request_Form lrf NATURAL JOIN Users u2 ) LEFT JOIN Users u1 ON lrf.manager_id = u1.id;";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query,DatabaseConnection.FETCH);

        ResultSet rs = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            while ( rs.next()){
                DetailedAnnuaLeaveDTO detailedAnnuaLeaveDTO =
                        new DetailedAnnuaLeaveDTO(
                                rs.getLong("lrf.leave_date"),
                                rs.getInt("lrf.days"),
                                rs.getString("u1.firstname") + " " + rs.getString("u1.lastname"),
                                rs.getLong("lrf.approve_date"),
                                rs.getString("lrf.status"),
                                rs.getString("u2.firstname") + " " + rs.getString("u2.lastname"),
                                rs.getInt("lrf.id")
                        );

                try{
                    leaves.add(detailedAnnuaLeaveDTO);
                }catch (Exception e){
                    throw new Exception("Result set read problem");
                }
            }
            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                throw new Exception("Cannot close connection");
            }
            throw new Exception("cannot do the whole fetching process");
        }

        return leaves;
    }

    public MessageResponse respond(ResponseDTO responseDTO) {
        String query;

        query = "UPDATE Leave_Request_Form lrf SET lrf.manager_id = " +responseDTO.getManagerId()+
                ", lrf.status = '" + responseDTO.getStatus() + "', lrf.approve_date = " + System.currentTimeMillis() +
                " WHERE lrf.id = " + responseDTO.getId() + " AND lrf.leave_date = " + responseDTO.getLeaveDate()+";";

        if( userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Update failed",MessageType.ERROR);
        }

        return new MessageResponse("success",MessageType.SUCCESS);
    }
}
