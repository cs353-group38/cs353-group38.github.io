package com.example.HotelManagement.Controller;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.SecurityStaffOperations.SecurityStaffOperations;
import com.example.HotelManagement.SecurityStaffOperations.SecurityWalkDTO;
import com.example.HotelManagement.SecurityStaffOperations.ViewAllSecurityStaffDTO;
import com.example.HotelManagement.SecurityStaffOperations.ViewAllSecurityWalksDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin("*")
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

    @GetMapping("/viewAllSecurityWalks")
    public ViewAllSecurityWalksDTO viewAllSecurityWalks() {
        return securityStaffOperations.viewAllSecurityWalks();
    }

    @GetMapping("/viewAllSecurityWalks/{ssId}")
    public ViewAllSecurityWalksDTO viewSecurityStaffWalks(@PathVariable(name = "ssId") int ssId) {
        return securityStaffOperations.viewSecurityStaffWalks(ssId);
    }

    @GetMapping("/viewAllSecurityStaff")
    public ViewAllSecurityStaffDTO viewAllSecurityStaff() {
        return securityStaffOperations.viewAllSecurityStaff();
    }
}
