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
    public MessageResponse buyTicket(@PathVariable int guestId, @PathVariable int eventId) throws Exception {
        return buyTicket.buyTicket(guestId, eventId);
    }
}
