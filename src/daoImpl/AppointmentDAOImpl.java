package daoImpl;

import daoInt.IAppointmentDAO;
import daoModel.Appointment;
import daoModel.Customer;
import helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentDAOImpl implements IAppointmentDAO {
    /*public static int insert(Appointment appointment) throws SQLException {
        //String sql = "INSERT INTO APPOINTMENTS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";  // 3/15 12:13pm Question
        // marks are bind variables starting at index 1.
        String sql = "INSERT INTO APPOINTMENTS VALUES (NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?)";  // have to put in the first NULL to let mysql handle inputting
        // values for the Customer_ID.
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);  // anytime there is a preparedStatement() called in a method, you must throw a SQLException
        // in the header/signature of the method that calls the preparedStatement().
        ps.setString(1, appointment.getCustomerName());
        ps.setString(2, appointment.getAddress());
        ps.setString(3, appointment.getPostalCode());
        ps.setString(4, appointment.getPhone());
        ps.setInt(5, appointment.getDivisionId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }*/

    public static void select(Appointment appointment){
    }

    public static int update(int AppointmentID, String Type) throws SQLException {  // this function updates the customer name
        String sql = "UPDATE APPOINTMENTS SET Type = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, AppointmentID);
        ps.setString(2, Type);
        int rowsAffected = ps.executeUpdate();
        System.out.println("The number of rows affected from the update() call is " + rowsAffected);
        return rowsAffected;
    }

    public static int delete(int CustomerID) throws SQLException {  // TODO fix this for appts not customers
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, CustomerID);
        int rowsAffected = ps.executeUpdate();
        System.out.println("The number of rows affected from the delete() call is " + rowsAffected);
        return rowsAffected;
    }
}
