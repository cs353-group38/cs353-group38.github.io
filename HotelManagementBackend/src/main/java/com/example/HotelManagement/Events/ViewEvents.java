package com.example.HotelManagement.Events;

import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.SecurityStaffOperations.ViewAllSecurityStaffDTO;
import com.example.HotelManagement.SignUp.UserInsertion;
import com.example.HotelManagement.SignUp.ViewCandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class ViewEvents {

    private DatabaseConnection databaseConnection;
    private UserInsertion userInsertion;
    private CreateEvent createEvent;

    @Autowired
    public ViewEvents(DatabaseConnection databaseConnection, UserInsertion userInsertion, CreateEvent createEvent) {
        this.databaseConnection = databaseConnection;
        this.userInsertion = userInsertion;
        this.createEvent = createEvent;
    }

    /**
     * Used when viewing a particular guest activity
     * @param eventId event id
     * @return dto object
     */
    public ViewGroupTourDTO viewGuestActivity(int eventId) {
        String query;
        List<ParticipantDTO> participantDTOList = new ArrayList<>();
        Object[] resultArr = null;
        ResultSet resultSet;
        Connection connection;

        if(!createEvent.entryExists("Guest_Activity", eventId, "event_id", null))
            throw new IllegalArgumentException("No such event.");

        //========================FILL THE PARTICIPANT LIST========================
        query = "SELECT *\n" +
                "FROM Ticket t, Users u\n" +
                "WHERE t.guest_id = u.id AND t.event_id = " + eventId + ";";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                ParticipantDTO participantDTO = new ParticipantDTO(
                        resultSet.getInt("guest_id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
                participantDTOList.add(participantDTO);
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
        //========================FILL THE PARTICIPANT LIST========================

        if(createEvent.entryExists("Group_Tours", eventId, "event_id", null)) {
            query = "SELECT *\n" +
                    "FROM Event NATURAL JOIN Guest_Activity NATURAL JOIN Group_Tours\n" +
                    "WHERE event_id = " + eventId + ";";

            resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
            resultSet = (ResultSet) resultArr[0];
            connection = (Connection) resultArr[1];

            try {
                if(resultSet.next()) {
                    return new ViewGroupTourDTO(
                            resultSet.getString("event_name"),
                            resultSet.getString("location_name"),
                            resultSet.getLong("start_date"),
                            resultSet.getLong("end_date"),
                            resultSet.getInt("min_age"),
                            resultSet.getInt("quota"),
                            resultSet.getString("description"),
                            resultSet.getInt("manager_id"),
                            resultSet.getDouble("price"),
                            resultSet.getString("organizer_name"),
                            resultSet.getString("tour_vehicle"),
                            resultSet.getInt("distance_to_cover"),
                            participantDTOList
                    );
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
        }
        else {
            query = "SELECT *\n" +
                    "FROM Event NATURAL JOIN Guest_Activity\n" +
                    "WHERE event_id = " + eventId + ";";

            resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
            resultSet = (ResultSet) resultArr[0];
            connection = (Connection) resultArr[1];

            try {
                if(resultSet.next()) {
                    return new ViewGroupTourDTO(
                            resultSet.getString("event_name"),
                            resultSet.getString("location_name"),
                            resultSet.getLong("start_date"),
                            resultSet.getLong("end_date"),
                            resultSet.getInt("min_age"),
                            resultSet.getInt("quota"),
                            resultSet.getString("description"),
                            resultSet.getInt("manager_id"),
                            resultSet.getDouble("price"),
                            "",
                            "",
                            0,
                            participantDTOList
                    );
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
        }
        return null;
    }

    /**
     * View all guest activities
     * @return dto object
     */
    public ViewAllGroupToursDTO viewAllGuestActivities() {
        String query;
        List<ViewGroupTourDTO> viewGroupTourDTOList = new ArrayList<>();
        Object[] resultArr = null;
        ResultSet resultSet;
        Connection connection;

        query = "SELECT event_id, event_name, location_name, start_date, end_date, quota, price\n" +
                "FROM Event NATURAL JOIN Guest_Activity;";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while (resultSet.next()) {
                ViewGroupTourDTO viewGroupTourDTO = new ViewGroupTourDTO(
                        resultSet.getString("event_name"),
                        resultSet.getString("location_name"),
                        resultSet.getLong("start_date"),
                        resultSet.getLong("end_date"),
                        0,
                        resultSet.getInt("quota"),
                        "",
                        resultSet.getInt("event_id"),
                        resultSet.getDouble("price"),
                        "",
                        "",
                        0,
                        new ArrayList<>()
                );
                viewGroupTourDTOList.add(viewGroupTourDTO);
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
        return new ViewAllGroupToursDTO(viewGroupTourDTOList);
    }

    public ViewAllGroupToursDTO viewGuestActivitiesByName(String name) {
        String query;
        List<ViewGroupTourDTO> viewGroupTourDTOList = new ArrayList<>();
        Object[] resultArr = null;
        ResultSet resultSet;
        Connection connection;

        query = "SELECT event_id, event_name, location_name, start_date, end_date, quota, price\n" +
                "FROM Event NATURAL JOIN Guest_Activity\n" +
                "WHERE event_name LIKE '%" + name + "%'\n" +
                "ORDER BY CASE WHEN event_name LIKE '" + name + "%' THEN 1\n" +
                "              WHEN event_name LIKE '%" + name + "%' THEN 2\n" +
                "END;";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while (resultSet.next()) {
                ViewGroupTourDTO viewGroupTourDTO = new ViewGroupTourDTO(
                        resultSet.getString("event_name"),
                        resultSet.getString("location_name"),
                        resultSet.getLong("start_date"),
                        resultSet.getLong("end_date"),
                        0,
                        resultSet.getInt("quota"),
                        "",
                        resultSet.getInt("event_id"),
                        resultSet.getDouble("price"),
                        "",
                        "",
                        0,
                        new ArrayList<>()
                );
                viewGroupTourDTOList.add(viewGroupTourDTO);
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
        return new ViewAllGroupToursDTO(viewGroupTourDTOList);
    }

    /**
     * View a particular training program
     * @param eventId event id
     * @return dto object
     */
    public ViewTrainingProgramDTO viewTrainingProgram(int eventId) {
        String query;
        List<ParticipantDTO> applicantList = new ArrayList<>();
        List<ParticipantDTO> participantList = new ArrayList<>();
        ViewTrainingProgramDTO viewTrainingProgramDTO = null;
        Object[] resultArr = null;
        ResultSet resultSet;
        Connection connection;

        //Preconditions
        if(!createEvent.entryExists("Training_Program", eventId, "event_id", null))
            throw new IllegalArgumentException("No such event.");

        //========================FILL THE PARTICIPANT LIST========================
        query = "SELECT *\n" +
                "FROM Evaluates_HK_Application, Users\n" +
                "WHERE housekeeper_id = id AND training_program_id = " + eventId + " AND application_status = 'APPROVED';";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                ParticipantDTO participantDTO = new ParticipantDTO(
                        resultSet.getInt("housekeeper_id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
                participantList.add(participantDTO);
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

        query = "SELECT *\n" +
                "FROM Evaluates_Sec_Staff_Application, Users\n" +
                "WHERE sec_staff_id = id AND training_program_id = " + eventId + " AND application_status = 'APPROVED';";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                ParticipantDTO participantDTO = new ParticipantDTO(
                        resultSet.getInt("sec_staff_id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
                participantList.add(participantDTO);
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
        //========================FILL THE PARTICIPANT LIST========================

        //========================FILL THE APPLICANT LIST========================
        query = "(SELECT *\n" +
                "FROM HK_Applies_To, Users\n" +
                "WHERE housekeeper_id = id AND training_program_id = " + eventId + " AND housekeeper_id NOT IN (SELECT Evaluates_HK_Application.housekeeper_id\n" +
                "                                                                                     FROM Evaluates_HK_Application, Users\n" +
                "                                                                                     WHERE housekeeper_id = id AND training_program_id = " + eventId + " AND application_status = 'APPROVED'));";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                ParticipantDTO participantDTO = new ParticipantDTO(
                        resultSet.getInt("housekeeper_id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
                applicantList.add(participantDTO);
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

        query = "(SELECT *\n" +
                "FROM Sec_Staff_Applies_To, Users\n" +
                "WHERE sec_staff_id = id AND training_program_id = " + eventId + " AND sec_staff_id NOT IN (SELECT Evaluates_Sec_Staff_Application.sec_staff_id\n" +
                "                                                                                     FROM Evaluates_Sec_Staff_Application, Users\n" +
                "                                                                                     WHERE sec_staff_id = id AND training_program_id = " + eventId + " AND application_status = 'APPROVED'));";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                ParticipantDTO participantDTO = new ParticipantDTO(
                        resultSet.getInt("sec_staff_id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
                applicantList.add(participantDTO);
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
        //========================FILL THE APPLICANT LIST========================

        query = "SELECT *\n" +
                "FROM Event\n" +
                "WHERE event_id = " + eventId + ";";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            if(!resultSet.next())
                throw new IllegalArgumentException("No such event.");

            viewTrainingProgramDTO = new ViewTrainingProgramDTO(
                    resultSet.getString("event_name"),
                    resultSet.getString("location_name"),
                    resultSet.getLong("start_date"),
                    resultSet.getLong("end_date"),
                    resultSet.getInt("min_age"),
                    resultSet.getInt("quota"),
                    resultSet.getString("description"),
                    resultSet.getInt("manager_id"),
                    applicantList,
                    participantList
            );

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

        return viewTrainingProgramDTO;
    }

    /**
     * Lists all the training programs in the system
     * @return dto object
     */
    public ViewAllGroupToursDTO viewAllTrainingPrograms() {
        String query;
        List<ViewGroupTourDTO> viewGroupTourDTOList = new ArrayList<>();
        Object[] resultArr = null;
        ResultSet resultSet;
        Connection connection;

        query = "SELECT *\n" +
                "FROM Event NATURAL JOIN Training_Program;";

        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);
        resultSet = (ResultSet) resultArr[0];
        connection = (Connection) resultArr[1];

        try {
            while(resultSet.next()) {
                ViewGroupTourDTO viewGroupTourDTO = new ViewGroupTourDTO(
                        resultSet.getString("event_name"),
                        resultSet.getString("location_name"),
                        resultSet.getLong("start_date"),
                        resultSet.getLong("end_date"),
                        0,
                        resultSet.getInt("quota"),
                        "",
                        resultSet.getInt("event_id"),
                        0,
                        "",
                        "",
                        0,
                        new ArrayList<>()
                );
                viewGroupTourDTOList.add(viewGroupTourDTO);
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

        return new ViewAllGroupToursDTO(viewGroupTourDTOList);
    }
}
