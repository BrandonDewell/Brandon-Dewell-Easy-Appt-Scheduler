package daoModel;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointment {
    private int apptId, customerId, userId, contactId;
    private String title, description, location, type, customerName, userName, contactName;
    private Timestamp start, end;
    private LocalDateTime sLDT, eLDT;

    // constructor
    public Appointment(int apptId, int customerId, int userId, int contactId, String title, String description, String location, String type, String customerName, String userName, String contactName, Timestamp start, Timestamp end) {
        this.apptId = apptId;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.customerName = customerName;
        this.userName = userName;
        this.contactName = contactName;
        this.start = start;
        this.end = end;
    }

    public Appointment(int apptId, String title, int customerId, int userId, String desc, String loc, String contactName, String type, LocalDate sDate, LocalTime sTime, LocalDate eDate, LocalTime eTime) {
    }



    public Appointment(int apptId, String title, int customerId, int userId, String desc, String loc, String contactName, String type, LocalDateTime sLDT, LocalDateTime eLDT) {

    }

    public Appointment(int apptId, int custId, int userId, int contactId, String title, String desc, String loc, String type, String custName, String userName, String contactName, LocalDateTime sLDT, LocalDateTime eLDT) {
    }

    // methods
    public LocalDateTime getsLDT() {
        return sLDT;
    }

    public LocalDateTime geteLDT() {
        return eLDT;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getUserName() {
        return userName;
    }

    public String getContactName() {
        return contactName;
    }

    public int getApptId() {
        return apptId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getUserId() {
        return userId;
    }

    public int getContactId() {
        return contactId;
    }

}