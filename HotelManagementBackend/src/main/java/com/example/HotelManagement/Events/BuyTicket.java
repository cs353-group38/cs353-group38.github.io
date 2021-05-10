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
