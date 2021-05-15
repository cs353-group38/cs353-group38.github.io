package com.example.HotelManagement.Events;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.SignUp.UserFetch;
import com.example.HotelManagement.SignUp.UserInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 * This class consists of the operations about joining an event
 */
@Service
public class BuyTicket {
    private UserFetch userFetch;
    private UserInsertion userInsertion;
    private CreateEvent createEvent;
    private DatabaseConnection databaseConnection;

    @Autowired
    public BuyTicket(UserFetch userFetch, UserInsertion userInsertion, CreateEvent createEvent, DatabaseConnection databaseConnection) {
        this.userFetch = userFetch;
        this.userInsertion = userInsertion;
        this.createEvent = createEvent;
        this.databaseConnection = databaseConnection;
    }

    /**
     * This method creates a ticket for the guest, increases the money_spent attribute of guest, decrements the quota
     * attribute of the event.
     * @param guestId id of the guest
     * @param eventId id of the event
     * @return Message response
     */
    public MessageResponse buyTicket(int guestId, int eventId) throws Exception {
        int ticketId;
        String query;
        double price = 0;
        int quota = 0;
        int guestAge = 0;
        Long guestDOB = null;      //date of birth
        int minAge = 0;
        Object[] arr;
        ResultSet rs = null;
        Connection connection = null;

        //preconditions
        if(!createEvent.entryExists("Guest_Activity", eventId, "event_id", null))
            return new MessageResponse("No such Guest Activity.", MessageType.ERROR);

        if(!createEvent.entryExists("Guests", guestId, "id", null))
            return new MessageResponse("No such Guest.", MessageType.ERROR);

        if(doublePrimaryEntryExists("Ticket", guestId, "guest_id", eventId, "event_id")) {
            return new MessageResponse("You cannot join the same activity twice.", MessageType.ERROR);
        }

        query = "SELECT date_of_birth\n" +
                "FROM Users\n" +
                "WHERE id = " + guestId + ";";

        guestDOB = userFetch.fetchLong(query, "date_of_birth");
        guestAge = (int) ((System.currentTimeMillis() - guestDOB) / 31556952000L);      //convert to age

        query = "SELECT min_age\n" +
                "FROM Event\n" +
                "WHERE event_id = " + eventId + ";";

        minAge = userFetch.fetchInt(query, "min_age");

        if(guestAge < minAge)
            return new MessageResponse("Age requirements not met.", MessageType.ERROR);

        query = "SELECT quota\n" +
                "FROM Event\n" +
                "WHERE event_id = " + eventId + ";";

        quota = userFetch.fetchInt(query, "quota");

        if(quota <= 0)
            return new MessageResponse("Not enough quota for this event.", MessageType.ERROR);

        ticketId = generateTicketId();
        query = "INSERT INTO Ticket VALUES(" + ticketId + ", " + eventId + ", " + guestId + ");";

        if(userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Insertion into Ticket failed",MessageType.ERROR);
        }

        query = "SELECT price\n" +
                "FROM Guest_Activity\n" +
                "WHERE event_id = " + eventId + ";";

        try {
            arr = databaseConnection.execute(query, DatabaseConnection.FETCH);
            rs = (ResultSet) arr[0];
            connection = (Connection) arr[1];

            while (rs.next()) {
                price = rs.getDouble("price");      //gets price
            }

            connection.close();
        }
        catch(Exception e) {
            try {
                connection.close();
            }
            catch (Exception e1) {
                return new MessageResponse("Connection failed", MessageType.ERROR);
            }
            return new MessageResponse("Price fetch failed.", MessageType.ERROR);
        }

        query = "UPDATE Guests\n" +
                "SET money_spent = money_spent + " + price + "\n" +
                "WHERE id = " + guestId + ";";

        if(userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Update of guest's money_spent failed", MessageType.ERROR);
        }

        query = "UPDATE Event\n" +
                "SET quota = quota - 1\n" +
                "WHERE event_id = " + eventId + ";";

        if(userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Update of event's quota failed", MessageType.ERROR);
        }

        return new MessageResponse("Ticket created successfully.", MessageType.SUCCESS);
    }

    /**
     * This method makes an application to a training program as a housekeeper
     * @param hkId housekeeper id
     * @param eventId training program id
     * @return Message Response
     */
    public MessageResponse applyAsHousekeeper(int hkId, int eventId) throws Exception {
        String query;
        Long hkDOB = null;      //housekeeper date of birth
        int hkAge = 0;
        int minAge = 0;
        int quota = 0;

        //preconditions
        if(!createEvent.entryExists("Training_Program", eventId, "event_id", null))
            return new MessageResponse("No such Training Program.", MessageType.ERROR);

        if(!createEvent.entryExists("Housekeeper", hkId, "id", null))
            return new MessageResponse("No such Housekeeper.", MessageType.ERROR);

        if(doublePrimaryEntryExists("HK_Applies_To", hkId, "housekeeper_id", eventId, "training_program_id")) {
            return new MessageResponse("You have already applied to this Training Program.", MessageType.ERROR);
        }

        query = "SELECT date_of_birth\n" +
                "FROM Users\n" +
                "WHERE id = " + hkId + ";";

        hkDOB = userFetch.fetchLong(query, "date_of_birth");
        hkAge = (int) ((System.currentTimeMillis() - hkDOB) / 31556952000L);      //convert to age

        query = "SELECT min_age\n" +
                "FROM Event\n" +
                "WHERE event_id = " + eventId + ";";

        minAge = userFetch.fetchInt(query, "min_age");

        if(hkAge < minAge)
            return new MessageResponse("Age requirements not met.", MessageType.ERROR);

        query = "SELECT quota\n" +
                "FROM Event\n" +
                "WHERE event_id = " + eventId + ";";

        quota = userFetch.fetchInt(query, "quota");

        if(quota <= 0)
            return new MessageResponse("Not enough quota for this event.", MessageType.ERROR);

        //insertion to the relationship table
        query = "INSERT INTO HK_Applies_To VALUES (" + hkId + ", " + eventId + ");";

        if(userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Insertion into HK_Applies_To failed",MessageType.ERROR);
        }

        return new MessageResponse("Application successful.", MessageType.SUCCESS);
    }

    /**
     * This method makes an application to a training program as a security staff
     * @param ssId security staff id
     * @param eventId training program id
     * @return Message Response
     */
    public MessageResponse applyAsSecurityStaff(int ssId, int eventId) throws Exception {
        String query;
        Long ssDOB = null;      //security staff date of birth
        int ssAge = 0;
        int minAge = 0;
        int quota = 0;

        //preconditions
        if(!createEvent.entryExists("Training_Program", eventId, "event_id", null))
            return new MessageResponse("No such Training Program.", MessageType.ERROR);

        if(!createEvent.entryExists("Security_Staff", ssId, "id", null))
            return new MessageResponse("No such Security Staff.", MessageType.ERROR);

        if(doublePrimaryEntryExists("Sec_Staff_Applies_To", ssId, "sec_staff_id", eventId, "training_program_id")) {
            return new MessageResponse("You have already applied to this Training Program.", MessageType.ERROR);
        }

        query = "SELECT date_of_birth\n" +
                "FROM Users\n" +
                "WHERE id = " + ssId + ";";

        ssDOB = userFetch.fetchLong(query, "date_of_birth");
        ssAge = (int) ((System.currentTimeMillis() - ssDOB) / 31556952000L);      //convert to age

        query = "SELECT min_age\n" +
                "FROM Event\n" +
                "WHERE event_id = " + eventId + ";";

        minAge = userFetch.fetchInt(query, "min_age");

        if(ssAge < minAge)
            return new MessageResponse("Age requirements not met.", MessageType.ERROR);

        query = "SELECT quota\n" +
                "FROM Event\n" +
                "WHERE event_id = " + eventId + ";";

        quota = userFetch.fetchInt(query, "quota");

        if(quota <= 0)
            return new MessageResponse("Not enough quota for this event.", MessageType.ERROR);

        //insertion to the relationship table
        query = "INSERT INTO Sec_Staff_Applies_To VALUES (" + ssId + ", " + eventId + ");";

        if(userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Insertion into Sec_Staff_Applies_To failed",MessageType.ERROR);
        }

        return new MessageResponse("Application successful.", MessageType.SUCCESS);
    }

    /**
     * This method evaluates an hk application to a training program
     * @param hkId housekeeper id
     * @param eventId event id
     * @param mgrId manager id, should not be null
     * @param status status,  Either APPROVED or REJECTED
     * @return Message response
     */
    public MessageResponse evaluateHKApplication(int hkId, int eventId, int mgrId, String status) {
        String query;

        //preconditions
        if(!doublePrimaryEntryExists("HK_Applies_To", hkId, "housekeeper_id", eventId, "training_program_id")) {
            return new MessageResponse("Application not found.", MessageType.ERROR);
        }

        status = status.toUpperCase(Locale.ENGLISH);
        if(!status.equals("APPROVED") && !status.equals("REJECTED")) {
            return new MessageResponse("Status must be either \"APPROVED\" or \"REJECTED\".", MessageType.ERROR);
        }

        if(doublePrimaryEntryExists("Evaluates_HK_Application", hkId, "housekeeper_id", eventId, "training_program_id")) {
            return new MessageResponse("Already Evaluated.", MessageType.ERROR);
        }

        //insertion to the relationship table
        query = "INSERT INTO Evaluates_HK_Application VALUES(" + hkId + ", " + eventId + ", " + mgrId + ", '" + status + "');";

        if(userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Insertion into Evaluates_HK_Application failed",MessageType.ERROR);
        }

        if(status.equals("APPROVED")) {
            query = "UPDATE Event\n" +
                    "SET quota = quota - 1\n" +
                    "WHERE event_id = " + eventId + ";";

            if(userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
                return new MessageResponse("Update of event's quota failed", MessageType.ERROR);
            }
        }

        return new MessageResponse("Evaluation successful", MessageType.ERROR);
    }

    /**
     * This method evaluates an ss application to a training program
     * @param ssId security staff id
     * @param eventId event id
     * @param mgrId manager id, should not be null
     * @param status status, Either APPROVED or REJECTED
     * @return Message response
     */
    public MessageResponse evaluateSSApplication(int ssId, int eventId, int mgrId, String status) {
        String query;

        //preconditions
        if(!doublePrimaryEntryExists("Sec_Staff_Applies_To", ssId, "sec_staff_id", eventId, "training_program_id")) {
            return new MessageResponse("Application not found.", MessageType.ERROR);
        }

        status = status.toUpperCase(Locale.ENGLISH);
        if(!status.equals("APPROVED") && !status.equals("REJECTED")) {
            return new MessageResponse("Status must be either \"APPROVED\" or \"REJECTED\".", MessageType.ERROR);
        }

        if(doublePrimaryEntryExists("Evaluates_Sec_Staff_Application", ssId, "sec_staff_id", eventId, "training_program_id")) {
            return new MessageResponse("Already Evaluated.", MessageType.ERROR);
        }

        //insertion to the relationship table
        query = "INSERT INTO Evaluates_Sec_Staff_Application VALUES(" + ssId + ", " + eventId + ", " + mgrId + ", '" + status + "');";

        if(userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Insertion into Evaluates_Sec_Staff_Application failed",MessageType.ERROR);
        }

        if(status.equals("APPROVED")) {
            query = "UPDATE Event\n" +
                    "SET quota = quota - 1\n" +
                    "WHERE event_id = " + eventId + ";";

            if(userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
                return new MessageResponse("Update of event's quota failed", MessageType.ERROR);
            }
        }

        return new MessageResponse("Evaluation successful", MessageType.ERROR);
    }

    /**
     * Used when checking if an entry exists when a primary key consists of two integer attributes
     * @param tableName table name
     * @param id1 first id
     * @param idColumnName1 first id column name
     * @param id2 second id
     * @param idColumnName2 second id column name
     * @return boolean
     */
    public boolean doublePrimaryEntryExists(String tableName, int id1, String idColumnName1, int id2, String idColumnName2){
        String query;
        boolean result;
        query = "SELECT *\n" +
                "FROM " + tableName + "\n" +
                "WHERE " + idColumnName1 + " = " + id1 + " AND " + idColumnName2 + " = " + id2 + ";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            result = resultSet.next();
        }
        catch(Exception e) {
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
        catch (Exception e1) {
            throw new IllegalArgumentException("Error when closing the connection");        //SKETCHY
        }
        return result;
    }

    /**
     * Generates an id that does not exist in the Ticket table.
     * @return The id
     */
    private int generateTicketId() {
        int id;

        //find an unused id
        do {
            id = (int) (Math.random() * 100000);
        } while (createEvent.entryExists("Ticket", id, "ticket_id", null));
        return id;
    }
}
