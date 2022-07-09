package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** This class opens and closes the connection to the database. */
public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static String password = "Passw0rd!";
    public static Connection connection;

    /** This method opens the connection to allow SQL calls to the database. */
    public static void openConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
        }
        catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // need to get the connection
    /** This method gets the connection to the database.
     @return Returns a connection. */
    public static Connection getConnection(){   // 5/14/22 4:05pm about 11 mins into the Getting the DBConnection class project ready video.
        return connection;
    }

    /** This method closes the connection to the database. */
    public static void closeConnection() {
        try {
            connection.close();
        }
        catch(Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

}
