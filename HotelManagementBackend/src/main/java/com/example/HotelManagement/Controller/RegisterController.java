package com.example.HotelManagement.Controller;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Entity.User;
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

    @Autowired
    public RegisterController(UserInsertion userInsertion, ViewReservations viewReservations, MakeReservations makeReservations) {
        this.userInsertion = userInsertion;
        this.viewReservations = viewReservations;
        this.makeReservations = makeReservations;
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


}
