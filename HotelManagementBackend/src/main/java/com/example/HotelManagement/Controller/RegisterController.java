package com.example.HotelManagement.Controller;

import com.example.HotelManagement.AnnualLeave.AnnualLeaveDTO;
import com.example.HotelManagement.AnnualLeave.DetailedAnnuaLeaveDTO;
import com.example.HotelManagement.AnnualLeave.ManageAnnualLeaves;
import com.example.HotelManagement.AnnualLeave.ResponseDTO;
import com.example.HotelManagement.Comment.CommentDTO;
import com.example.HotelManagement.Comment.MakeComment;
import com.example.HotelManagement.Comment.ViewCommentDTO;
import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Entity.User;
import com.example.HotelManagement.Food.*;
import com.example.HotelManagement.Reserve.*;
import com.example.HotelManagement.SignUp.UserInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class RegisterController {

    private final UserInsertion userInsertion;
    private final ViewReservations viewReservations;
    private final MakeReservations makeReservations;
    private final ViewFoods viewFoods;
    private final ManageAnnualLeaves manageAnnualLeaves;
    private final MakeComment makeComment;

    @Autowired
    public RegisterController(UserInsertion userInsertion, ViewReservations viewReservations, MakeReservations makeReservations,
                              ViewFoods viewFoods, ManageAnnualLeaves manageAnnualLeaves, MakeComment makeComment) {
        this.userInsertion = userInsertion;
        this.viewReservations = viewReservations;
        this.makeReservations = makeReservations;
        this.viewFoods = viewFoods;
        this.manageAnnualLeaves = manageAnnualLeaves;
        this.makeComment = makeComment;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> errorHandler(Exception e ){
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(),MessageType.ERROR));
    }

    @GetMapping("/login/{type}/{email}/{password}")
    public LoginDTO login( @PathVariable("type") String type, @PathVariable("email") String email, @PathVariable("password") String password) throws Exception {
        LoginDTO loginDTO = makeReservations.login(type,email,password);
        loginDTO.setRole(type);
        return loginDTO;
    }
    /**
     * Registers a guest or a candidate to the system
     * @param type
     * @param user
     * @return
     */
    @PostMapping("/register/{type}")
    public MessageResponse register(@PathVariable("type") String type, @RequestBody User user ){
        if( type.equals("CANDIDATE")){
            return userInsertion.insertCandidate(
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getGender(),
                    user.getDate_of_birth(),
                    ""
                );
        }
        else if(type.equals("GUEST")) {
            return userInsertion.insertGuest(
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getGender(),
                    user.getDate_of_birth(),
                    0
            );
        }
        return new MessageResponse("Error", MessageType.ERROR);
    }

    /**
     * View all of the reservations
     * @return
     * @throws Exception
     */
    @GetMapping("/reservations")
    public List<ReservationDTO> viewAllReservations() throws Exception {

        return viewReservations.viewAllReservations();
    }

    /**
     * View all of the reservations
     * @return
     * @throws Exception
     */
    @PostMapping("/availableRoomTypes")
    public List<String> viewAllRoomTypes( @RequestBody MakeReservationDTO makeReservationDTO) throws Exception {
        return viewReservations.viewRoomTypes(makeReservationDTO);
    }

    /**
     * View reservations of a spesific guest
     * @param guestId
     * @return
     * @throws Exception
     */
    @GetMapping("/reservation/{guestId}")
    public ReservationDTO viewReservation( @PathVariable("guestId") int guestId ) throws Exception {

        return viewReservations.viewReservation(guestId);
    }

    /**
     * View all empty rooms
     * @return
     * @throws Exception
     */
    @GetMapping("/emptyRooms")
    public List<RoomDTO> viewAllEmptyRooms() throws Exception {
        return viewReservations.getAllEmptyRooms();
    }

    /**
     * Reserve a room
     * @param reservationDTO
     * @return
     */
    @PostMapping("/reserve")
    public MessageResponse reserve( @RequestBody MakeReservationDTO reservationDTO){
        return makeReservations.makeReservation(reservationDTO);
    }

    /**
     * View all of the foods
     * @return
     * @throws Exception
     */
    @GetMapping("/foods")
    public List<FoodDTO> viewAllFoods() throws Exception {
        return viewFoods.getAllFoods();
    }

    /**
     * Used to order a food
     * @param orderDTO coming json
     * @return
     */
    @PostMapping("/order")
    public MessageResponse order(@RequestBody OrderDTO orderDTO){
        return viewFoods.order(orderDTO);
    }

    /**
     * Used to view all of the orders
     * @return
     * @throws Exception
     */
    @GetMapping("/viewOrders")
    public List<ManageOrderDTO> viewOrders() throws Exception {
        return viewFoods.viewOrders();
    }

    /**
     * Used to view a spesific order
     * @param orderId
     * @return
     * @throws Exception
     */
    @GetMapping("/viewOrder/{orderId}")
    public List<OrderDetailsDTO> viewOrder(@PathVariable("orderId") int orderId ) throws Exception {
        return viewFoods.viewOrder(orderId);
    }

    /**
     * Used to assign an order to a housekeeper by a manager
     * @param orderId
     * @param housekeeperId
     * @param managerId
     * @return
     */
    @PutMapping("/assignOrder/{orderId}/{housekeeperId}/{managerId}")
    public MessageResponse assignOrder( @PathVariable("orderId") int orderId,
                                        @PathVariable("housekeeperId") int housekeeperId,
                                        @PathVariable("managerId") int managerId){
        return viewFoods.assignOrder(orderId,housekeeperId,managerId);
    }

    /**
     * Used when the order is delivered
     * @param orderId
     * @return
     */
    @PutMapping("/deliverOrder/{orderId}")
    public MessageResponse deliverOrder( @PathVariable("orderId") int orderId ){
        return viewFoods.deliverOrder(orderId);
    }

    /**
     * Used to view available housekeepers
     * @return
     * @throws Exception
     */
    @GetMapping("/availableHousekeepers")
    public List<HousekeeperDTO> getAvailable() throws Exception {
        return viewFoods.getFreeHousekeepers();
    }

    /**
     * Used to view assigned orders by a housekeeper
     * @param housekeeperId
     * @return
     * @throws Exception
     */
    @GetMapping("/assignedOrder/{housekeeperId}")
    public AssignedOrderDTO getAssignedOrder( @PathVariable("housekeeperId") int housekeeperId) throws Exception {
        return viewFoods.getAssignedOrder(housekeeperId);
    }

    /**
     * Used to all the history of annual leaves of that employee
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/viewAnnualLeaves/{employeeId}")
    public List<AnnualLeaveDTO> getAnnualLeaves( @PathVariable("employeeId") int id) throws Exception {
        return manageAnnualLeaves.viewLeaves(id);
    }

    /**
     * Used to request a leave by an employee
     * @param id
     * @param annualLeaveDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/requestLeave/{employeeId}")
    public MessageResponse leaveRequest( @PathVariable("employeeId") int id, @RequestBody AnnualLeaveDTO annualLeaveDTO) throws Exception {
        return manageAnnualLeaves.leaveRequest(id,annualLeaveDTO);
    }

    /**
     * Used to view the left days of the employee for annual leave
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/viewLeaveDays/{employeeId}")
    public int getDays(  @PathVariable("employeeId") int id ) throws Exception {
        return manageAnnualLeaves.getAnnualDays(id);
    }

    /**
     * Used to view leave requests
     * @return
     * @throws Exception
     */
    @GetMapping("/viewLeaveRequests")
    public List<DetailedAnnuaLeaveDTO> getLeaveRequests() throws Exception {
        return manageAnnualLeaves.viewAllLeaveRequests();
    }

    /**
     * Used to respond to a leave request
     * @param responseDTO
     * @return
     */
    @PutMapping("/answerRequestLeave")
    public MessageResponse answer( @RequestBody ResponseDTO responseDTO ){
        return manageAnnualLeaves.respond(responseDTO);
    }

    /**
     * Used to comment on a reservation
     * @param commentDTO
     * @return
     */
    @PostMapping("/comment")
    public MessageResponse comment( @RequestBody CommentDTO commentDTO ){
        return makeComment.comment(commentDTO);
    }

    /**
     * Used to view all of the comments
     * @return
     * @throws Exception
     */
    @GetMapping("/viewComments")
    public List<ViewCommentDTO> viewComments() throws Exception {
        return makeComment.viewComments();
    }

    /**
     * Used to view comments of a guest
     * @param guestId
     * @return
     * @throws Exception
     */
    @GetMapping("/viewComments/{guestId}")
    public List<ViewCommentDTO> viewCommentsGuest( @PathVariable("guestId") int guestId ) throws Exception {
        return makeComment.viewCommentsGuest(guestId);
    }

}
