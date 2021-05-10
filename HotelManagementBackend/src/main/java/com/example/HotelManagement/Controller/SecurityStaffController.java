package com.example.HotelManagement.Controller;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.SecurityStaffOperations.SecurityStaffOperations;
import com.example.HotelManagement.SecurityStaffOperations.SecurityWalkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class SecurityStaffController {

    private final SecurityStaffOperations securityStaffOperations;

    @Autowired
    public SecurityStaffController(SecurityStaffOperations securityStaffOperations) {
        this.securityStaffOperations = securityStaffOperations;
    }

    /**
     *Exception handlers
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponse> illegalArgHandler(IllegalArgumentException illegalArgumentException ){
        return ResponseEntity.badRequest().body(new MessageResponse(illegalArgumentException.getMessage(),MessageType.ERROR));
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<MessageResponse> illegalStateHandler(IllegalStateException illegalStateException ){
        return ResponseEntity.badRequest().body(new MessageResponse(illegalStateException.getMessage(),MessageType.ERROR));
    }
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<MessageResponse> sqlExceptionHandler(SQLException sqlException){
        return ResponseEntity.badRequest().body(new MessageResponse(sqlException.getMessage(),MessageType.ERROR));
    }

    @PostMapping("/assignSecurityWalk")
    public MessageResponse assignSecurityWalk(@RequestBody SecurityWalkDTO securityWalkDTO) throws SQLException {
        return securityStaffOperations.assignSecurityWalk(
               securityWalkDTO.getMgrId(),
               securityWalkDTO.getSsId(),
               securityWalkDTO.getBuildingNo(),
               securityWalkDTO.getStartDate(),
               securityWalkDTO.getEndDate()
        );
    }
}
