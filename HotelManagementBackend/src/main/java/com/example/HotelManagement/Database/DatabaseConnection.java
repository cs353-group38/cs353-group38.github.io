package com.example.HotelManagement.Database;

import java.sql.*;

public class DatabaseConnection {

    public final static int FETCH = 0;
    public final static int UPDATE = 1;

    private final String url = "jdbc:mysql://dijkstra.ug.bcc.bilkent.edu.tr:3306/bora_cun";
    private final String user = "bora.cun";
    private final String pass = "DPZ3a7Km";

    public DatabaseConnection() {}

    public ResultSet execute(String query , int type ) {
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = null;
            //Connection
            System.out.println("Connecting to the database...");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection successful.");

            stmt = con.createStatement();

            if( type == FETCH ){
                resultSet = stmt.executeQuery(query);
                con.close();
                return resultSet;
            }
            else if( type == UPDATE) {
                stmt.executeUpdate(query);
                con.close();
                return null;
            }
        }
        catch (Exception e) {

            try {
                con.close();
            }catch ( Exception e1 ){
                System.out.println(e1.getMessage());
            }

            System.out.println(e.getMessage());
        }
        return null;
    }



}
