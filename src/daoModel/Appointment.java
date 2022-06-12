package daoModel;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Appointment {
    private int apptId;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private int customerId;
    private int userId;
    private int contactId;

    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    // constructors
    public Appointment(int apptId, String title, String description, String location, String type, Timestamp start, Timestamp end, int customerId, int userId, int contactId) {
        this.apptId = apptId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    public Appointment() {
    }

    public ObservableList<Appointment> getAllAppointments(){  // TODO move this to the impl so the DAO is set up correctly.
        //ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM Appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String desc = rs.getString("Description");
                String loc = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");  // should be data type date and time instead of timestamp? no
                Timestamp end = rs.getTimestamp("End");     // should be data type date and time
                int custId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contId = rs.getInt("Contact_ID");


                Appointment a = new Appointment(apptId, title, desc, loc, type, start, end, custId, userId, contId);
                allAppointments.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAppointments;
    }

    // methods
    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /*public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }*/
}