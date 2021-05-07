package com.example.HotelManagement;

import com.example.HotelManagement.Database.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelManagementApplication {

	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		/*
		databaseConnection.createTable(DatabaseConnection.CREATE_EMPLOYEE);
		databaseConnection.createTable(DatabaseConnection.CREATE_RECEPTIONIST);
		databaseConnection.createTable(DatabaseConnection.CREATE_CANDIDATE);
		databaseConnection.createTable(DatabaseConnection.CREATE_RECRUITER);
		databaseConnection.createTable(DatabaseConnection.CREATE_SECURITY_STAFF);
		databaseConnection.createTable(DatabaseConnection.CREATE_MANAGER);
		databaseConnection.createTable(DatabaseConnection.CREATE_GUESTS);
		databaseConnection.createTable(DatabaseConnection.CREATE_HOUSEKEEPER);
		*/
		SpringApplication.run(HotelManagementApplication.class, args);
	}

}
