package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBC {  // abstract because I am not going to be instantiating any objects out of this class in order to connect to the DB.

    private static final String protocol = "jdbc";  // final because I do not want it extended or inherited from.
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // reference to the driver interface that is part of the connection
    private static final String userName = "sqlUser"; // Username for the mySQL database
    private static String password = "Passw0rd!"; // Password for the mySQL database
    public static Connection connection;  // Connection Interface

    public static void openConnection()  // try/catch block handling is required by the JDBC protocol
    {
        try {
            Class.forName(driver); // this locates the driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // creates a connection object reference
            System.out.println("Connection successful! -- openConnection() called from JDBC.java");
        }
        catch(SQLException e) {
            e.printStackTrace();  // 5/14/22 3:57pm from Getting the DBConnection class project ready video at about 10 minutes in.
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    // need to get the connection
    public static Connection getConnection(){   // 5/14/22 4:05pm about 11 mins into the Getting the DBConnection class project ready video.
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!  -- closeConnection() called from JDBC.java");
        }
        catch(Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

}
