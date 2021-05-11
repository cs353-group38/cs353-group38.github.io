package com.example.HotelManagement.Controller;

import com.example.HotelManagement.DTO.MessageResponse;
import com.example.HotelManagement.DTO.MessageType;
import com.example.HotelManagement.SignUp.EvaluateJobApplicationDTO;
import com.example.HotelManagement.SignUp.JobApplication;
import com.example.HotelManagement.SignUp.JobApplicationDTO;
import com.example.HotelManagement.SignUp.UserInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
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
}
