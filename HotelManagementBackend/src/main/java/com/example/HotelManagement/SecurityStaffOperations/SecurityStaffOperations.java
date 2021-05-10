package com.example.HotelManagement.SecurityStaffOperations;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.SignUp.UserFetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class SecurityStaffOperations {

    private DatabaseConnection databaseConnection;
    private UserFetch userFetch;

    @Autowired
    public SecurityStaffOperations(DatabaseConnection databaseConnection, UserFetch userFetch) {
        this.databaseConnection = databaseConnection;
        this.userFetch = userFetch;
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
    public MessageResponse assignSecurityWalk(int mgrId, int ssId, String buildingNo, Long startDate, Long endDate) throws SQLException {
        String query;
        if(userFetch.selectSecurityStaffById(ssId) == null)
            return new MessageResponse("No such security staff.", MessageType.ERROR);
        if(userFetch.selectManagerById(mgrId) == null)
            return new MessageResponse("No such manager.", MessageType.ERROR);
        if(!userFetch.buildingExists(buildingNo))
            return new MessageResponse("No such building.", MessageType.ERROR);

        query = "INSERT INTO Security_Walk VALUES (" + mgrId + ", " + ssId + ", '" + buildingNo + "', " +
                startDate + ", " + endDate + ");";
        return executeUpdate(query);
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
