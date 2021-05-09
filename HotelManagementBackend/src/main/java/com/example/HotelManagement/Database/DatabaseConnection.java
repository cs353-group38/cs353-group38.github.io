package com.example.HotelManagement.Database;

import org.apache.coyote.http11.filters.SavedRequestInputFilter;

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
    public Object[] execute(String query, int type ) {
        Object[] arr = new Object[2];
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
                arr[0] = resultSet;
                arr[1] = con;
                return arr;
            }
            else if( type == UPDATE) {
                stmt.executeUpdate(query);
                arr[0] = null;
                arr[1] = con;
                return arr;
            }
        }
        catch (Exception e) {
            arr[0] = null;
            arr[1] = con;
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
        String update = "DROP TABLE IF EXISTS " + tableName + " CASCADE;";
        try {
            execute(update, UPDATE);
            System.out.println("Dropped " + tableName + ".");
            return 1;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Drops all the tables in the database. Be careful when using this, every table will be gone.
     * @return 1 if successful, 0 if failed
     */
    public int dropAllTables() {
        if (dropTable("Cleaning_Duty") == 0) {return 0;}
        if (dropTable("Security_Walk") == 0) {return 0;}
        if (dropTable("Approves") == 0) {return 0;}
        if (dropTable("Evaluates_HK_Application") == 0) {return 0;}
        if (dropTable("Evaluates_Sec_Staff_Application") == 0) {return 0;}
        if (dropTable("HK_Applies_To") == 0) {return 0;}
        if (dropTable("Sec_Staff_Applies_To") == 0) {return 0;}
        if (dropTable("Places_To_Visit") == 0) {return 0;}
        if (dropTable("Order_Contains") == 0) {return 0;}
        if (dropTable("Leave_Request_Form") == 0) {return 0;}
        if (dropTable("Security_Report") == 0) {return 0;}
        if (dropTable("Job_Application") == 0) {return 0;}
        if (dropTable("Food_Drink") == 0) {return 0;}
        if (dropTable("Restaurant") == 0) {return 0;}
        if (dropTable("Food_Order") == 0) {return 0;}
        if (dropTable("Tourist_Attraction") == 0) {return 0;}
        if (dropTable("Ticket") == 0) {return 0;}
        if (dropTable("Training_Program") == 0) {return 0;}
        if (dropTable("Group_Tours") == 0) {return 0;}
        if (dropTable("Activity") == 0) {return 0;}
        if (dropTable("Guest_Activity") == 0) {return 0;}
        if (dropTable("Event") == 0) {return 0;}
        if (dropTable("Receptionist") == 0) {return 0;}
        if (dropTable("Security_Staff") == 0) {return 0;}
        if (dropTable("Manager") == 0) {return 0;}
        if (dropTable("Housekeeper") == 0) {return 0;}
        if (dropTable("Recruiter") == 0) {return 0;}
        if (dropTable("Employee") == 0) {return 0;}
        if (dropTable("Candidate") == 0) {return 0;}
        if (dropTable("Guests") == 0) {return 0;}
        if (dropTable("Room") == 0) {return 0;}
        if (dropTable("Room_Type") == 0) {return 0;}
        if (dropTable("Reservation") == 0) {return 0;}
        if (dropTable("Comment") == 0) {return 0;}
        if (dropTable("QnA") == 0) {return 0;}
        if (dropTable("Building") == 0) {return 0;}
        if (dropTable("Location") == 0) {return 0;}
        if (dropTable("Users") == 0) {return 0;}
        return 1;
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

    /**
     * Initializes the database with empty tables.
     * @return 1 for success, 0 for failure
     */
    public int createAllTables() {
        if (createTable(CREATE_USERS) == 0) {return 0;}
        if (createTable(CREATE_EMPLOYEE) == 0) {return 0;}
        if (createTable(CREATE_RECEPTIONIST) == 0) {return 0;}
        if (createTable(CREATE_CANDIDATE) == 0) {return 0;}
        if (createTable(CREATE_RECRUITER) == 0) {return 0;}
        if (createTable(CREATE_SECURITY_STAFF) == 0) {return 0;}
        if (createTable(CREATE_MANAGER) == 0) {return 0;}
        if (createTable(CREATE_GUESTS) == 0) {return 0;}
        if (createTable(CREATE_HOUSEKEEPER) == 0) {return 0;}
        if (createTable(CREATE_LOCATION) == 0) {return 0;}
        if (createTable(CREATE_BUILDING) == 0) {return 0;}
        if (createTable(CREATE_ROOM_TYPE) == 0) {return 0;}
        if (createTable(CREATE_ROOM) == 0) {return 0;}
        if (createTable(CREATE_RESERVATION) == 0) {return 0;}
        if (createTable(CREATE_COMMENT) == 0) {return 0;}
        if (createTable(CREATE_QNA) == 0) {return 0;}
        if (createTable(CREATE_EVENT) == 0) {return 0;}
        if (createTable(CREATE_GUEST_ACTIVITY) == 0) {return 0;}
        if (createTable(CREATE_ACTIVITY) == 0) {return 0;}
        if (createTable(CREATE_GROUP_TOURS) == 0) {return 0;}
        if (createTable(CREATE_TRAINING_PROGRAM) == 0) {return 0;}
        if (createTable(CREATE_TICKET) == 0) {return 0;}
        if (createTable(CREATE_TOURIST_ATTRACTION) == 0) {return 0;}
        if (createTable(CREATE_FOOD_ORDER) == 0) {return 0;}
        if (createTable(CREATE_RESTAURANT) == 0) {return 0;}
        if (createTable(CREATE_FOOD_DRINK) == 0) {return 0;}
        if (createTable(CREATE_JOB_APPLICATION) == 0) {return 0;}
        if (createTable(CREATE_SECURITY_REPORT) == 0) {return 0;}
        if (createTable(CREATE_LEAVE_REQUEST_FORM) == 0) {return 0;}
        if (createTable(CREATE_ORDER_CONTAINS) == 0) {return 0;}
        if (createTable(CREATE_PLACES_TO_VISIT) == 0) {return 0;}
        if (createTable(CREATE_SEC_STAFF_APPLIES_TO) == 0) {return 0;}
        if (createTable(CREATE_HK_APPLIES_TO) == 0) {return 0;}
        if (createTable(CREATE_EVALUATES_SEC_STAFF_APPLICATION) == 0) {return 0;}
        if (createTable(CREATE_EVALUATES_HK_APPLICATION) == 0) {return 0;}
        if (createTable(CREATE_APPROVES) == 0) {return 0;}
        if (createTable(CREATE_SECURITY_WALK) == 0) {return 0;}
        if (createTable(CREATE_CLEANING_DUTY) == 0) {return 0;}
        return 1;
    }

    /*
    ============================USER TABLE DEFINITIONS============================
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
            "                      date_of_birth LONG,\n" +
            "                      PRIMARY KEY (id),\n" +
            "                      CHECK (date_of_birth < UNIX_TIMESTAMP() AND id > 0 ) ) ENGINE=InnoDB;";

    public static final String CREATE_EMPLOYEE = "CREATE TABLE IF NOT EXISTS Employee(\n" +
            "    id INT,\n" +
            "    salary NUMERIC(18, 2) NOT NULL,\n" +
            "    employment_date LONG NOT NULL,\n" +
            "    annual_leave INT NOT NULL,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Users(id)\n" +
            "        ON DELETE CASCADE \n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (salary >= 0 AND id > 0 AND annual_leave > 0)) ENGINE=InnoDB;";

    public static final String CREATE_RECEPTIONIST = "CREATE TABLE IF NOT EXISTS Receptionist(\n" +
            "    id INT,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Employee(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_CANDIDATE = "CREATE TABLE IF NOT EXISTS Candidate(\n" +
            "    id INT,\n" +
            "    cover_letter VARCHAR(5000) NOT NULL,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Users(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_RECRUITER = "CREATE TABLE IF NOT EXISTS Recruiter(\n" +
            "    id INT,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Employee(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_SECURITY_STAFF = "CREATE TABLE IF NOT EXISTS Security_Staff(\n" +
            "    id INT,\n" +
            "    security_rank VARCHAR(20) NOT NULL,\n" +
            "    weapon VARCHAR(20),\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Employee(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_MANAGER = "CREATE TABLE IF NOT EXISTS Manager(\n" +
            "    id INT,\n" +
            "    office_no VARCHAR(10) NOT NULL,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Employee(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0) ) ENGINE=InnoDB;";

    public static final String CREATE_GUESTS = "CREATE TABLE IF NOT EXISTS Guests(\n" +
            "    id INT,\n" +
            "    money_spent NUMERIC(18, 2) NOT NULL,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Users(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0 AND money_spent > 0)) ENGINE=InnoDB;";

    public static final String CREATE_HOUSEKEEPER = "CREATE TABLE IF NOT EXISTS Housekeeper(\n" +
            "    id INT,\n" +
            "    PRIMARY KEY (id),\n" +
            "    FOREIGN KEY (id) REFERENCES Employee(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0)) ENGINE=InnoDB;";

    /*
    ============================ROOM TABLE DEFINITIONS============================
     */
    public static final String CREATE_ROOM = "CREATE TABLE IF NOT EXISTS Room (\n" +
            "    room_no INT,\n" +
            "    building_no VARCHAR(50),\n" +
            "    type VARCHAR(50) NOT NULL,\n" +
            "    description VARCHAR(5000),\n" +
            "    PRIMARY KEY (room_no, building_no),\n" +
            "    FOREIGN KEY (type) REFERENCES Room_Type(type)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (building_no) REFERENCES Building(building_no)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (room_no > 0 AND  LENGTH(building_no) > 0 AND LENGTH(type) > 0)) ENGINE=InnoDB;";      //LENGTH(type) produces warning

    public static final String CREATE_ROOM_TYPE = "CREATE TABLE IF NOT EXISTS Room_Type (\n" +
            "    type VARCHAR(50),\n" +
            "    price NUMERIC(18, 2) NOT NULL,\n" +
            "    no_of_people INT NOT NULL,\n" +
            "    PRIMARY KEY (type),\n" +
            "    CHECK (LENGTH(type) > 0)) ENGINE=InnoDB;";   //LENGTH(type) produces warning

    public static final String CREATE_RESERVATION = "CREATE TABLE IF NOT EXISTS Reservation(\n" +
            "    reservation_id INT,\n" +
            "    guest_id INT NOT NULL,\n" +
            "    room_no INT,\n" +
            "    building_no VARCHAR(50),\n" +
            "    check_in_date LONG NOT NULL,\n" +
            "    check_out_date LONG NOT NULL,\n" +
            "    PRIMARY KEY (reservation_id),\n" +
            "    FOREIGN KEY (guest_id) REFERENCES Guests(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (room_no,building_no) REFERENCES Room(room_no, building_no)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (reservation_id > 0 AND guest_id > 0 AND check_in_date < check_out_date AND room_no > 0 AND  LENGTH(building_no) > 0) ) ENGINE=InnoDB;";

    public static final String CREATE_COMMENT = "CREATE TABLE IF NOT EXISTS Comment(\n" +
            "    comment_id INT,\n" +
            "    reservation_id INT NOT NULL,\n" +
            "    text VARCHAR(5000) NOT NULL,\n" +
            "    date LONG NOT NULL,\n" +
            "    topic VARCHAR(50),\n" +
            "    PRIMARY KEY (comment_id),\n" +
            "    FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (comment_id > 0 AND reservation_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_QNA = "CREATE TABLE IF NOT EXISTS QnA(\n" +
            "    thread_id INT,\n" +
            "    guest_id INT NOT NULL,\n" +
            "    receptionist_id INT,\n" +
            "    question_text VARCHAR(5000) NOT NULL,\n" +
            "    question_date LONG NOT NULL,\n" +
            "    answer_text VARCHAR(5000),\n" +
            "    answer_date LONG,\n" +
            "    PRIMARY KEY (thread_id),\n" +
            "    FOREIGN KEY (guest_id) REFERENCES Guests(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (receptionist_id) REFERENCES Receptionist(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (thread_id > 0 AND guest_id > 0 AND receptionist_id > 0)) ENGINE=InnoDB;";

    /*
    ============================PLACE TABLE DEFINITIONS============================
     */

    public static final String CREATE_BUILDING = "CREATE TABLE IF NOT EXISTS Building(\n" +
            "    building_no VARCHAR(50),\n" +
            "    location_name VARCHAR(50) NOT NULL,\n" +
            "    name VARCHAR(50) NOT NULL,\n" +
            "    no_of_rooms INT NOT NULL,\n" +
            "    no_of_floors INT NOT NULL,\n" +
            "    area INT NOT NULL,\n" +
            "    type VARCHAR(50) NOT NULL,\n" +
            "    PRIMARY KEY (building_no),\n" +
            "    FOREIGN KEY (location_name) REFERENCES Location(name)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (no_of_rooms > 0 AND no_of_floors > 0 AND area > 0 AND  CHAR_LENGTH(building_no) > 0 AND CHAR_LENGTH(location_name) > 0)) ENGINE=InnoDB;";

    public static final String CREATE_LOCATION = "CREATE TABLE IF NOT EXISTS Location(\n" +
            "    name VARCHAR(50),\n" +
            "    opening LONG,\n" +
            "    closing LONG,\n" +
            "    min_age INT,\n" +
            "    PRIMARY KEY (name),\n" +
            "    CHECK ( CHAR_LENGTH(name) > 0 AND opening < closing AND min_age > 0)) ENGINE=InnoDB;";

    /*
    ============================EVENT TABLE DEFINITIONS============================
     */

    public static final String CREATE_EVENT = "CREATE TABLE IF NOT EXISTS Event(\n" +
            "    event_id INT,\n" +
            "    event_name VARCHAR(50) NOT NULL,\n" +
            "    location_name VARCHAR(50) NOT NULL,\n" +
            "    start_date LONG NOT NULL,\n" +
            "    end_date LONG NOT NULL,\n" +
            "    min_age INT,\n" +
            "    quota INT,\n" +
            "    description VARCHAR(3000),\n" +
            "    manager_id INT NOT NULL,\n" +
            "    PRIMARY KEY (event_id),\n" +
            "    FOREIGN KEY (location_name) REFERENCES Location(name)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (manager_id) REFERENCES Manager(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK((event_id > 0) AND (start_date < end_date))) ENGINE=InnoDB;";

    public static final String CREATE_GUEST_ACTIVITY = "CREATE TABLE IF NOT EXISTS Guest_Activity(\n" +
            "    event_id INT,\n" +
            "    price NUMERIC (18, 2) NOT NULL,\n" +
            "    PRIMARY KEY (event_id),\n" +
            "    FOREIGN KEY (event_id) REFERENCES Event(event_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (event_id > 0 AND price >= 0.0)) ENGINE=InnoDB;";

    public static final String CREATE_ACTIVITY = "CREATE TABLE IF NOT EXISTS Activity(\n" +
            "    event_id INT,\n" +
            "    PRIMARY KEY (event_id),\n" +
            "    FOREIGN KEY (event_id) REFERENCES Guest_Activity(event_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (event_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_GROUP_TOURS = "CREATE TABLE IF NOT EXISTS Group_Tours(\n" +
            "    event_id INT,\n" +
            "    organizer_name VARCHAR(50) NOT NULL,\n" +
            "    tour_vehicle VARCHAR(20),\n" +
            "    distance_to_cover INT NOT NULL,\n" +
            "    PRIMARY KEY (event_id),\n" +
            "    FOREIGN KEY (event_id) REFERENCES Guest_Activity(event_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (event_id > 0 AND distance_to_cover > 0)) ENGINE=InnoDB;";

    public static final String CREATE_TRAINING_PROGRAM = "CREATE TABLE IF NOT EXISTS Training_Program(\n" +
            "    event_id INT,\n" +
            "    PRIMARY KEY (event_id),\n" +
            "    FOREIGN KEY (event_id) REFERENCES Event(event_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (event_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_TICKET = "CREATE TABLE IF NOT EXISTS Ticket(\n" +
            "    ticket_id INT,\n" +
            "    event_id INT NOT NULL,\n" +
            "    guest_id INT NOT NULL,\n" +
            "    PRIMARY KEY(ticket_id),\n" +
            "    FOREIGN KEY (event_id) REFERENCES Guest_Activity(event_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (guest_id) REFERENCES Guests(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (ticket_id > 0 AND event_id > 0 AND guest_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_TOURIST_ATTRACTION = "CREATE TABLE IF NOT EXISTS Tourist_Attraction(\n" +
            "    name VARCHAR(50),\n" +
            "    visits_per_day INT NOT NULL,\n" +
            "    PRIMARY KEY (name),\n" +
            "    CHECK (visits_per_day >= 0)) ENGINE=InnoDB;";

    /*
    ============================FOOD TABLE DEFINITIONS============================
     */

    public static final String CREATE_FOOD_DRINK = "CREATE TABLE IF NOT EXISTS Food_Drink(\n" +
            "    food_id INT,\n" +
            "    name VARCHAR(50) NOT NULL,\n" +
            "    type VARCHAR(20),\n" +
            "    no_in_stock INT NOT NULL,\n" +
            "    price NUMERIC(18, 2) NOT NULL,\n" +
            "    calorie INT,\n" +
            "    restaurant_id INT NOT NULL,\n" +
            "    PRIMARY KEY (food_id),\n" +
            "    FOREIGN KEY (restaurant_id) REFERENCES Restaurant(restaurant_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (food_id > 0 AND no_in_stock >= 0 AND price >= 0 AND restaurant_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_FOOD_ORDER = "CREATE TABLE IF NOT EXISTS Food_Order(\n" +
            "    order_id INT,\n" +
            "    rating INT,\n" +
            "    comment VARCHAR(1000),\n" +
            "    guest_id INT NOT NULL,\n" +
            "    manager_id INT,\n" +
            "    housekeeper_id INT,\n" +
            "    assign_time LONG NOT NULL,\n" +
            "    delivery_time LONG,\n" +
            "    status VARCHAR(20),\n" +
            "    PRIMARY KEY (order_id),\n" +
            "    FOREIGN KEY (guest_id) REFERENCES Guests(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (manager_id) REFERENCES Manager(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (housekeeper_id) REFERENCES Housekeeper(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (order_id > 0 AND guest_id > 0 AND assign_time <= UNIX_TIMESTAMP())) ENGINE=InnoDB;";

    public static final String CREATE_RESTAURANT = "CREATE TABLE IF NOT EXISTS Restaurant(\n" +
            "    restaurant_id INT,\n" +
            "    location_name VARCHAR(50) NOT NULL,\n" +
            "    name VARCHAR(50) NOT NULL,\n" +
            "    type VARCHAR(20),\n" +
            "    hours_opening LONG,\n" +
            "    hours_closing LONG,\n" +
            "    PRIMARY KEY (restaurant_id),\n" +
            "    FOREIGN KEY (location_name) REFERENCES Location(name)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (restaurant_id > 0 AND CHAR_LENGTH(location_name) > 0)) ENGINE=InnoDB;";

    /*
    ============================EMPLOYEE STUFF TABLE DEFINITIONS============================
     */

    public static final String CREATE_JOB_APPLICATION = "CREATE TABLE IF NOT EXISTS Job_Application (\n" +
            "    id INT,\n" +
            "    position VARCHAR(50),\n" +
            "    status VARCHAR(20) NOT NULL,\n" +
            "    PRIMARY KEY (id, position),\n" +
            "    FOREIGN KEY (id) REFERENCES Candidate(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_SECURITY_REPORT = "CREATE TABLE IF NOT EXISTS Security_Report(\n" +
            "    report_id INT,\n" +
            "    message VARCHAR(1000) NOT NULL,\n" +
            "    date LONG NOT NULL,\n" +
            "    security_staff_id INT NOT NULL,\n" +
            "    PRIMARY KEY (report_id),\n" +
            "    FOREIGN KEY (security_staff_id) REFERENCES Security_Staff(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (report_id > 0 AND security_staff_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_LEAVE_REQUEST_FORM = "CREATE TABLE IF NOT EXISTS Leave_Request_Form(\n" +
            "    id INT,\n" +
            "    leave_date LONG,\n" +
            "    days INT NOT NULL,\n" +
            "    manager_id INT,\n" +
            "    approve_date LONG,\n" +
            "    status VARCHAR(20) NOT NULL,\n" +
            "    PRIMARY KEY (id, leave_date(13)),\n" +
            "    FOREIGN KEY (id) REFERENCES Employee(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (manager_id) REFERENCES Manager(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (leave_date >= UNIX_TIMESTAMP() AND id > 0 AND days > 0)) ENGINE=InnoDB;";

    /*
    ============================RELATIONSHIP TABLE DEFINITIONS============================
     */

    public static final String CREATE_ORDER_CONTAINS = "CREATE TABLE IF NOT EXISTS Order_Contains(\n" +
            "    order_id INT,\n" +
            "    food_id INT,\n" +
            "    PRIMARY KEY (order_id, food_id),\n" +
            "    FOREIGN KEY (order_id) REFERENCES Food_Order(order_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (food_id) REFERENCES Food_Drink(food_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (order_id > 0 AND food_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_PLACES_TO_VISIT = "CREATE TABLE IF NOT EXISTS Places_To_Visit(\n" +
            "    event_id INT,\n" +
            "    attraction_name VARCHAR(50),\n" +
            "    PRIMARY KEY (event_id, attraction_name),\n" +
            "    FOREIGN KEY (event_id) REFERENCES Group_Tours(event_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (attraction_name) REFERENCES Tourist_Attraction(name)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (event_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_SEC_STAFF_APPLIES_TO = "CREATE TABLE IF NOT EXISTS Sec_Staff_Applies_To(\n" +
            "    sec_staff_id INT,\n" +
            "    training_program_id INT,\n" +
            "    PRIMARY KEY (sec_staff_id, training_program_id),\n" +
            "    FOREIGN KEY (training_program_id) REFERENCES Training_Program(event_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (sec_staff_id) REFERENCES Security_Staff(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (training_program_id > 0 AND sec_staff_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_HK_APPLIES_TO = "CREATE TABLE IF NOT EXISTS HK_Applies_To(\n" +
            "    housekeeper_id INT,\n" +
            "    training_program_id INT,\n" +
            "    PRIMARY KEY (housekeeper_id, training_program_id),\n" +
            "    FOREIGN KEY (training_program_id) REFERENCES Training_Program(event_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (housekeeper_id) REFERENCES Housekeeper(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (training_program_id > 0 AND housekeeper_id> 0)) ENGINE=InnoDB;";

    public static final String CREATE_EVALUATES_SEC_STAFF_APPLICATION = "CREATE TABLE IF NOT EXISTS Evaluates_Sec_Staff_Application(\n" +
            "    sec_staff_id INT,\n" +
            "    training_program_id INT,\n" +
            "    manager_id INT,\n" +
            "    application_status VARCHAR(50),\n" +
            "    PRIMARY KEY (sec_staff_id, training_program_id),\n" +
            "    FOREIGN KEY (sec_staff_id, training_program_id) REFERENCES Sec_Staff_Applies_To (sec_staff_id, training_program_id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (manager_id) REFERENCES Manager(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (training_program_id > 0 AND sec_staff_id > 0 AND manager_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_EVALUATES_HK_APPLICATION = "CREATE TABLE IF NOT EXISTS Evaluates_HK_Application(\n" +
            "    housekeeper_id INT,\n" +
            "    training_program_id INT,\n" +
            "    manager_id INT,\n" +
            "    application_status VARCHAR(50),\n" +
            "    PRIMARY KEY (housekeeper_id, training_program_id),\n" +
            "    FOREIGN KEY (housekeeper_id, training_program_id) REFERENCES HK_Applies_To(housekeeper_id, training_program_id)\n" +
            "        ON DELETE CASCADE \n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (manager_id) REFERENCES Manager(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (housekeeper_id > 0 AND training_program_id > 0 AND manager_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_APPROVES = "CREATE TABLE IF NOT EXISTS Approves(\n" +
            "    candidate_id INT,\n" +
            "    position VARCHAR(50),\n" +
            "    recruiter_id INT,\n" +
            "    PRIMARY KEY (candidate_id, position),\n" +
            "    FOREIGN KEY (candidate_id, position) REFERENCES Job_Application(id, position)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (recruiter_id) REFERENCES Recruiter(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (candidate_id > 0 AND recruiter_id > 0)) ENGINE=InnoDB;";

    public static final String CREATE_SECURITY_WALK = "CREATE TABLE IF NOT EXISTS Security_Walk(\n" +
            "    manager_id INT,\n" +
            "    security_staff_id INT,\n" +
            "    building_no VARCHAR(50),\n" +
            "    start_date LONG,\n" +
            "    end_date LONG,\n" +
            "    PRIMARY KEY (manager_id, security_staff_id, building_no, start_date(13), end_date(13)),\n" +
            "    FOREIGN KEY (manager_id) REFERENCES Manager(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (security_staff_id) REFERENCES Security_Staff(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (building_no) REFERENCES Building(building_no)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (manager_id > 0 AND security_staff_id > 0 AND CHAR_LENGTH(building_no) > 0)) ENGINE=InnoDB;";

    public static final String CREATE_CLEANING_DUTY = "CREATE TABLE IF NOT EXISTS Cleaning_Duty(\n" +
            "    housekeeper_id INT,\n" +
            "    room_no INT,\n" +
            "    building_no VARCHAR(50),\n" +
            "    date LONG,\n" +
            "    status VARCHAR(20) NOT NULL,\n" +
            "    manager_id INT NOT NULL,\n" +
            "    PRIMARY KEY (housekeeper_id, room_no, building_no, date(13)),\n" +
            "    FOREIGN KEY (housekeeper_id) REFERENCES Housekeeper(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (room_no, building_no) REFERENCES Room(room_no, building_no)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    FOREIGN KEY (manager_id) REFERENCES Manager(id)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CHECK (housekeeper_id > 0 AND room_no > 0 AND CHAR_LENGTH(building_no) > 0 AND manager_id > 0 AND UNIX_TIMESTAMP() <= date)) ENGINE=InnoDB;";
}