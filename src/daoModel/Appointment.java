package daoModel;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Appointment {
    private int apptId, customerId, userId, contactId;
    private String title, description, location, type, customerName, userName, contactName;
    private Timestamp start, end;

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

    public String getCustomerName() {
        return customerName;
    }

    public String getUserName() {
        return userName;
    }

    public String getContactName() {
        return contactName;
    }

    // methods
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