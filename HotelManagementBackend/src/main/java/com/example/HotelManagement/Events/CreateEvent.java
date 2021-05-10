package com.example.HotelManagement.Events;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.Entity.User;
import com.example.HotelManagement.SignUp.UserFetch;
import com.example.HotelManagement.SignUp.UserInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CreateEvent {

    private DatabaseConnection databaseConnection;
    private UserFetch userFetch;
    private UserInsertion userInsertion;

    @Autowired
    public CreateEvent(DatabaseConnection databaseConnection, UserFetch userFetch, UserInsertion userInsertion) {
        this.databaseConnection = databaseConnection;
        this.userFetch = userFetch;
        this.userInsertion = userInsertion;
    }

    /**
     * Used for inserting a general event in the Event table.
     * @param id event id
     * @param eventName event name
     * @param locationName location name
     * @param startDate start date as long
     * @param endDate end date as long
     * @param minAge min age to attend the event
     * @param quota quota
     * @param description description of the event
     * @param mgrId id of the manager that created the event
     * @return Message Response
     */
    private MessageResponse createEvent(int id, String eventName, String locationName, Long startDate, Long endDate,
                                       int minAge, int quota, String description, int mgrId) {
        String query;

        //preconditions
        if(entryExists("Event", id, "event_id", null))
            return new MessageResponse("This event already exists", MessageType.ERROR);

        if(!entryExists("Manager", mgrId, "id", null))
            return new MessageResponse("Manager not found.", MessageType.ERROR);

        if(!entryExists("Location", 0, "name", locationName))
            return new MessageResponse("Location not found.", MessageType.ERROR);

        query = "INSERT INTO Event VALUES(" + id + ", '" + eventName + "', '" + locationName + "', " +
                startDate + ", " + endDate + ", " + minAge + ", " + quota + ", '" + description + "', " + mgrId + ");";

        if(userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Insertion into Events failed",MessageType.ERROR);
        }

        return new MessageResponse("Insertion successful",MessageType.SUCCESS);
    }

    /**
     * This method inserts and entry in the Training Program table using the given information
     * @param eventName event name
     * @param locationName location name
     * @param startDate start date
     * @param endDate end date
     * @param minAge min age
     * @param quota quota
     * @param description description
     * @param mgrId manager id
     * @return Message Response
     */
    public MessageResponse createTrainingProgram(String eventName, String locationName, Long startDate, Long endDate,
                                                  int minAge, int quota, String description, int mgrId) {
        String query;
        int id = generateEventId();
        if(createEvent(id, eventName, locationName, startDate, endDate, minAge, quota, description, mgrId).getMessageType().equals(MessageType.ERROR))
            return new MessageResponse("Training Program insertion failed.", MessageType.ERROR);

        query = "INSERT INTO Training_Program VALUES (" + id + ");";
        if(userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Insertion into Training Program failed",MessageType.ERROR);
        }

        return new MessageResponse("Insertion successful",MessageType.SUCCESS);
    }

    /**
     * Checks to see if an entry already exists in a table
     * @param tableName name of the table to be checked
     * @param id integer id of the entry. If the primary key is not an integer but a string, enter 0.
     * @param idColumnName name of the id column: Example: name, id, event_id...
     * @param stringKey If the primary key is not an entry but a string, enter the key here. Otherwise enter null
     * @return true if entry exists, false if not.
     */
    public boolean entryExists(String tableName, int id, String idColumnName, String stringKey){
        String query;
        boolean result;
        if(stringKey == null) {
            query = "SELECT *\n" +
                    "FROM " + tableName + "\n" +
                    "WHERE " + idColumnName + " = " + id + ";";
        }
        else{
            query = "SELECT *\n" +
                    "FROM " + tableName + "\n" +
                    "WHERE " + idColumnName + " = '" + stringKey + "';";
        }

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            result = resultSet.next();
        }
        catch(SQLException e) {
            try {
                connection.close();
            }
            catch (SQLException e1) {
                throw new IllegalArgumentException("Error when closing the connection");        //SKETCHY
            }
            return false;
        }
        try {
            connection.close();
        }
        catch (SQLException e1) {
            throw new IllegalArgumentException("Error when closing the connection");        //SKETCHY
        }
        return result;
    }

    /**
     * Generates an id that does not exist in the Event table.
     * @return The id
     */
    private int generateEventId() {
        int id;

        //find an unused id
        do {
            id = (int) (Math.random() * 100000);
        } while (entryExists("Event", id, "event_id", null));
        return id;
    }
}
