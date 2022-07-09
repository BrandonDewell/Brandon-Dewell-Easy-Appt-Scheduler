package daoImpl;

import daoInt.IAppointmentDAO;
import daoModel.Appointment;
import helper.JDBC;
import helper.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/** This method implements an Interface and provides methods to do SQL calls to the database. */
public class AppointmentDAOImpl implements IAppointmentDAO {

/** This method gets an Observable List via a SQL call to the database.
    @return Returns the allAppointmentsOL observable list of Appointment objects.
 */
    @Override
    public ObservableList<Appointment> getAllAppointmentsOL() {

        ObservableList<Appointment> allAppointmentsOL = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Appointments.Customer_ID, Customers.Customer_Name, Appointments.User_ID, Users.User_Name, Contacts.Contact_Name, Description, Location, Appointments.Contact_ID, Type, Start, End " +
                         "FROM Appointments, Customers, Users, Contacts " +
                         "WHERE Appointments.Customer_ID = Customers.Customer_ID AND Appointments.Contact_ID = Contacts.Contact_ID AND Appointments.User_ID = Users.User_ID";

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
                String custName = rs.getString("Customer_Name");
                String userName = rs.getString("User_Name");
                String contactName = rs.getString("Contact_Name");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                Appointment a = new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);
                allAppointmentsOL.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAppointmentsOL;
    }

    /** This method gets an Observable List via a SQL call to the database.
     @return Returns the allAppointmentTypesOL observable list of string objects based on their type.
     */
    public ObservableList<String> getAllAppointmentTypesOL() {
        ObservableList<String> allAppointmentTypesOL = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Distinct type FROM client_schedule.appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("Type");
                allAppointmentTypesOL.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAppointmentTypesOL;
    }

    /** This method gets an Observable List via a SQL call to the database.
     @return Returns the weekApptsOL observable list of appointment objects based on the current week.
     @throws SQLException  If a database access exception occurred.
     */
    public ObservableList<Appointment> selectWeekViewOL() throws SQLException {
        ObservableList<Appointment> weekApptsOL = FXCollections.observableArrayList();

        String sql = "SELECT Appointment_ID, Title, Appointments.Customer_ID, Customers.Customer_Name, Appointments.User_ID, Users.User_Name, Contacts.Contact_Name, Description, Location, Appointments.Contact_ID, Type, Start, End " +
                     "FROM Appointments, Customers, Users, Contacts " +
                     "WHERE Appointments.Customer_ID = Customers.Customer_ID AND Appointments.Contact_ID = Contacts.Contact_ID AND Appointments.User_ID = Users.User_ID AND YEARWEEK (start, 0) = YEARWEEK (curDate(), 0)";
                   /*"SELECT Appointment_ID, Title, Appointments.Customer_ID, Customers.Customer_Name, Appointments.User_ID, Users.User_Name, Contacts.Contact_Name, Description, Location, Appointments.Contact_ID, Type, Start, End " +
                     "FROM Appointments INNER JOIN Customers ON Appointments.Customer_ID = Customers.Customer_ID INNER JOIN Users ON Appointments.User_ID = Users.User_ID INNER JOIN Contacts ON Appointments.Contact_ID = Contacts.Contact_ID " +
                     "WHERE YEARWEEK (start, 0) = YEARWEEK (curDate(), 0)";*/
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
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
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();  // use localdatetime instead of timestamp?  Timestamp outputs as 2020-05-29 12:00:00.0 so date and time are both represented in a timestamp.
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();

            Appointment a = new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);
            weekApptsOL.add(a);
        }
        return weekApptsOL;
    }

    /** This method gets an Observable List via a SQL call to the database.
     @return Returns the monthApptsOL observable list of appointment objects based on the current month.
     @throws SQLException  If a database access exception occurred.
     */
    public ObservableList<Appointment> selectMonthViewOL() throws SQLException {
        ObservableList<Appointment> monthApptsOL = FXCollections.observableArrayList();

        String sql = "SELECT Appointment_ID, Title, Appointments.Customer_ID, Customers.Customer_Name, Appointments.User_ID, Users.User_Name, Contacts.Contact_Name, Description, Location, Appointments.Contact_ID, Type, Start, End " +
                     "FROM Appointments, Customers, Users, Contacts " +
                     "WHERE Appointments.Customer_ID = Customers.Customer_ID AND Appointments.Contact_ID = Contacts.Contact_ID AND Appointments.User_ID = Users.User_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
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
            String custName = rs.getString("Customer_Name");
            String userName = rs.getString("User_Name");
            String contactName = rs.getString("Contact_Name");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();

            if(Utility.isCurrentMonth(start.toLocalDate())){
                Appointment a = new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);
                monthApptsOL.add(a);
            }
        }
        return monthApptsOL;
    }

    /** This method gets an Observable List via a SQL call to the database.
     * @throws SQLException  If a database access exception occurred.
     */
    public void selectAllView() throws SQLException {

        String sql = "SELECT * FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int apptId = rs.getInt("Appointment_ID");
            int custId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            String title = rs.getString("Title");
            String desc = rs.getString("Description");
            String loc = rs.getString("Location");
            String type = rs.getString("Type");
            String custName = rs.getString("Customer_Name");
            String userName = rs.getString("User_Name");
            String contactName = rs.getString("Contact_Name");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);
        }
    }

    /** This method gets an Observable List via a SQL call to the database.
     @param month The month inputted.
     @param type The type of appointment inputted.
     @return If information is found, it returns the count (an int) of the number of results otherwise returns -1.
     */
    public static int numberByMonthAndType(String month, String type){
        try {
            String sql = "SELECT COUNT(*) " +
                    "FROM client_schedule.appointments " +
                    "WHERE monthname(Start) = ? AND Type = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, month);
            ps.setString(2, type);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /** This method inserts an appointment via a SQL call to the database.
     @param appointment The appointment object to be inserted.
     @return Returns the rowsAffected as an int or the int 0.
     */
    public int insert(Appointment appointment) {
        try {
            String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
            ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
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

    /** This method inserts an appointment via a SQL call to the database.
     @param title The title to be inserted.
     @param description The description to be inserted.
     @param location The location to be inserted.
     @param type The type to be inserted.
     @param start The start LocalDateTime object to be inserted.
     @param end The end LocalDateTime object to be inserted.
     @param customerID The customerID to be inserted.
     @param userID The userID to be inserted.
     @param contactID The contactId to be inserted.
     @return Returns the rowsAffected as an int or the int 0.
     */
    public int insert(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        try {
            String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

    /** This method selects an appointment via a SQL call to the database.
     @param appointment The appointment object to be selected. */
    @Override
    public void select(Appointment appointment) {
    }

    /** This method selects an appointment.
     * @throws SQLException  If a database access exception occurred.
     */
     public void select() throws SQLException {
    }

    /** This method updates an appointment object via a SQL call to the database.
     @param appointment The appointment object to be updated.
     @return The int 0 returned.
     */
    @Override
    public int update(Appointment appointment) {
        return 0;
    }

    /** This method updates an appointment object via a SQL call to the database.
     @param AppointmentID The AppointmentID int to be updated.
     @param Type The Type string to be updated.
     @return rowsAffected The number of rows the SQL statment worked on and returned as an int.
     @throws SQLException  If a database access exception occurred.
     */
    public static int update(int AppointmentID, String Type) throws SQLException {
        String sql = "UPDATE APPOINTMENTS " +
                "SET Type = ? " +
                "WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, AppointmentID);
        ps.setString(2, Type);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method updates an appointment object via a SQL call to the database.
     @param apptId The apptId int to be updated.
     @param title The title to be inserted.
     @param description The description to be inserted.
     @param location The location to be inserted.
     @param type The type to be inserted.
     @param start The start LocalDateTime object to be inserted.
     @param end The end LocalDateTime object to be inserted.
     @param customerID The customerID to be inserted.
     @param userID The userID to be inserted.
     @param contactID The contactId to be inserted.
     @return Returns the rowsAffected as an int or the int 0.
     */
    public int update(int apptId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        try {
            String sql = "UPDATE APPOINTMENTS " +
                    "SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                    "WHERE Appointment_ID = ?";

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

    /** This method deletes an appointment object via a SQL call to the database.
     @param appointment The appointment object with a matching appointmentID to be deleted.
     @return Returns the rowsAffected as an int or the int 0. */
    @Override
    public int delete(Appointment appointment) {

        try {
            String sql = "DELETE FROM APPOINTMENTS " +
                         "WHERE Appointment_ID = ?";
            PreparedStatement ps = null;
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, appointment.getApptId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /** This method deletes an appointment object via a SQL call to the database.
     @param AppointmentID The AppointmentID of the appointment to be deleted.
     @return Returns the rowsAffected as an int or the int 0. */
    public int delete(int AppointmentID) {

        try {
            String sql = "DELETE FROM APPOINTMENTS " +
                         "WHERE Appointment_ID = ?";
            PreparedStatement ps = null;
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, AppointmentID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /** This method deletes an appointment object via a SQL call to the database.
     @param CustomerID The CustomerID of the appointment to be deleted.
     @return Returns the rowsAffected as an int or the int 0. */
    public int deleteCustAppts(int CustomerID) {

        try {
            String sql = "DELETE FROM APPOINTMENTS " +
                         "WHERE Customer_ID = ?";
            PreparedStatement ps = null;
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, CustomerID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /** This method provides upcoming information on whether there is an upcoming appointment in the next 15 minutes via a SQL call to the database.
     @return Returns the appointmentID and time if found, otherwise returns null.
     @throws SQLException  If a database access exception occurred.*/
    public static Appointment upcomingApptInfo() throws SQLException {

            String sql = "SELECT * " +
                    "FROM client_schedule.appointments " +
                    "WHERE Start between now() AND date_add(now(), interval 15 minute)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                LocalDateTime sLDT = rs.getTimestamp("Start").toLocalDateTime();
                if (sLDT.toLocalDate().equals(LocalDate.now())) {
                    return new Appointment(rs.getInt("Appointment_ID"), sLDT);
                } else {
                    return null;
                }
            }
        return null;
    }

}