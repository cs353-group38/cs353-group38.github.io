package com.example.HotelManagement;

import com.example.HotelManagement.Database.DatabaseConnection;
import com.example.HotelManagement.SignUp.UserFetch;
import com.example.HotelManagement.SignUp.UserInsertion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class HotelManagementApplication {

	public static void main(String[] args) throws SQLException {
		//These are for test purposes, will be deleted later
		DatabaseConnection databaseConnection = new DatabaseConnection();
		UserFetch userFetch = new UserFetch(databaseConnection);
		UserInsertion userInsertion = new UserInsertion(databaseConnection, userFetch);
		//databaseConnection.dropAllTables();
		//databaseConnection.createAllTables();
		//userInsertion.insertGuest("Eray", "Tuzun", "eray2@bilkent", "password", "532587",
		//		"Ankara", "Male", 123123123L, 0);
		//--------------------------------------------------
		SpringApplication.run(HotelManagementApplication.class, args);
	}

}
