package com.example.HotelManagement.SignUp;

import com.example.HotelManagement.Database.DatabaseConnection;

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
    public int insertUser(String firstName, String lastName, String email, String password, String phone,
                          String address, String gender, Long dateOfBirth) {
        String query;
        ResultSet rs;
        int id;

        //Preconditions
        if(firstName.length() > 50) {
            throw new Exception("First name length cannot be more than 50 characters.");
        }
        if (lastName.length() > 50) {
            throw new Exception("Last name length cannot be more than 50 characters.");
        }
        if (email.length() > 75) {
            throw new Exception("Email length cannot be more than 75 characters.");
        }
        if (password.length() > 20) {
            throw new Exception("Password length cannot be more than 20 characters.");
        }
        if (phone.length() > 20) {
            throw new Exception("Phone length cannot be more than 20 characters.");
        }
        if (address.length() > 100) {
            throw new Exception("Address length cannot be more than 100 characters.");
        }
        if (gender.length() > 20) {
            throw new Exception("Gender length cannot be more than 20 characters.");
        }

        //find an unused id
        do {
            id = (int) (Math.random() * 100000);
            rs = userFetch.selectUserById(id);
        } while (rs != null);

        //method
        query = "INSERT INTO Users VALUES (" + id + ", " + firstName + ", " + lastName + ", " + email + ", " +
                password + ", " + phone + ", " + address + ", " + gender + ", " + dateOfBirth + ");";
        return 1;
    }
}
