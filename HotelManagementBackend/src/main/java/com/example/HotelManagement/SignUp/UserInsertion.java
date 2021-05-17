package com.example.HotelManagement.SignUp;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.Entity.Candidate;
import com.example.HotelManagement.Entity.User;
import com.example.HotelManagement.Events.ViewGroupTourDTO;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInsertion {

    private final int HK_SALARY = 5000;
    private final int ANNUAL_LEAVE = 20;
    private final String OFFICE_NO = "A-105";

    private DatabaseConnection databaseConnection;
    private UserFetch userFetch;

    @Autowired
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

        if (response.getMessageType().equals(MessageType.ERROR))
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
        if (response.getMessageType().equals(MessageType.ERROR))
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
        if (response.getMessageType().equals(MessageType.ERROR))
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
        if (response.getMessageType().equals(MessageType.ERROR))
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
        if (response.getMessageType().equals(MessageType.ERROR))
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
        if (response.getMessageType().equals(MessageType.ERROR))
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
        if (response.getMessageType().equals(MessageType.ERROR))
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
        if (response.getMessageType().equals(MessageType.ERROR))
            return response;
        return executeUpdate(query);
    }

    //==============================Promote Methods==============================
    /**
     * Promotes a candidate to a housekeeper in the database.
     * @param id id of the user to be promoted
     * @return Message response
     */
    public MessageResponse promoteToHousekeeper(int id) {
        Candidate candidate = userFetch.selectCandidateById(id);
        String query;
        MessageResponse response;
        if(candidate == null) {
            return new MessageResponse("No such candidate.", MessageType.ERROR);
        }

        query = "DELETE FROM Candidate\n" +
                "WHERE id=" + id + ";";
        response = executeUpdate(query);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        query = "INSERT INTO Employee VALUES (" + id + ", " + HK_SALARY + ", UNIX_TIMESTAMP() * 1000, " + ANNUAL_LEAVE + ");";
        response = executeUpdate(query);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        query = "INSERT INTO Housekeeper VALUES (" + id + ");";
        return executeUpdate(query);
    }

    /**
     * Promotes a candidate to a manager in the database.
     * @param id id of the user to be promoted
     * @return Message response
     */
    public MessageResponse promoteToManager(int id) {
        Candidate candidate = userFetch.selectCandidateById(id);
        String query;
        MessageResponse response;
        if(candidate == null) {
            return new MessageResponse("No such candidate.", MessageType.ERROR);
        }

        query = "DELETE FROM Candidate\n" +
                "WHERE id=" + id + ";";
        response = executeUpdate(query);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        query = "INSERT INTO Employee VALUES (" + id + ", " + HK_SALARY + ", UNIX_TIMESTAMP() * 1000, " + ANNUAL_LEAVE + ");";
        response = executeUpdate(query);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        query = "INSERT INTO Manager VALUES (" + id + ", '" + OFFICE_NO + "');";
        return executeUpdate(query);
    }

    /**
     * Promotes a candidate to a receptionist in the database.
     * @param id id of the user to be promoted
     * @return Message response
     */
    public MessageResponse promoteToReceptionist(int id) {
        Candidate candidate = userFetch.selectCandidateById(id);
        String query;
        MessageResponse response;
        if(candidate == null) {
            return new MessageResponse("No such candidate.", MessageType.ERROR);
        }

        query = "DELETE FROM Candidate\n" +
                "WHERE id=" + id + ";";
        response = executeUpdate(query);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        query = "INSERT INTO Employee VALUES (" + id + ", " + HK_SALARY + ", UNIX_TIMESTAMP() * 1000, " + ANNUAL_LEAVE + ");";
        response = executeUpdate(query);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        query = "INSERT INTO Receptionist VALUES (" + id + ");";
        return executeUpdate(query);
    }

    /**
     * Promotes a candidate to a recruiter in the database.
     * @param id id of the user to be promoted
     * @return Message response
     */
    public MessageResponse promoteToRecruiter(int id) {
        Candidate candidate = userFetch.selectCandidateById(id);
        String query;
        MessageResponse response;
        if(candidate == null) {
            return new MessageResponse("No such candidate.", MessageType.ERROR);
        }

        query = "DELETE FROM Candidate\n" +
                "WHERE id=" + id + ";";
        response = executeUpdate(query);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        query = "INSERT INTO Employee VALUES (" + id + ", " + HK_SALARY + ", UNIX_TIMESTAMP() * 1000, " + ANNUAL_LEAVE + ");";
        response = executeUpdate(query);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        query = "INSERT INTO Recruiter VALUES (" + id + ");";
        return executeUpdate(query);
    }

    /**
     * Promotes a candidate to a security staff in the database.
     * @param id id of the user to be promoted
     * @return Message response
     */
    public MessageResponse promoteToSecurityStaff(int id) {
        Candidate candidate = userFetch.selectCandidateById(id);
        String query;
        MessageResponse response;
        if(candidate == null) {
            return new MessageResponse("No such candidate.", MessageType.ERROR);
        }

        query = "DELETE FROM Candidate\n" +
                "WHERE id=" + id + ";";
        response = executeUpdate(query);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        query = "INSERT INTO Employee VALUES (" + id + ", " + HK_SALARY + ", UNIX_TIMESTAMP() * 1000, " + ANNUAL_LEAVE + ");";
        response = executeUpdate(query);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        query = "INSERT INTO Security_Staff VALUES (" + id + ", '" + "Beginner" + "', '" + "Pistol" + "');";
        return executeUpdate(query);
    }

    public UserAgeReportDTO viewUserAges() {
        String query;
        List<UserAgeDTO> userAgeDTOList = new ArrayList<>();
        Object[] resultArr = null;
        ResultSet resultSet;
        Connection connection;

        query = "SELECT CASE\n" +
                "           WHEN (Users.id IN (SELECT id FROM Guests)) THEN 'Guest'\n" +
                "           WHEN (Users.id IN (SELECT id FROM Manager)) THEN 'Manager'\n" +
                "           WHEN (Users.id IN (SELECT id FROM Housekeeper)) THEN 'Housekeeper'\n" +
                "           WHEN (Users.id IN (SELECT id FROM Security_Staff)) THEN 'Security Staff'\n" +
                "           WHEN (Users.id IN (SELECT id FROM Recruiter)) THEN 'Recruiter'\n" +
                "           WHEN (Users.id IN (SELECT id FROM Receptionist)) THEN 'Receptionist'\n" +
                "           WHEN (Users.id IN (SELECT id FROM Candidate)) THEN 'Candidate'\n" +
                "        END AS user_type, AVG((UNIX_TIMESTAMP() * 1000 - date_of_birth) / 31556952000) AS avg_age, MIN((UNIX_TIMESTAMP() * 1000 - date_of_birth) / 31556952000) AS min_age, MAX((UNIX_TIMESTAMP() * 1000 - date_of_birth) / 31556952000) max_age\n" +
                "FROM Users\n" +
                "GROUP BY user_type;";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                if(resultSet.getString("user_type") != null) {
                    UserAgeDTO userAgeDTO = new UserAgeDTO(
                            resultSet.getString("user_type"),
                            (int) Math.floor(resultSet.getDouble("avg_age") + 0.5),
                            (int) Math.floor(resultSet.getDouble("min_age") + 0.5),
                            (int) Math.floor(resultSet.getDouble("max_age") + 0.5)
                    );
                    userAgeDTOList.add(userAgeDTO);
                }
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

        return new UserAgeReportDTO(userAgeDTOList);
    }

    public RoomTypeReportDTO viewRoomTypes() {
        String query;
        List<RoomTypeDTO> roomTypeDTOList = new ArrayList<>();
        Object[] resultArr = null;
        ResultSet resultSet;
        Connection connection;

        query = "SELECT type, reservation_count, avg_money_spent\n" +
                "FROM (SELECT type, COUNT(DISTINCT reservation_id) AS reservation_count, AVG(money_spent) AS avg_money_spent\n" +
                "      FROM Room_Type NATURAL JOIN Room NATURAL JOIN Reservation, Guests\n" +
                "      WHERE guest_id = Guests.id\n" +
                "      GROUP BY type) AS Room_Type_Stats\n" +
                "WHERE reservation_count > 0;";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                RoomTypeDTO roomTypeDTO = new RoomTypeDTO(
                        resultSet.getString("type"),
                        resultSet.getInt("reservation_count"),
                        resultSet.getDouble("avg_money_spent")
                );
                roomTypeDTOList.add(roomTypeDTO);
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

        return new RoomTypeReportDTO(roomTypeDTOList);
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
            user = userFetch.selectUserById(id);
        } while (user != null);
        return id;
    }

    /**
     * Executes a given query to insert a user. Query could be any other update sql, however, the error messages are
     * designed for user insertion.
     * @param query The query to be executed.
     * @return
     */
    public MessageResponse executeUpdate(String query) {
        Connection connection = null;
        try {
            Object[] arr = databaseConnection.execute(query, DatabaseConnection.UPDATE);
            connection = (Connection) arr[1];
            connection.close();
        }
        catch (Exception e) {
            try{
                connection.close();
            }catch (Exception e1){

                return new MessageResponse("Connection failed",MessageType.ERROR);
            }
            return new MessageResponse("User insertion failed.", MessageType.ERROR);
        }
        return new MessageResponse("User insertion successful.", MessageType.SUCCESS);
    }
}
