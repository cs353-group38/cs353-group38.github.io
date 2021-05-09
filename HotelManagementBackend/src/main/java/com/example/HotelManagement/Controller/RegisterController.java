package com.example.HotelManagement.Controller;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.Entity.User;
import com.example.HotelManagement.SignUp.UserInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private final UserInsertion userInsertion;

    @Autowired
    public RegisterController(UserInsertion userInsertion) {
        this.userInsertion = userInsertion;
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
}
