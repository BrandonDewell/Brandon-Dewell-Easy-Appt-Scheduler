package daoImpl;

import daoInt.IAppointmentDAO;
import daoModel.Appointment;
import daoModel.Customer;
import helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentDAOImpl implements IAppointmentDAO {
    public static int insert(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS VALUES (NULL, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?, ?, ?)";  // have to put in the first NULL to let mysql handle inputting
        // values for the Customer_ID.
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);  // anytime there is a preparedStatement() called in a method, you must throw a SQLException
        // in the header/signature of the method that calls the preparedStatement().
        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, appointment.getStart());
        ps.setTimestamp(6, appointment.getEnd());
        ps.setInt(11, appointment.getCustomerId());
        ps.setInt(12, appointment.getUserId());
        ps.setInt(13, appointment.getContactId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void select(Appointment appointment){
    }

    public static int update(int AppointmentID, String Type) throws SQLException {  // this function updates the appointment type
        String sql = "UPDATE APPOINTMENTS SET Type = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, AppointmentID);
        ps.setString(2, Type);
        int rowsAffected = ps.executeUpdate();
        System.out.println("The number of rows affected from the update() call is " + rowsAffected);
        return rowsAffected;
    }

    public static int delete(int AppointmentID) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, AppointmentID);
        int rowsAffected = ps.executeUpdate();
        System.out.println("The number of rows affected from the delete() call is " + rowsAffected);
        return rowsAffected;
    }
}
