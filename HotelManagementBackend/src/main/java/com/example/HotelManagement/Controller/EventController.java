package com.example.HotelManagement.Controller;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.Events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    private final CreateEvent createEvent;
    private final BuyTicket buyTicket;

    @Autowired
    public EventController(CreateEvent createEvent, BuyTicket buyTicket) {
        this.createEvent = createEvent;
        this.buyTicket = buyTicket;
    }

    @PostMapping("/createTrainingProgram")
    public MessageResponse createTrainingProgram(@RequestBody CreateEventDTO createEventDTO) {
        return createEvent.createTrainingProgram(
                createEventDTO.getEventName(),
                createEventDTO.getLocationName(),
                createEventDTO.getStartDate(),
                createEventDTO.getEndDate(),
                createEventDTO.getMinAge(),
                createEventDTO.getQuota(),
                createEventDTO.getDescription(),
                createEventDTO.getMgrId()
        );
    }

    @PostMapping("/createActivity")
    public MessageResponse createActivity(@RequestBody CreateGuestActivityDTO createGuestActivityDTO) {
        return createEvent.createActivity(
                createGuestActivityDTO.getEventName(),
                createGuestActivityDTO.getLocationName(),
                createGuestActivityDTO.getStartDate(),
                createGuestActivityDTO.getEndDate(),
                createGuestActivityDTO.getMinAge(),
                createGuestActivityDTO.getQuota(),
                createGuestActivityDTO.getDescription(),
                createGuestActivityDTO.getMgrId(),
                createGuestActivityDTO.getPrice()
        );
    }

    @PostMapping("/createGroupTour")
    public MessageResponse createGroupTours(@RequestBody CreateGroupToursDTO createGroupToursDTO) {
        return createEvent.createGroupTours(
                createGroupToursDTO.getEventName(),
                createGroupToursDTO.getLocationName(),
                createGroupToursDTO.getStartDate(),
                createGroupToursDTO.getEndDate(),
                createGroupToursDTO.getMinAge(),
                createGroupToursDTO.getQuota(),
                createGroupToursDTO.getDescription(),
                createGroupToursDTO.getMgrId(),
                createGroupToursDTO.getPrice(),
                createGroupToursDTO.getOrganizerName(),
                createGroupToursDTO.getTourVehicle(),
                createGroupToursDTO.getDistanceToCover()
        );
    }

    @PostMapping("/buyTicket/{eventId}/{guestId}")
    public MessageResponse buyTicket(@PathVariable(name = "guestId") int guestId, @PathVariable(name = "eventId") int eventId) throws Exception {
        return buyTicket.buyTicket(guestId, eventId);
    }

    /**
     * A housekeeper applies to a training program
     * @param hkId housekeeper id
     * @param eventId event id
     * @return Message response
     * @throws Exception exception
     */
    @PostMapping("/applyAsHK/{eventId}/{hkId}")
    public MessageResponse applyAsHousekeeper(@PathVariable(name = "hkId") int hkId, @PathVariable(name = "eventId") int eventId) throws Exception {
        return buyTicket.applyAsHousekeeper(hkId, eventId);
    }

    /**
     * A security staff applies to a training program
     * @param ssId security staff
     * @param eventId event id
     * @return Message response
     * @throws Exception exception
     */
    @PostMapping("/applyAsSS/{eventId}/{ssId}")
    public MessageResponse applyAsSecurityStaff(@PathVariable(name = "ssId") int ssId, @PathVariable(name = "eventId") int eventId) throws Exception {
        return buyTicket.applyAsSecurityStaff(ssId, eventId);
    }

    @PostMapping("/evaluateHKApplication")
    public MessageResponse evaluateHKApplication(@RequestBody EvaluateEmployeeDTO dto) {
        return buyTicket.evaluateHKApplication(dto.getEmployeeId(), dto.getEventId(), dto.getMgrId(), dto.getStatus());
    }

    @PostMapping("/evaluateSSApplication")
    public MessageResponse evaluateSSApplication(@RequestBody EvaluateEmployeeDTO dto) {
        return buyTicket.evaluateSSApplication(dto.getEmployeeId(), dto.getEventId(), dto.getMgrId(), dto.getStatus());
    }
}
