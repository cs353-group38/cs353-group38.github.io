package com.example.HotelManagement.SignUp;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.Entity.User;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;

@Service
public class UserInsertion {

    private DatabaseConnection databaseConnection;
    private UserFetch userFetch;

    public UserInsertion(DatabaseConnection databaseConnection, UserFetch userFetch) {
        this.databaseConnection = databaseConnection;
        this.userFetch = userFetch;
    }

    /**
     * This method inserts a row with the given information to the Users table.
     * @param id id
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     * @param phone phone
     * @param address address
     * @param gender gender
     * @param dateOfBirth date of birth as Long value
     * @return Success or fail message
     */
    public MessageResponse insertUser(int id, String firstName, String lastName, String email, String password, String phone,
                                      String address, String gender, Long dateOfBirth) {
        String query;

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

        //insertion
        query = "INSERT INTO Users VALUES (" + id + ", '" + firstName + "', '" + lastName + "', '" + email + "', '" +
                password + "', '" + phone + "', '" + address + "', '" + gender + "', " + dateOfBirth + ");";
        return executeUpdate(query);
    }

    /**
     * This method inserts a row with the given information to the Candidate and Users table.
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     * @param phone phone
     * @param address address
     * @param gender gender
     * @param dateOfBirth date of birth as Long value
     * @param coverLetter cover letter
     * @return Success or fail message
     */
    public MessageResponse insertCandidate(String firstName, String lastName, String email, String password, String phone,
                                           String address, String gender, Long dateOfBirth, String coverLetter) {
        String query;
        int id;
        MessageResponse response;

        //Preconditions
        if(coverLetter.length() > 5000) {
            return new MessageResponse("Cover letter length cannot be more than 5000 characters.", MessageType.ERROR);
        }

        id = generateId();

        response = insertUser(id, firstName, lastName, email, password, phone, address, gender, dateOfBirth);
        query = "INSERT INTO Candidate VALUES (" + id + ", '" + coverLetter + "');";

        if (response.getMessageType() == MessageType.ERROR)
            return response;
        return executeUpdate(query);
    }

    /**
     * This method inserts a row with the given information to the Employee and Users table.
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     * @param phone phone
     * @param address address
     * @param gender gender
     * @param dateOfBirth date of birth as Long value
     * @param salary salary
     * @param employmentDate employment date
     * @param annualLeave annual leave
     * @return Success or fail message
     */
    public MessageResponse insertEmployee(int id, String firstName, String lastName, String email, String password, String phone,
                                           String address, String gender, Long dateOfBirth, double salary,
                                          Long employmentDate, int annualLeave) {
        String query;
        MessageResponse response = insertUser(id, firstName, lastName, email, password, phone, address, gender, dateOfBirth);
        query = "INSERT INTO Employee VALUES (" + id + ", " + salary + ", " + employmentDate + ", " + annualLeave + ");";
        if (response.getMessageType() == MessageType.ERROR)
            return response;
        return executeUpdate(query);
    }

    /**
     * This method inserts a row with the given information to the Guests and Users table.
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     * @param phone phone
     * @param address address
     * @param gender gender
     * @param dateOfBirth date of birth as Long value
     * @param moneySpent Money spent
     * @return Success or fail message
     */
    public MessageResponse insertGuest(String firstName, String lastName, String email, String password, String phone,
                                          String address, String gender, Long dateOfBirth, double moneySpent) {
        String query;
        int id = generateId();
        MessageResponse response = insertUser(id, firstName, lastName, email, password, phone, address, gender, dateOfBirth);
        query = "INSERT INTO Guests VALUES (" + id + ", " + moneySpent + ");";
        if (response.getMessageType() == MessageType.ERROR)
            return response;
        return executeUpdate(query);
    }

    /**
     * This method inserts a row with the given information to the Housekeeper and Users table.
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     * @param phone phone
     * @param address address
     * @param gender gender
     * @param dateOfBirth date of birth as Long value
     * @param salary salary
     * @param employmentDate employment date
     * @param annualLeave annual leave
     * @return Success or fail message
     */
    public MessageResponse insertHousekeeper(String firstName, String lastName, String email, String password, String phone,
                                       String address, String gender, Long dateOfBirth, double salary,
                                             Long employmentDate, int annualLeave) {
        String query;
        int id = generateId();
        MessageResponse response = insertEmployee(id, firstName, lastName, email, password, phone, address, gender, dateOfBirth, salary, employmentDate, annualLeave);
        query = "INSERT INTO Housekeeper VALUES (" + id + ");";
        if (response.getMessageType() == MessageType.ERROR)
            return response;
        return executeUpdate(query);
    }

    /**
     * This method inserts a row with the given information to the Manager and Users table.
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     * @param phone phone
     * @param address address
     * @param gender gender
     * @param dateOfBirth date of birth as Long value
     * @param salary salary
     * @param employmentDate employment date
     * @param annualLeave annual leave
     * @param officeNo office no
     * @return Success or fail message
     */
    public MessageResponse insertManager(String firstName, String lastName, String email, String password, String phone,
                                             String address, String gender, Long dateOfBirth, double salary,
                                         Long employmentDate, int annualLeave, String officeNo) {
        String query;
        int id = generateId();
        MessageResponse response = insertEmployee(id, firstName, lastName, email, password, phone, address, gender, dateOfBirth, salary, employmentDate, annualLeave);
        query = "INSERT INTO Manager VALUES (" + id + ", '" + officeNo + "');";
        if (response.getMessageType() == MessageType.ERROR)
            return response;
        return executeUpdate(query);
    }

    /**
     * This method inserts a row with the given information to the Receptionist and Users table.
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     * @param phone phone
     * @param address address
     * @param gender gender
     * @param dateOfBirth date of birth as Long value
     * @param salary salary
     * @param employmentDate employment date
     * @param annualLeave annual leave
     * @return Success or fail message
     */
    public MessageResponse insertReceptionist(String firstName, String lastName, String email, String password, String phone,
                                             String address, String gender, Long dateOfBirth, double salary,
                                             Long employmentDate, int annualLeave) {
        String query;
        int id = generateId();
        MessageResponse response = insertEmployee(id, firstName, lastName, email, password, phone, address, gender, dateOfBirth, salary, employmentDate, annualLeave);
        query = "INSERT INTO Receptionist VALUES (" + id + ");";
        if (response.getMessageType() == MessageType.ERROR)
            return response;
        return executeUpdate(query);
    }

    /**
     * This method inserts a row with the given information to the Recruiter and Users table.
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     * @param phone phone
     * @param address address
     * @param gender gender
     * @param dateOfBirth date of birth as Long value
     * @param salary salary
     * @param employmentDate employment date
     * @param annualLeave annual leave
     * @return Success or fail message
     */
    public MessageResponse insertRecruiter(String firstName, String lastName, String email, String password, String phone,
                                              String address, String gender, Long dateOfBirth, double salary,
                                              Long employmentDate, int annualLeave) {
        String query;
        int id = generateId();
        MessageResponse response = insertEmployee(id, firstName, lastName, email, password, phone, address, gender, dateOfBirth, salary, employmentDate, annualLeave);
        query = "INSERT INTO Recruiter VALUES (" + id + ");";
        if (response.getMessageType() == MessageType.ERROR)
            return response;
        return executeUpdate(query);
    }

    /**
     * This method inserts a row with the given information to the Recruiter and Users table.
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     * @param phone phone
     * @param address address
     * @param gender gender
     * @param dateOfBirth date of birth as Long value
     * @param salary salary
     * @param employmentDate employment date
     * @param annualLeave annual leave
     * @param securityRank security rank
     * @param weapon weapon
     * @return Success or fail message
     */
    public MessageResponse insertSecurityStaff(String firstName, String lastName, String email, String password, String phone,
                                           String address, String gender, Long dateOfBirth, double salary,
                                           Long employmentDate, int annualLeave, String securityRank, String weapon) {
        String query;
        int id = generateId();
        MessageResponse response = insertEmployee(id, firstName, lastName, email, password, phone, address, gender, dateOfBirth, salary, employmentDate, annualLeave);
        query = "INSERT INTO Security_Staff VALUES (" + id + ", '" + securityRank + "', '" + weapon + "');";
        if (response.getMessageType() == MessageType.ERROR)
            return response;
        return executeUpdate(query);
    }

    /**
     * Generates an id that does not exist in the Users table.
     * @return The id
     */
    private int generateId() {
        int id;
        User user;

        //find an unused id
        do {
            id = (int) (Math.random() * 100000);
            user = userFetch.selectUserById(1);       //FIX THIS
        } while (user == null);
        return id;
    }

    /**
     * Executes a given query to insert a user. Query could be any other update sql, however, the error messages are
     * designed for user insertion.
     * @param query The query to be executed.
     * @return
     */
    private MessageResponse executeUpdate(String query) {
        try {
            databaseConnection.execute(query, DatabaseConnection.UPDATE);
        }
        catch (Exception e) {
            return new MessageResponse("User insertion failed.", MessageType.ERROR);
        }
        return new MessageResponse("User insertion successful.", MessageType.SUCCESS);
    }
}
