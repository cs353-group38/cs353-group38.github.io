package com.example.HotelManagement;

import com.example.HotelManagement.Database.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelManagementApplication {

	public static void main(String[] args) {
		//These are for test purposes, will be deleted later
		DatabaseConnection databaseConnection = new DatabaseConnection();
		//databaseConnection.dropAllTables();
		//databaseConnection.createAllTables();
		//--------------------------------------------------
		System.out.println(Math.random());
		SpringApplication.run(HotelManagementApplication.class, args);
	}

}
