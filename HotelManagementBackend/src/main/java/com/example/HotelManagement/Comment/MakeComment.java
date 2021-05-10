package com.example.HotelManagement.Comment;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.Food.OrderDetailsDTO;
import com.example.HotelManagement.SignUp.UserInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class MakeComment {

    private final DatabaseConnection databaseConnection;
    private final UserInsertion userInsertion;

    @Autowired
    public MakeComment(DatabaseConnection databaseConnection, UserInsertion userInsertion) {
        this.databaseConnection = databaseConnection;
        this.userInsertion = userInsertion;
    }

    public MessageResponse comment(CommentDTO commentDTO) {
        String query;

        int id = generateId();

        query = "INSERT INTO Comment VALUES (" + id + ", " + commentDTO.getReservationId()
                + ", '" + commentDTO.getText()+ "', " + System.currentTimeMillis() + ", '" + commentDTO.getTopic() +"');";

        if( userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Insertion into Comment failed",MessageType.ERROR);
        }

        return new MessageResponse("Insertion success",MessageType.SUCCESS);
    }

    private int generateId() {
        int id;

        //find an unused id
        do {
            id = (int) (Math.random() * 100000);
        } while (selectCommentById(id));
        return id;
    }

    private boolean selectCommentById(int id) {
        String query = "SELECT *\n" +
                "FROM Comment\n" +
                "WHERE comment_id = " + id + ";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);

        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            resultSet.next();
            resultSet.getInt("comment_id");
            connection.close();
            return true;
        } catch (Exception e) {
            try {
                connection.close();
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
                return false;
            }
            System.out.println(e.getMessage());
            return false;
        }
    }


    public List<ViewCommentDTO> viewComments() throws Exception {
        List<ViewCommentDTO> comments = new ArrayList<>();

        String query = "SELECT * FROM (Comment c NATURAL JOIN Reservation res), Users u WHERE res.guest_id = u.id;";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query,DatabaseConnection.FETCH);

        ResultSet rs = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            while ( rs.next()){
                ViewCommentDTO viewCommentDTO =
                        new ViewCommentDTO(
                                rs.getInt("u.id"),
                                rs.getString("u.firstname") + " " + rs.getString("u.lastname"),
                                rs.getString("c.text"),
                                rs.getLong("c.date"),
                                rs.getString("c.topic")
                        );

                try{
                    comments.add(viewCommentDTO);
                }catch (Exception e){
                    throw new Exception("Result set read problem");
                }
            }
            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                throw new Exception("Cannot close connection");
            }
            throw new Exception("cannot do the whole fetching process");
        }

        return comments;
    }

    public List<ViewCommentDTO> viewCommentsGuest(int guestId) throws Exception {
        List<ViewCommentDTO> comments = new ArrayList<>();

        String query = "SELECT * FROM (Comment c NATURAL JOIN Reservation res), Users u WHERE res.guest_id = u.id AND u.id = "+ guestId +";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query,DatabaseConnection.FETCH);

        ResultSet rs = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            while ( rs.next()){
                ViewCommentDTO viewCommentDTO =
                        new ViewCommentDTO(
                                rs.getInt("u.id"),
                                rs.getString("u.firstname") + " " + rs.getString("u.lastname"),
                                rs.getString("c.text"),
                                rs.getLong("c.date"),
                                rs.getString("c.topic")
                        );

                try{
                    comments.add(viewCommentDTO);
                }catch (Exception e){
                    throw new Exception("Result set read problem");
                }
            }
            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                throw new Exception("Cannot close connection");
            }
            throw new Exception("cannot do the whole fetching process");
        }

        return comments;
    }
}
