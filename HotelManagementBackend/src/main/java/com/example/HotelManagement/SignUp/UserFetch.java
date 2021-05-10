package com.example.HotelManagement.SignUp;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    /**
     * This method fetches the candidate with the given id. If there are multiple entries for the same given id, only
     * one of them is returned.
     * @param givenId given id
     * @return Candidate object
     */
    public Candidate selectCandidateById(int givenId) {
        String query = "SELECT *\n" +
                "FROM Candidate\n" +
                "WHERE id = " + givenId + ";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            User user = selectUserById(givenId);
            if (user == null)
                return null;
            resultSet.next();
            Candidate candidate = new Candidate(
                    resultSet.getInt("id"),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getGender(),
                    user.getDate_of_birth(),
                    resultSet.getString("cover_letter")
            );
            connection.close();
            return candidate;
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

    /**
     * Fetches the employee with the given id, if exists
     * @param givenId given id
     * @return the employee
     */
    public Employee selectEmployeeById(int givenId) {
        String query = "SELECT *\n" +
                "FROM Employee\n" +
                "WHERE id = " + givenId + ";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            User user = selectUserById(givenId);
            if (user == null)
                return null;
            resultSet.next();
            Employee employee = new Employee(
                    resultSet.getInt("id"),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getGender(),
                    user.getDate_of_birth(),
                    resultSet.getBigDecimal("salary"),
                    resultSet.getLong("employment_date"),
                    resultSet.getInt("annual_leave")
            );
            connection.close();
            return employee;
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

    /**
     * This method fetches the manager with the given id if it exists
     * @param givenId given id of the manager
     * @return the manager from the database
     */
    public Manager selectManagerById(int givenId) {
        String query = "SELECT *\n" +
                "FROM Manager\n" +
                "WHERE id = " + givenId + ";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            Employee employee = selectEmployeeById(givenId);
            if (employee == null)
                return null;
            resultSet.next();
            Manager manager = new Manager(
                    resultSet.getInt("id"),
                    employee.getFirstname(),
                    employee.getLastname(),
                    employee.getEmail(),
                    employee.getPassword(),
                    employee.getPhone(),
                    employee.getAddress(),
                    employee.getGender(),
                    employee.getDate_of_birth(),
                    employee.getSalary(),
                    employee.getEmployment_date(),
                    employee.getAnnual_leave(),
                    resultSet.getString("office_no")
            );
            connection.close();
            return manager;
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

    /**
     * This method fetches the security staff with the given id if it exists
     * @param givenId given id of the security staff
     * @return the security staff from the database
     */
    public SecurityStaff selectSecurityStaffById(int givenId) {
        String query = "SELECT *\n" +
                "FROM Security_Staff\n" +
                "WHERE id = " + givenId + ";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            Employee employee = selectEmployeeById(givenId);
            if (employee == null)
                return null;
            resultSet.next();
            SecurityStaff securityStaff = new SecurityStaff(
                    resultSet.getInt("id"),
                    employee.getFirstname(),
                    employee.getLastname(),
                    employee.getEmail(),
                    employee.getPassword(),
                    employee.getPhone(),
                    employee.getAddress(),
                    employee.getGender(),
                    employee.getDate_of_birth(),
                    employee.getSalary(),
                    employee.getEmployment_date(),
                    employee.getAnnual_leave(),
                    resultSet.getString("security_rank"),
                    resultSet.getString("weapon")
            );
            connection.close();
            return securityStaff;
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

    /**
     * Used when a single integer is needed from the database.
     * @param query query to pull out a single integer
     * @param columnLabel attribute name of the desired integer
     * @return int
     * @throws Exception exception
     */
    public int fetchInt(String query, String columnLabel) throws Exception {
        Object[] arr;
        ResultSet rs = null;
        Connection connection = null;
        int result = 0;

        try {
            arr = databaseConnection.execute(query, DatabaseConnection.FETCH);
            rs = (ResultSet) arr[0];
            connection = (Connection) arr[1];

            while (rs.next()) {
                result = rs.getInt(columnLabel);      //gets the result
            }

            connection.close();
        }
        catch(Exception e) {
            try {
                connection.close();
            }
            catch (Exception e1) {
                throw new Exception("Connection failed");
            }
            throw new Exception(columnLabel + " fetch failed.");
        }
        return result;
    }

    /**
     * Used when a single integer is needed from the database.
     * @param query query to pull out a single integer
     * @param columnLabel attribute name of the desired integer
     * @return int
     * @throws Exception exception
     */
    public Long fetchLong(String query, String columnLabel) throws Exception {
        Object[] arr;
        ResultSet rs = null;
        Connection connection = null;
        Long result = null;

        try {
            arr = databaseConnection.execute(query, DatabaseConnection.FETCH);
            rs = (ResultSet) arr[0];
            connection = (Connection) arr[1];

            while (rs.next()) {
                result = rs.getLong(columnLabel);      //gets the result
            }

            connection.close();
        }
        catch(Exception e) {
            try {
                connection.close();
            }
            catch (Exception e1) {
                throw new Exception("Connection failed");
            }
            throw new Exception(columnLabel + " fetch failed.");
        }
        return result;
    }
}
