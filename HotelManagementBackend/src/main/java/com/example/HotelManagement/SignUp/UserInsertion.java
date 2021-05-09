package com.example.HotelManagement.SignUp;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import org.apache.logging.log4j.message.Message;

import java.sql.ResultSet;

public class UserInsertion {

    private DatabaseConnection databaseConnection;
    private UserFetch userFetch;

    public UserInsertion(DatabaseConnection databaseConnection, UserFetch userFetch) {
        this.databaseConnection = databaseConnection;
        this.userFetch = userFetch;
    }

    /**
     * This method inserts a row with the given information to the Users table.
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     * @param phone phone
     * @param address address
     * @param gender gender
     * @param dateOfBirth date of birth as Long value
     * @return 1 if success, 0 if failure
     */
    public MessageResponse insertUser(String firstName, String lastName, String email, String password, String phone,
                                      String address, String gender, Long dateOfBirth) {
        String query;
        ResultSet rs;
        int id;

        //Preconditions
        if(firstName.length() > 50) {
            return new MessageResponse("First name length cannot be more than 50 characters.", MessageType.ERROR);
        }
        if (lastName.length() > 50) {
            return new MessageResponse("Last name length cannot be more than 50 characters.", MessageType.ERROR);
        }
        if (email.length() > 75) {
            return new MessageResponse("Email length cannot be more than 75 characters.", MessageType.ERROR);
        }
        if (password.length() > 20) {
            return new MessageResponse("Password length cannot be more than 20 characters.", MessageType.ERROR);
        }
        if (phone.length() > 20) {
            return new MessageResponse("Phone length cannot be more than 20 characters.", MessageType.ERROR);
        }
        if (address.length() > 100) {
            return new MessageResponse("Address length cannot be more than 100 characters.", MessageType.ERROR);
        }
        if (gender.length() > 20) {
            return new MessageResponse("Gender length cannot be more than 20 characters.", MessageType.ERROR);
        }

        //find an unused id
        do {
            id = (int) (Math.random() * 100000);
            rs = userFetch.selectUserById(1);
        } while (rs == null);

        //insertion
        query = "INSERT INTO Users VALUES (" + id + ", '" + firstName + "', '" + lastName + "', '" + email + "', '" +
                password + "', '" + phone + "', '" + address + "', '" + gender + "', " + dateOfBirth + ");";
        try {
            databaseConnection.execute(query, DatabaseConnection.UPDATE);
        }
        catch (Exception e) {
            return new MessageResponse("User insertion failed.", MessageType.ERROR);
        }
        return new MessageResponse("User insertion successful.", MessageType.SUCCESS);
    }
}
