package daoImpl;

import daoInt.IAppointmentDAO;
import daoModel.Appointment;
import daoModel.Customer;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentDAOImpl implements IAppointmentDAO {  // write sql interactions and observable lists here

    @Override
    public ObservableList<Appointment> getAllAppointmentsOL() {

        ObservableList<Appointment> allAppointmentsOL = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Appointments.Customer_ID, Customers.Customer_Name, Appointments.User_ID, Users.User_Name, Contacts.Contact_Name, Description, Location, Appointments.Contact_ID, Type, Start, End FROM Appointments, Customers, Users, Contacts " +
                    "WHERE Appointments.Customer_ID = Customers.Customer_ID AND Appointments.Contact_ID = Contacts.Contact_ID AND Appointments.User_ID = Users.User_ID";

            // "SELECT Appointment_ID, Title, Appointments.Customer_ID, Appointments.User_ID, Description, Location, Appointments.Contact_ID, Type, Start, End FROM Appointments"

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                int custId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                String title = rs.getString("Title");
                String desc = rs.getString("Description");
                String loc = rs.getString("Location");
                String type = rs.getString("Type");
                String custName = rs.getString("Customer_Name");  // changed from customerId to customer_name
                String userName = rs.getString("User_Name");
                String contactName = rs.getString("Contact_Name");
                Timestamp start = rs.getTimestamp("Start");  // use localdatetime instead of timestamp?
                Timestamp end = rs.getTimestamp("End");
                LocalDateTime sLDT = start.toLocalDateTime();
                LocalDateTime eLDT = end.toLocalDateTime();
                Appointment a = new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);

                //Customer c = new Customer();
                allAppointmentsOL.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAppointmentsOL;
    }


    public int insert(Appointment appointment) {
        try {
            String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";  // have to put in the first NULL to let mysql handle inputting
            // values for the Customer_ID.

            // UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Division_ID = ?, Postal_Code = ?, Phone = ? WHERE Customer_ID = ?

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, appointment.getStart());
            ps.setTimestamp(6, appointment.getEnd());
            ps.setInt(7, appointment.getCustomerId());
            ps.setInt(8, appointment.getUserId());
            ps.setInt(9, appointment.getContactId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int insert(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        try {
            String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";  // have to put in the first NULL to let mysql handle inputting
            // values for the Customer_ID.
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, customerID);
            ps.setInt(8, userID);
            ps.setInt(9, contactID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void select(Appointment appointment) {
    }

    @Override
    public int update(Appointment appointment) {
        return 0;
    }

    @Override
    public int delete(Appointment appointment) {

        try {
            String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
            PreparedStatement ps = null;
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, appointment.getApptId());
            int rowsAffected = ps.executeUpdate();
            System.out.println("The number of rows affected from the delete() call is " + rowsAffected);
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
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


    public int delete(int AppointmentID) {

        try {
            String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
            PreparedStatement ps = null;
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, AppointmentID);
            int rowsAffected = ps.executeUpdate();
            System.out.println("The number of rows affected from the delete() call is " + rowsAffected  +  "  -- public int delete(int " +
                    "AppointmentID) in AppointmentDAOImpl.java");
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(int apptId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        try {
            String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";  // have to put in the first NULL to let mysql handle inputting
            // values for the Customer_ID.

            // "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Division_ID = ?, Postal_Code = ?, Phone = ? WHERE Customer_ID = ?"

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, customerID);
            ps.setInt(8, userID);
            ps.setInt(9, contactID);
            ps.setInt(10, apptId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
