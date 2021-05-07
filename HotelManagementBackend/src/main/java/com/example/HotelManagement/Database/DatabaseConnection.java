package com.example.HotelManagement.Database;

import java.sql.*;

public class DatabaseConnection {

    public final static int FETCH = 0;
    public final static int UPDATE = 1;

    private final String url = "jdbc:mysql://dijkstra.ug.bcc.bilkent.edu.tr:3306/bora_cun";
    private final String user = "bora.cun";
    private final String pass = "DPZ3a7Km";

    public DatabaseConnection() {}

    /**
     * This method creates a connection, and executes a query or update.
     * @param query The desired SQL update or query as string
     * @param type determines the type of the execution: FETCH for query, UPDATE for update
     * @return The result of the query as a ResultSet. null if type is UPDATE.
     */
    public ResultSet execute(String query, int type ) {
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

    /*
    ============================DROP TABLE METHODS============================
     */

    /**
     * Drops the given table if it exists.
     * @param tableName The name of the table
     * @return 1 if successful, 0 if failure
     */
    public int dropTable(String tableName) {
        String update = "DROP TABLE IF EXISTS " + tableName + ";";
        try {
            execute(update, UPDATE);
            return 1;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /*
    ============================CREATE TABLE METHODS============================
     */

    /**
     * Creates the given table.
     * @param query SQL Code of table definition
     * @return 1 for success, 0 for failure
     */
    public int createTable(String query) {
        try {
            execute(query, UPDATE);
            return 1;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /*
    ============================TABLE DEFINITIONS============================
     */
    public static final String CREATE_USERS = "CREATE TABLE IF NOT EXISTS Users(\n" +
            "                      id INT,\n" +
            "                      firstname VARCHAR(50) NOT NULL,\n" +
            "                      lastname VARCHAR(50) NOT NULL,\n" +
            "                      email VARCHAR(75) NOT NULL UNIQUE,\n" +
            "                      password VARCHAR(20) NOT NULL,\n" +
            "                      phone VARCHAR(20),\n" +
            "                      address VARCHAR(100),\n" +
            "                      gender VARCHAR(20),\n" +
            "                      date_of_birth DATE,\n" +
            "                      PRIMARY KEY (id),\n" +
            "                      CHECK (date_of_birth < CURDATE() AND id > 0 ) );";

    public static final String CREATE_EMPLOYEE = "CREATE TABLE IF NOT EXISTS Employee(\n" +
            "    id INT,\n" +
            "    salary NUMERIC(18, 2) NOT NULL,\n" +
            "    employment_date DATE NOT NULL,\n" +
            "    annual_leave INT NOT NULL,\n" +
            "    PRIMARY KEY (id),\n" +
            "    CHECK (salary >= 0 AND id > 0 AND annual_leave > 0));";

    public static final String CREATE_RECEPTIONIST = "CREATE TABLE IF NOT EXISTS Receptionist(\n" +
            "    id INT,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Employee(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0));";

    public static final String CREATE_CANDIDATE = "CREATE TABLE IF NOT EXISTS Candidate(\n" +
            "    id INT,\n" +
            "    cover_letter VARCHAR(5000) NOT NULL,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Users(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0));";

    public static final String CREATE_RECRUITER = "CREATE TABLE IF NOT EXISTS Recruiter(\n" +
            "    id INT,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Employee(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0));";

    public static final String CREATE_SECURITY_STAFF = "CREATE TABLE IF NOT EXISTS Security_Staff(\n" +
            "    id INT,\n" +
            "    security_rank VARCHAR(20) NOT NULL,\n" +
            "    weapon VARCHAR(20),\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Employee(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0));";

    public static final String CREATE_MANAGER = "CREATE TABLE IF NOT EXISTS Manager(\n" +
            "    id INT,\n" +
            "    office_no VARCHAR(10) NOT NULL,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Employee(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0) );";

    public static final String CREATE_GUESTS = "CREATE TABLE IF NOT EXISTS Guests(\n" +
            "    id INT,\n" +
            "    money_spent NUMERIC(18, 2) NOT NULL,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Users(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0 AND money_spent > 0));";

    public static final String CREATE_HOUSEKEEPER = "CREATE TABLE IF NOT EXISTS Housekeeper(\n" +
            "    id INT,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Employee(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0));";
}
