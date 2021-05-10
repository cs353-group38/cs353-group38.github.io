package com.example.HotelManagement.Controller;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.Events.CreateEvent;
import com.example.HotelManagement.Events.CreateEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final CreateEvent createEvent;

    @Autowired
    public EventController(CreateEvent createEvent) {
        this.createEvent = createEvent;
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
}
