package com.example.HotelManagement.SecurityStaffOperations;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.Events.CreateEvent;
import com.example.HotelManagement.SignUp.UserFetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityStaffOperations {

    private DatabaseConnection databaseConnection;
    private UserFetch userFetch;
    private CreateEvent createEvent;

    @Autowired
    public SecurityStaffOperations(DatabaseConnection databaseConnection, UserFetch userFetch, CreateEvent createEvent) {
        this.databaseConnection = databaseConnection;
        this.userFetch = userFetch;
        this.createEvent = createEvent;
    }

    /**
     * Assigns a security staff to a security walk
     * @param mgrId Unique and existing manager id
     * @param ssId Unique and existing security staff id
     * @param buildingNo Unique and existing building number
     * @param startDate Start date and hour as Long
     * @param endDate End date and hour as Long
     * @return Message response indicating success or fail.
     */
    public MessageResponse assignSecurityWalk(int mgrId, int ssId, String buildingNo, Long startDate, Long endDate) {
        String query;
        if(userFetch.selectSecurityStaffById(ssId) == null)
            return new MessageResponse("No such security staff.", MessageType.ERROR);
        if(userFetch.selectManagerById(mgrId) == null)
            return new MessageResponse("No such manager.", MessageType.ERROR);
        if(!createEvent.entryExists("Building", 0, "building_no", buildingNo))
            return new MessageResponse("No such building.", MessageType.ERROR);

        query = "INSERT INTO Security_Walk VALUES (" + mgrId + ", " + ssId + ", '" + buildingNo + "', " +
                startDate + ", " + endDate + ");";
        return executeUpdate(query);
    }

    public ViewSecurityWalkDTO viewSecurityWalk(SecurityWalkDTO securityWalkDTO) {
        String query;
        Object[] resultArr = null;
        ResultSet resultSet;
        Connection connection;
        ViewSecurityWalkDTO dto = null;

        query = "SELECT manager_id, security_staff_id, building_no, start_date, end_date, ss.firstname AS ss_firstname, ss.lastname AS ss_lastname,\n" +
                "       mgr.firstname AS mgr_firstname, mgr.lastname AS mgr_lastname, security_rank, weapon\n" +
                "FROM Security_Walk, Users ss NATURAL JOIN Security_Staff, Users mgr\n" +
                "WHERE manager_id = " + securityWalkDTO.getMgrId() + " AND security_staff_id = " + securityWalkDTO.getSsId() + " AND building_no = '" + securityWalkDTO.getBuildingNo() + "' AND start_date = " + securityWalkDTO.getStartDate() + " AND end_date = " + securityWalkDTO.getEndDate() + " AND\n" +
                "      mgr.id = manager_id AND security_staff_id = ss.id;";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            if(resultSet.next()) {
                dto = new ViewSecurityWalkDTO(
                        resultSet.getInt("manager_id"),
                        resultSet.getInt("security_staff_id"),
                        resultSet.getString("building_no"),
                        resultSet.getLong("start_date"),
                        resultSet.getLong("end_date"),
                        resultSet.getString("ss_firstname"),
                        resultSet.getString("ss_lastname"),
                        resultSet.getString("mgr_firstname"),
                        resultSet.getString("mgr_lastname"),
                        resultSet.getString("security_rank"),
                        resultSet.getString("weapon")
                );
            }
            else
                throw new IllegalArgumentException("No such security walk");

            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                throw new IllegalArgumentException("Connection failure.");
            }
            throw new IllegalArgumentException("Connection failure.");
        }

        return dto;
    }

    /**
     * Returns all the security walks in the system
     * @return dto object
     */
    public ViewAllSecurityWalksDTO viewAllSecurityWalks() {
        String query;
        List<ViewSecurityWalkDTO> dtoList = new ArrayList<>();
        Object[] resultArr = null;
        ResultSet resultSet;
        Connection connection;

        query = "SELECT manager_id, security_staff_id, building_no, start_date, end_date, ss.firstname AS ss_firstname, ss.lastname AS ss_lastname,\n" +
                "       mgr.firstname AS mgr_firstname, mgr.lastname AS mgr_lastname, security_rank, weapon\n" +
                "FROM Security_Walk, Users ss NATURAL JOIN Security_Staff, Users mgr\n" +
                "WHERE manager_id = mgr.id AND security_staff_id = ss.id AND end_date > UNIX_TIMESTAMP()\n" +
                "ORDER BY start_date, end_date;";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                ViewSecurityWalkDTO viewSecurityWalkDTO = new ViewSecurityWalkDTO(
                        resultSet.getInt("manager_id"),
                        resultSet.getInt("security_staff_id"),
                        resultSet.getString("building_no"),
                        resultSet.getLong("start_date"),
                        resultSet.getLong("end_date"),
                        resultSet.getString("ss_firstname"),
                        resultSet.getString("ss_lastname"),
                        resultSet.getString("mgr_firstname"),
                        resultSet.getString("mgr_lastname"),
                        resultSet.getString("security_rank"),
                        resultSet.getString("weapon")
                );
                dtoList.add(viewSecurityWalkDTO);
            }

            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                throw new IllegalArgumentException("Connection failure.");
            }
            throw new IllegalArgumentException("Connection failure.");
        }

        query = "SELECT manager_id, security_staff_id, building_no, start_date, end_date, ss.firstname AS ss_firstname, ss.lastname AS ss_lastname,\n" +
                "       mgr.firstname AS mgr_firstname, mgr.lastname AS mgr_lastname, security_rank, weapon\n" +
                "FROM Security_Walk, Users ss NATURAL JOIN Security_Staff, Users mgr\n" +
                "WHERE manager_id = mgr.id AND security_staff_id = ss.id AND end_date <= UNIX_TIMESTAMP()\n" +
                "ORDER BY start_date DESC;";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                ViewSecurityWalkDTO viewSecurityWalkDTO = new ViewSecurityWalkDTO(
                        resultSet.getInt("manager_id"),
                        resultSet.getInt("security_staff_id"),
                        resultSet.getString("building_no"),
                        resultSet.getLong("start_date"),
                        resultSet.getLong("end_date"),
                        resultSet.getString("ss_firstname"),
                        resultSet.getString("ss_lastname"),
                        resultSet.getString("mgr_firstname"),
                        resultSet.getString("mgr_lastname"),
                        resultSet.getString("security_rank"),
                        resultSet.getString("weapon")
                );
                dtoList.add(viewSecurityWalkDTO);
            }

            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                throw new IllegalArgumentException("Connection failure.");
            }
            throw new IllegalArgumentException("Connection failure.");
        }

        return new ViewAllSecurityWalksDTO(dtoList);
    }

    /**
     * Lists the security walks assigned to a particular security staff
     * @param ssId security staff id
     * @return dto object
     */
    public ViewAllSecurityWalksDTO viewSecurityStaffWalks(int ssId) {
        String query;
        List<ViewSecurityWalkDTO> dtoList = new ArrayList<>();
        Object[] resultArr = null;
        ResultSet resultSet;
        Connection connection;

        if(!createEvent.entryExists("Security_Staff", ssId, "id", null))
            throw new IllegalArgumentException("Security staff not found");

        query = "SELECT manager_id, security_staff_id, building_no, start_date, end_date, ss.firstname AS ss_firstname, ss.lastname AS ss_lastname,\n" +
                "       mgr.firstname AS mgr_firstname, mgr.lastname AS mgr_lastname, security_rank, weapon\n" +
                "FROM Security_Walk, Users ss NATURAL JOIN Security_Staff, Users mgr\n" +
                "WHERE manager_id = mgr.id AND security_staff_id = ss.id AND security_staff_id = " + ssId + " AND end_date > UNIX_TIMESTAMP()\n" +
                "ORDER BY start_date, end_date;";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                ViewSecurityWalkDTO viewSecurityWalkDTO = new ViewSecurityWalkDTO(
                        resultSet.getInt("manager_id"),
                        resultSet.getInt("security_staff_id"),
                        resultSet.getString("building_no"),
                        resultSet.getLong("start_date"),
                        resultSet.getLong("end_date"),
                        resultSet.getString("ss_firstname"),
                        resultSet.getString("ss_lastname"),
                        resultSet.getString("mgr_firstname"),
                        resultSet.getString("mgr_lastname"),
                        resultSet.getString("security_rank"),
                        resultSet.getString("weapon")
                );
                dtoList.add(viewSecurityWalkDTO);
            }

            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                throw new IllegalArgumentException("Connection failure.");
            }
            throw new IllegalArgumentException("Connection failure.");
        }

        query = "SELECT manager_id, security_staff_id, building_no, start_date, end_date, ss.firstname AS ss_firstname, ss.lastname AS ss_lastname,\n" +
                "       mgr.firstname AS mgr_firstname, mgr.lastname AS mgr_lastname, security_rank, weapon\n" +
                "FROM Security_Walk, Users ss NATURAL JOIN Security_Staff, Users mgr\n" +
                "WHERE manager_id = mgr.id AND security_staff_id = ss.id AND security_staff_id = " + ssId + " AND end_date <= UNIX_TIMESTAMP()\n" +
                "ORDER BY start_date DESC;";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                ViewSecurityWalkDTO viewSecurityWalkDTO = new ViewSecurityWalkDTO(
                        resultSet.getInt("manager_id"),
                        resultSet.getInt("security_staff_id"),
                        resultSet.getString("building_no"),
                        resultSet.getLong("start_date"),
                        resultSet.getLong("end_date"),
                        resultSet.getString("ss_firstname"),
                        resultSet.getString("ss_lastname"),
                        resultSet.getString("mgr_firstname"),
                        resultSet.getString("mgr_lastname"),
                        resultSet.getString("security_rank"),
                        resultSet.getString("weapon")
                );
                dtoList.add(viewSecurityWalkDTO);
            }

            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                throw new IllegalArgumentException("Connection failure.");
            }
            throw new IllegalArgumentException("Connection failure.");
        }

        return new ViewAllSecurityWalksDTO(dtoList);
    }

    public ViewAllSecurityStaffDTO viewAllSecurityStaff() {
        String query;
        List<SecurityStaffDTO> dtoList = new ArrayList<>();
        Object[] resultArr = null;
        ResultSet resultSet;
        Connection connection;

        query = "SELECT id, firstname, lastname, security_rank, weapon\n" +
                "FROM Users NATURAL JOIN Security_Staff\n" +
                "ORDER BY firstname;";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                SecurityStaffDTO securityStaffDTO = new SecurityStaffDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("security_rank"),
                        resultSet.getString("weapon")
                );
                dtoList.add(securityStaffDTO);
            }

            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                throw new IllegalArgumentException("Connection failure.");
            }
            throw new IllegalArgumentException("Connection failure.");
        }

        return new ViewAllSecurityStaffDTO(dtoList);
    }

    /**
     * Executes an update in the database. Be careful with the message responses when using this.
     * @param query query
     * @return Message response
     */
    private MessageResponse executeUpdate(String query) {
        try {
            databaseConnection.execute(query, DatabaseConnection.UPDATE);
        }
        catch (Exception e) {
            return new MessageResponse("Operation failed.", MessageType.ERROR);
        }
        return new MessageResponse("Operation successful.", MessageType.SUCCESS);
    }
}
