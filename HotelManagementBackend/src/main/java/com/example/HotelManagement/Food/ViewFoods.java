package com.example.HotelManagement.Food;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.Reserve.ReservationDTO;
import com.example.HotelManagement.Reserve.RoomDTO;
import com.example.HotelManagement.SignUp.UserInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class ViewFoods {

    private final DatabaseConnection databaseConnection;
    private final UserInsertion userInsertion;

    @Autowired
    public ViewFoods(DatabaseConnection databaseConnection, UserInsertion userInsertion) {
        this.databaseConnection = databaseConnection;
        this.userInsertion = userInsertion;
    }

    public List<FoodDTO> getAllFoods() throws Exception {
        List<FoodDTO> foods = new ArrayList<>();

        String query = "SELECT * FROM (Food_Drink f, Restaurant r) WHERE r.restaurant_id = f.restaurant_id";
        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query,DatabaseConnection.FETCH);

        ResultSet rs = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            while ( rs.next()){
                FoodDTO foodDTO =
                        new FoodDTO(
                                rs.getInt("f.food_id"),
                                rs.getString("f.name"),
                                rs.getString("f.type"),
                                rs.getDouble("f.price"),
                                rs.getInt("f.calorie"),
                                rs.getString("r.location_name"),
                                rs.getString("r.name"),
                                rs.getString("r.type")
                        );

                try{
                    foods.add(foodDTO);
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

        return foods;
    }


    public MessageResponse order(OrderDTO orderDTO) {
        String query;

        int id = generateId();


        query = "INSERT INTO Food_Order VALUES (" + id + ", " + "null"
                + ", " + "null" + ", " + orderDTO.getGuestId() + ", " +
                "null" + ", " + "null" + ", "+ "-1" + ", " + "-1" + ", " + "'ORDERED'" +");";

        if( userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Insertion into Order failed",MessageType.ERROR);
        }

        for( int i = 0; i < orderDTO.getFoodIdList().size();i++){
            query = "INSERT INTO Order_Contains VALUES (" + id + "," + orderDTO.getFoodIdList().get(i) + ");";

            if( userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
                return new MessageResponse("Insertion into Order contains failed",MessageType.ERROR);
            }
        }
        return new MessageResponse("Insertion success",MessageType.SUCCESS);
    }
    private int generateId() {
        int id;

        //find an unused id
        do {
            id = (int) (Math.random() * 100000);
        } while (selectOrderById(id));
        return id;
    }

    private boolean selectOrderById(int id) {
        String query = "SELECT *\n" +
                "FROM Food_Order\n" +
                "WHERE order_id = " + id + ";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query, DatabaseConnection.FETCH);

        ResultSet resultSet = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            resultSet.next();
            resultSet.getString("order_id");
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


    public List<ManageOrderDTO> viewOrders() throws Exception {
        List<ManageOrderDTO> orders = new ArrayList<>();

        String query = "SELECT * FROM (Users u, Room r, Building b2), (Restaurant res), (Reservation res2), " +
                "(Food_Order fo NATURAL JOIN Order_Contains oc NATURAL JOIN Food_Drink fd ) WHERE  u.id = res2.guest_id " +
                "AND res2.building_no = r.building_no AND b2.building_no = r.building_no AND fo.guest_id = u.id " +
                "AND res2.room_no = r.room_no AND res.restaurant_id = fd.restaurant_id";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query,DatabaseConnection.FETCH);

        ResultSet rs = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        int curResId = 0;
        int preResId = -1;

        try {
            while ( rs.next()){
                curResId = rs.getInt("fo.order_id");

                if( curResId != preResId ) {
                    ManageOrderDTO manageOrderDTO =
                            new ManageOrderDTO(
                                    rs.getInt("fo.order_id"),
                                    rs.getInt("fo.guest_id"),
                                    rs.getString("u.firstName") + " " + rs.getString("u.lastName"),
                                    rs.getInt("fo.manager_id"),
                                    rs.getInt("fo.housekeeper_id"),
                                    rs.getInt("r.room_no"),
                                    rs.getString("b2.name"),
                                    rs.getString("b2.location_name"),
                                    rs.getString("res.name"),
                                    rs.getString("res.location_name")
                            );

                    try {
                        orders.add(manageOrderDTO);
                    } catch (Exception e) {
                        throw new Exception("Result set read problem");
                    }
                }
                preResId = curResId;
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

        return orders;
    }


    public List<OrderDetailsDTO> viewOrder(int orderId) throws Exception {
        List<OrderDetailsDTO> foods = new ArrayList<>();

        String query = "SELECT * FROM (Users u, Room r, Building b), (Restaurant res), (Reservation res2), " +
                "(Food_Order fo NATURAL JOIN Order_Contains oc NATURAL JOIN Food_Drink fd ) WHERE u.id = res2.guest_id " +
                "AND b.building_no = r.building_no AND res.restaurant_id = fd.restaurant_id AND  res2.building_no = r.building_no" +
                " AND res2.room_no = r.room_no AND fo.guest_id = u.id AND oc.order_id = "+orderId+";";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query,DatabaseConnection.FETCH);

        ResultSet rs = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            while ( rs.next()){
                OrderDetailsDTO orderDetailsDTO =
                        new OrderDetailsDTO(
                                rs.getString("u.firstname") + " " + rs.getString("u.lastname"),
                                rs.getString("fd.name"),
                                rs.getDouble("fd.price"),
                                rs.getString("res.name"),
                                rs.getString("b.name"),
                                rs.getInt("r.room_no"),
                                rs.getInt("fo.housekeeper_id")
                        );

                try{
                    foods.add(orderDetailsDTO);
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

        return foods;
    }

    public MessageResponse assignOrder(int orderId, int housekeeperId, int managerId) {
        String query;

        query = "UPDATE Food_Order fo SET fo.manager_id = "+ managerId +", fo.housekeeper_id = "+ housekeeperId +"," +
                "fo.status = 'ASSIGNED', fo.assign_time = "+ System.currentTimeMillis() + " WHERE fo.order_id = " + orderId +";";

        if( userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Updating orders failed",MessageType.ERROR);
        }

        return new MessageResponse("Update success",MessageType.SUCCESS);
    }


    public MessageResponse deliverOrder(int orderId) {
        String query;

        query = "UPDATE Food_Order fo SET fo.delivery_time = "+ System.currentTimeMillis()  +",fo.status = 'DELIVERED' " +
                "WHERE fo.order_id = " + orderId +";";

        if( userInsertion.executeUpdate(query).getMessageType().equals(MessageType.ERROR)){
            return new MessageResponse("Updating orders failed",MessageType.ERROR);
        }

        return new MessageResponse("Update success",MessageType.SUCCESS);
    }


    public List<HousekeeperDTO> getFreeHousekeepers() throws Exception {

        List<HousekeeperDTO> hsList = new ArrayList<>();

        String query = "SELECT * FROM Users ho NATURAL JOIN Housekeeper u WHERE u.id NOT IN ("+
                " SELECT fo.housekeeper_id FROM Food_Order fo WHERE fo.status = 'ASSIGNED' );";

        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query,DatabaseConnection.FETCH);

        ResultSet rs = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];

        try {
            while ( rs.next()){
                HousekeeperDTO housekeeperDTO =
                        new HousekeeperDTO(
                                rs.getInt("ho.id"),
                                rs.getString("ho.firstname") + " " + rs.getString("ho.lastname")
                        );

                try{
                    hsList.add(housekeeperDTO);
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

        return hsList;
    }

    public AssignedOrderDTO getAssignedOrder(int housekeeperId) throws Exception {
        AssignedOrderDTO assignedOrderDTO = null;

        String query = "SELECT * FROM (Users u1 NATURAL JOIN Guests g), Room r, Reservation res, Building as b, Users u2, " +
                "( Food_Order fo NATURAL JOIN Order_Contains oc NATURAL JOIN Food_Drink fd), Restaurant ret WHERE b.building_no = r.building_no " +
                "AND fo.status = 'ASSIGNED' AND res.guest_id = g.id AND res.room_no = r.room_no AND res.building_no = r.building_no AND " +
                "fo.guest_id = g.id AND fo.manager_id = u2.id AND  ret.restaurant_id = fd.restaurant_id AND fo.housekeeper_id = " + housekeeperId + ";";
        Object[] resultArr = null;
        resultArr = databaseConnection.execute(query,DatabaseConnection.FETCH);

        ResultSet rs = (ResultSet) resultArr[0];
        Connection connection = (Connection) resultArr[1];
        List<String> foods = new ArrayList<>();

        try {
            while(rs.next()) {
                foods.add(rs.getString("fd.name"));
                assignedOrderDTO =
                        new AssignedOrderDTO(
                                rs.getInt("fo.order_id"),
                                rs.getString("u1.firstname") + " " + rs.getString("u1.lastname"),
                                rs.getInt("r.room_no"),
                                rs.getString("r.building_no"),
                                rs.getString("b.location_name"),
                                rs.getString("ret.name"),
                                rs.getString("ret.location_name"),
                                rs.getString("u2.firstname") + " " + rs.getString("u2.lastname"),
                                null
                        );
            }
            assignedOrderDTO.setFoodNames(foods);
            connection.close();
        }
        catch ( Exception e ){
            try {
                connection.close();
            }catch (Exception e1 ){
                throw new Exception("Cannot close connection");
            }
            throw new Exception("rs problem errors or no such guest");
        }

        return assignedOrderDTO;
    }


}
