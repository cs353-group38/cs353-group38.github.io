package com.example.HotelManagement.SignUp;

import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;

@Service
public class UserFetch {

    private DatabaseConnection databaseConnection;

    @Autowired
    public UserFetch(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * This method fetches the user with the given id from the Users table.
     * @param givenId the given id
     * @return The user with the given id, null if the user does not exists.
     */
    public User selectUserById(int givenId) {
        String query = "SELECT *\n" +
                "FROM Users\n" +
                "WHERE id = " + givenId + ";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);

        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            resultSet.next();
            User user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("phone"),
                    resultSet.getString("address"),
                    resultSet.getString("gender"),
                    resultSet.getLong("date_of_birth")
            );
            connection.close();
            return user;
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                System.out.println(e1.getMessage());
                return null;
            }
            System.out.println(e.getMessage());
            return null;
        }
    }
}
