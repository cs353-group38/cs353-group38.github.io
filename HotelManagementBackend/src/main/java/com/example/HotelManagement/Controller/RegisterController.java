package com.example.HotelManagement.Controller;

import com.example.HotelManagement.AnnualLeave.ManageAnnualLeaves;
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
public class RegisterController {

    private final UserInsertion userInsertion;
    private final ViewReservations viewReservations;
    private final MakeReservations makeReservations;
    private final ViewFoods viewFoods;
    private final ManageAnnualLeaves manageAnnualLeaves;

    @Autowired
    public RegisterController(UserInsertion userInsertion, ViewReservations viewReservations, MakeReservations makeReservations, ViewFoods viewFoods, ManageAnnualLeaves manageAnnualLeaves) {
        this.userInsertion = userInsertion;
        this.viewReservations = viewReservations;
        this.makeReservations = makeReservations;
        this.viewFoods = viewFoods;
        this.manageAnnualLeaves = manageAnnualLeaves;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> errorHandler(Exception e ){
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(),MessageType.ERROR));
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

    @GetMapping("/reservations")
    public List<ReservationDTO> viewAllReservations() throws Exception {

        return viewReservations.viewAllReservations();
    }

    @GetMapping("/reservation/{guestId}")
    public ReservationDTO viewReservation( @PathVariable("guestId") int guestId ) throws Exception {

        return viewReservations.viewReservation(guestId);
    }

    @GetMapping("/emptyRooms")
    public List<RoomDTO> viewAllEmptyRooms() throws Exception {
        return viewReservations.getAllEmptyRooms();
    }

    @PostMapping("/reserve")
    public MessageResponse reserve( @RequestBody MakeReservationDTO reservationDTO){
        return makeReservations.makeReservation(reservationDTO);
    }

    @GetMapping("/foods")
    public List<FoodDTO> viewAllFoods() throws Exception {
        return viewFoods.getAllFoods();
    }

    @PostMapping("/order")
    public MessageResponse order(@RequestBody OrderDTO orderDTO){
        return viewFoods.order(orderDTO);
    }

    @GetMapping("/viewOrders")
    public List<ManageOrderDTO> viewOrders() throws Exception {
        return viewFoods.viewOrders();
    }

    @GetMapping("/viewOrder/{orderId}")
    public List<OrderDetailsDTO> viewOrder(@PathVariable("orderId") int orderId ) throws Exception {
        return viewFoods.viewOrder(orderId);
    }

    @PutMapping("/assignOrder/{orderId}/{housekeeperId}/{managerId}")
    public MessageResponse assignOrder( @PathVariable("orderId") int orderId,
                                        @PathVariable("housekeeperId") int housekeeperId,
                                        @PathVariable("managerId") int managerId){
        return viewFoods.assignOrder(orderId,housekeeperId,managerId);
    }

    @PutMapping("/deliverOrder/{orderId}")
    public MessageResponse deliverOrder( @PathVariable("orderId") int orderId ){
        return viewFoods.deliverOrder(orderId);
    }

    @GetMapping("/availableHousekeepers")
    public List<HousekeeperDTO> getAvailable() throws Exception {
        return viewFoods.getFreeHousekeepers();
    }

    @GetMapping("/assignedOrder/{housekeeperId}")
    public AssignedOrderDTO getAssignedOrder( @PathVariable("housekeeperId") int housekeeperId) throws Exception {
        return viewFoods.getAssignedOrder(housekeeperId);
    }
}
