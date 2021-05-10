package com.example.HotelManagement.AnnualLeave;

import com.example.HotelManagement.Database.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageAnnualLeaves {

    private final DatabaseConnection databaseConnection;

    @Autowired
    public ManageAnnualLeaves(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

}
