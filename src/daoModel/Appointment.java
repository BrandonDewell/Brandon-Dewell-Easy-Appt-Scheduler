package daoModel;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** This class manipulates appointments. */
public class Appointment {

    private int apptId;
    private int customerId;
    private int userId;
    private int contactId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String customerName;
    private String userName;
    private String contactName;
    private LocalDateTime start;
    private LocalDateTime end;

    /** This method is a constructor for Appointments.
     @param apptId The appointment ID to be constructed.
     @param customerId The customer ID to be constructed.
     @param userId The user ID to be constructed.
     @param contactId The contact ID to be constructed.
     @param title The title to be constructed.
     @param description The description to be constructed.
     @param location The location to be constructed.
     @param type The type to be constructed.
     @param customerName The customer name to be constructed.
     @param userName The user name to be constructed.
     @param contactName The contact name to be constructed.
     @param start The start LocalDateTime to be constructed.
     @param end The end LocalDateTime to be constructed.
     */

    public Appointment(int apptId, int customerId, int userId, int contactId, String title, String description, String location, String type, String customerName, String userName, String contactName, LocalDateTime start, LocalDateTime end) {
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

    /** This method is a constructor for Appointments.
     @param apptId The appointment ID to be constructed.
     @param title The title to be constructed.
     @param customerId The customer ID to be constructed.
     @param userId The user ID to be constructed.
     @param desc The description to be constructed.
     @param loc The location to be constructed.
     @param contactName The contact name to be constructed.
     @param type The type to be constructed.
     @param sDate The start LocalDate to be constructed.
     @param sTime The start LocalTime to be constructed.
     @param eDate The end LocalDate to be constructed.
     @param eTime The end LocalTime to be constructed.
     */
    public Appointment(int apptId, String title, int customerId, int userId, String desc, String loc, String contactName, String type, LocalDate sDate, LocalTime sTime, LocalDate eDate, LocalTime eTime) {
    }

    /** This method is a constructor for Appointments.
     @param apptId The appointment ID to be constructed.
     @param title The title to be constructed.
     @param customerId The customer ID to be constructed.
     @param userId The user ID to be constructed.
     @param desc The description to be constructed.
     @param loc The location to be constructed.
     @param contactName The contact name to be constructed.
     @param type The type to be constructed.
     @param sLDT The start LocalDateTime to be constructed.
     @param eLDT The end LocalDateTime to be constructed.
     */
    public Appointment(int apptId, String title, int customerId, int userId, String desc, String loc, String contactName, String type, LocalDateTime sLDT, LocalDateTime eLDT) {

    }

    /** This method is a constructor for Appointments.
     @param apptId The appointment ID to be constructed.
     @param custId The customer ID to be constructed.
     @param userId The user ID to be constructed.
     @param contactId The contact ID to be constructed.
     @param title The title to be constructed.
     @param desc The description to be constructed.
     @param loc The location to be constructed.
     @param type The type to be constructed.
     @param custName The customer name to be constructed.
     @param userName The user name to be constructed.
     @param contactName The contact name to be constructed.
     @param start The start Timestamp to be constructed.
     @param end The end Timestamp to be constructed.
     */
    public Appointment(int apptId, int custId, int userId, int contactId, String title, String desc, String loc, String type, String custName, String userName, String contactName, Timestamp start, Timestamp end) {
    }

    /** This method is a constructor for Appointments.
     @param apptId The appointment ID to be constructed.
     @param start The start LocalDateTime to be constructed.
     */
    public Appointment(int apptId, LocalDateTime start) {
        this.apptId = apptId;
        this.start = start;
    }

    /**
     @ return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     @ return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     @ return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     @ return the appointment ID
     */
    public int getApptId() {
        return apptId;
    }

    /**
     @ return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     @ return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     @ return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     @ return the type
     */
    public String getType() {
        return type;
    }

    /**
     @ return the start LocalDateTime
     */
    public LocalDateTime getStart() {
        return start; }

    /**
     @ return the end LocalDateTime
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     @ return the customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     @ return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     @ return the contact ID
     */
    public int getContactId() {
        return contactId;
    }

    /**
     @ return the start LocalDate and start LocalTime
     */
    public String getStartString() {
        return start.toLocalDate().toString() + " " + start.toLocalTime().toString();
    }

    /**
     @ return the end LocalDate and end LocalTime
     */
    public String getEndString() {
        return end.toLocalDate().toString() + " " + end.toLocalTime().toString();
    }

    /**
     @ return the type
     */
    @Override
    public String toString(){
        return (type);
    }

}