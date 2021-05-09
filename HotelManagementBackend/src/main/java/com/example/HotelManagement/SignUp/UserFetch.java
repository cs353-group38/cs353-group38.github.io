package com.example.HotelManagement.SignUp;

import com.example.HotelManagement.Database.DatabaseConnection;

import java.sql.ResultSet;

public class UserFetch {

    private DatabaseConnection databaseConnection;

    public UserFetch(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * This method fetches the user with the given id from the Users table.
     * @param givenId the given id
     * @return The user with the given id, null if the user does not exists.
     */
    public ResultSet selectUserById(int givenId) {
        String query = "SELECT *\n" +
                "FROM Users\n" +
                "WHERE id = " + givenId + ";";

        return databaseConnection.execute(query, DatabaseConnection.FETCH);
    }
}
