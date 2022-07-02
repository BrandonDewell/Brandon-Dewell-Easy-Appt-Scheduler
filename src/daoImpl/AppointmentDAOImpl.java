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

public class AppointmentDAOImpl implements IAppointmentDAO {  // write sql interactions and observable lists here

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
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();  // use localdatetime instead of timestamp?  Timestamp outputs as 2020-05-29 12:00:00.0 so date and time are both represented in a timestamp.
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                /*LocalDateTime sLDT = start.toLocalDateTime();
                LocalDateTime eLDT = end.toLocalDateTime();*/
                Appointment a = new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);


                //System.out.println("Timestamp start = " + start);   // Timestamp outputs as 2020-05-29 12:00:00.0 so date and time are both represented in a timestamp.
                allAppointmentsOL.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAppointmentsOL;
    }


    public ObservableList<String> getAllAppointmentTypesOL() {
        ObservableList<String> allAppointmentTypesOL = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Distinct type FROM client_schedule.appointments";

                    /*"SELECT Appointment_ID, Title, Appointments.Customer_ID, Customers.Customer_Name, Appointments.User_ID, Users.User_Name, Contacts.Contact_Name, Description, Location, Appointments.Contact_ID, Type, Start, End " +
                            "FROM Appointments, Customers, Users, Contacts " +
                            "WHERE Appointments.Customer_ID = Customers.Customer_ID AND Appointments.Contact_ID = Contacts.Contact_ID AND Appointments.User_ID = Users.User_ID";*/

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String type = rs.getString("Type");



                //System.out.println("Timestamp start = " + start);   // Timestamp outputs as 2020-05-29 12:00:00.0 so date and time are both represented in a timestamp.
                allAppointmentTypesOL.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAppointmentTypesOL;

       /* try {
            String sql = "SELECT * FROM CONTACTS";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contact c = new Contact(contactID, contactName, email);
                contactsOL.add(c);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return contactsOL;*/

    }


    public ObservableList<Appointment> selectWeekViewOL() throws SQLException {

        int currentDay = LocalDate.now().getDayOfWeek().getValue();
        ObservableList<Appointment> weekApptsOL = FXCollections.observableArrayList();

        //String sql = "SELECT * FROM APPOINTMENTS WHERE MONTH(Start) = ?";
        //String sql = "SELECT Appointment_ID, Title, Appointments.Customer_ID, Customers.Customer_Name, Appointments.User_ID, Users.User_Name, Contacts.Contact_Name, Description, Location, Appointments.Contact_ID, Type, Start, End FROM Appointments, Customers, Users, Contacts " +
                //"WHERE Appointments.Customer_ID = Customers.Customer_ID AND Appointments.Contact_ID = Contacts.Contact_ID AND Appointments.User_ID = Users.User_ID AND MONTH(Start) = ?";

        String sql = "SELECT Appointment_ID, Title, Appointments.Customer_ID, Customers.Customer_Name, Appointments.User_ID, Users.User_Name, Contacts.Contact_Name, Description, Location, Appointments.Contact_ID, Type, Start, End " +
                     "FROM Appointments, Customers, Users, Contacts " +
                     "WHERE Appointments.Customer_ID = Customers.Customer_ID AND Appointments.Contact_ID = Contacts.Contact_ID AND Appointments.User_ID = Users.User_ID AND YEARWEEK (start, 1) = YEARWEEK (CURDATE(), 1)";
        // This sql statement is using syntax manipulation of SQL vs a different approach of using syntax manipulation of Java.  If I were to do this with Java, I would use the WeekFields Class.
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
       // ps.setInt(1, currentDay);
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
                /*LocalDateTime sLDT = start.toLocalDateTime();
                LocalDateTime eLDT = end.toLocalDateTime();*/
            Appointment a = new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);
            weekApptsOL.add(a);
        }

        return weekApptsOL;

    }

    /*public void selectMonthViewOL(Timestamp start, Timestamp end) throws SQLException {

        String sql = "SELECT * FROM APPOINTMENTS WHERE Start BETWEEN ?, ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setTimestamp(1, start);
        ps.setTimestamp(2, end);
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
            start = rs.getTimestamp("Start");  // use localdatetime instead of timestamp?
            end = rs.getTimestamp("End");
            LocalDateTime sLDT = start.toLocalDateTime();
            LocalDateTime eLDT = end.toLocalDateTime();
            Appointment a = new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);
        }
       // adao.getAllAppointmentsOL();
    }*/

    public ObservableList<Appointment> selectMonthViewOL() throws SQLException {

       // int currentMonth = LocalDate.now().getMonth().getValue();
        ObservableList<Appointment> monthApptsOL = FXCollections.observableArrayList();

        String sql = "SELECT Appointment_ID, Title, Appointments.Customer_ID, Customers.Customer_Name, Appointments.User_ID, Users.User_Name, Contacts.Contact_Name, Description, Location, Appointments.Contact_ID, Type, Start, End " +
                     "FROM Appointments, Customers, Users, Contacts " +
                     "WHERE Appointments.Customer_ID = Customers.Customer_ID AND Appointments.Contact_ID = Contacts.Contact_ID AND Appointments.User_ID = Users.User_ID";

        //String sql = "SELECT Appointment_ID, Title, Appointments.Customer_ID, Customers.Customer_Name, Appointments.User_ID, Users.User_Name, Contacts.Contact_Name, Description, Location, Appointments.Contact_ID, Type, Start, End FROM Appointments, Customers, Users, Contacts " +
        //        "WHERE Appointments.Customer_ID = Customers.Customer_ID AND Appointments.Contact_ID = Contacts.Contact_ID AND Appointments.User_ID = Users.User_ID AND YEARMONTH(start, 1) = YEARMONTH (CURDATE(), 1)";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        //ps.setInt(1, currentMonth);
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
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();  // use localdatetime instead of timestamp?  Timestamp outputs as 2020-05-29 12:00:00.0 so date and time are both represented in a timestamp.
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                /*LocalDateTime sLDT = start.toLocalDateTime();
                LocalDateTime eLDT = end.toLocalDateTime();*/


            if(Utility.isCurrentMonth(start.toLocalDate()) == true){    // "start.toLocalDateTime().toLocalDate()" is what you would call doing a "cascading method call" or somthing similar.  - from meeting with Juan 6/24 at 9:15am.
                Appointment a = new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);
                monthApptsOL.add(a);
            }
        }

        return monthApptsOL;
    }

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
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();  // use localdatetime instead of timestamp?  Timestamp outputs as 2020-05-29 12:00:00.0 so date and time are both represented in a timestamp.
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                /*LocalDateTime sLDT = start.toLocalDateTime();
                LocalDateTime eLDT = end.toLocalDateTime();*/
            Appointment a = new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);
        }
    }

    public static int numberByMonthAndType(String month, String type){  // This is static because my goal is to not create an instance.
        // I only want to return a number.
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

    public int insert(Appointment appointment) {
        try {
            String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Division_ID = ?, Postal_Code = ?, Phone = ? WHERE Customer_ID = ?

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

    public int insert(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        try {
            String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";  // have to put in the first NULL to let mysql handle inputting
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



    @Override
    public void select(Appointment appointment) {

    }

    public void select() throws SQLException {

        /*if (onActionWeekView.isS){

        }
        String sql = "SELECT * FROM APPOINTMENTS WHERE Start = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            rs.getInt(1, AppointmentID);

        ps.setString(2, Type);
        int rowsAffected = ps.executeQuery();
        System.out.println("The number of rows affected from the update() call is " + rowsAffected);
        return rowsAffected;
        }*/
    }

   /* public void selectWeekViewOL(Timestamp start, Timestamp end) throws SQLException {

        String sql = "SELECT * FROM APPOINTMENTS WHERE Start = ?, End = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setTimestamp(1, start);
        ps.setTimestamp(2, end);
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
            start = rs.getTimestamp("Start");  // use localdatetime instead of timestamp?
            end = rs.getTimestamp("End");
            LocalDateTime sLDT = start.toLocalDateTime();
            LocalDateTime eLDT = end.toLocalDateTime();
            Appointment a = new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);
        }

    }*/

    @Override
    public int update(Appointment appointment) {
        return 0;
    }

    public static int update(int AppointmentID, String Type) throws SQLException {  // this function updates the appointment type
        String sql = "UPDATE APPOINTMENTS " +
                "SET Type = ? " +
                "WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, AppointmentID);
        ps.setString(2, Type);
        int rowsAffected = ps.executeUpdate();
        System.out.println("The number of rows affected from the update() call is " + rowsAffected);
        return rowsAffected;
    }

    public int update(int apptId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        try {
            String sql = "UPDATE APPOINTMENTS " +
                    "SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                    "WHERE Appointment_ID = ?";  // have to put in the first NULL to let mysql handle inputting
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



    @Override
    public int delete(Appointment appointment) {

        try {
            String sql = "DELETE FROM APPOINTMENTS " +
                         "WHERE Appointment_ID = ?";
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

    public int delete(int AppointmentID) {

        try {
            String sql = "DELETE FROM APPOINTMENTS " +
                         "WHERE Appointment_ID = ?";
            PreparedStatement ps = null;
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, AppointmentID);
            int rowsAffected = ps.executeUpdate();
            System.out.println("The number of rows affected from the delete() call is " + rowsAffected  +  "  -- public int delete(int " +
                    "AppointmentID) in AppointmentDAOImpl.java *******************************");
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteCustAppts(int CustomerID) {

        try {
            String sql = "DELETE FROM APPOINTMENTS " +
                         "WHERE Customer_ID = ?";
            PreparedStatement ps = null;
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, CustomerID);
            int rowsAffected = ps.executeUpdate();
            System.out.println("The number of rows affected from the delete() call is " + rowsAffected  +  "  -- public int deleteCustAppts(int " +
                    "CustomerID) in AppointmentDAOImpl.java *******************************");
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
