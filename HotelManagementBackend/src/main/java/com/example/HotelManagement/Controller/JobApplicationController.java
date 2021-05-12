package com.example.HotelManagement.Controller;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.SignUp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@CrossOrigin("*")
public class JobApplicationController {

    private final JobApplication jobApplication;
    private final UserInsertion userInsertion;

    @Autowired
    public JobApplicationController(JobApplication jobApplication, UserInsertion userInsertion) {
        this.jobApplication = jobApplication;
        this.userInsertion = userInsertion;
    }

    @PostMapping("/createJobApplication")
    public MessageResponse createJobApplication(@RequestBody JobApplicationDTO dto) {
        return jobApplication.createJobApplication(dto.getCandidateId(), dto.getPosition());
    }

    @PostMapping("/evaluateJobApplication")
    public MessageResponse evaluateJobApplication(@RequestBody EvaluateJobApplicationDTO dto) {
        if(dto.getStatus().toUpperCase(Locale.ENGLISH).equals("APPROVED"))
            return jobApplication.approveJobApplication(dto.getCandidateId(), dto.getPosition(), dto.getRecruiterId());
        else if (dto.getStatus().toUpperCase(Locale.ENGLISH).equals("REJECTED"))
            return jobApplication.rejectJobApplication(dto.getCandidateId(), dto.getPosition(), dto.getRecruiterId());
        else
            return new MessageResponse("Application status should either be APPROVED or REJECTED", MessageType.ERROR);
    }

    @GetMapping("/viewJobApplication/{candidateId}/{position}")
    public ViewCandidateDTO viewJobApplication(@PathVariable(name = "candidateId") int candidateId, @PathVariable(name = "position") String position) {
        return jobApplication.viewJobApplication(candidateId, position);
    }

    @GetMapping("viewAllJobApplications")
    public ViewAllCandidatesDTO viewAllJobApplications() {
        return jobApplication.viewAllJobApplications();
    }
    
    @GetMapping("/viewMyJobApplications/{candidateId}")
    public ViewAllCandidatesDTO viewMyJobApplications(@PathVariable(name = "candidateId") int candidateId) {
        return jobApplication.viewMyJobApplications(candidateId);
    }
}
