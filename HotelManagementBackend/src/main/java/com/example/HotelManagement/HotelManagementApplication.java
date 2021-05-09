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
		SpringApplication.run(HotelManagementApplication.class, args);
	}

}
