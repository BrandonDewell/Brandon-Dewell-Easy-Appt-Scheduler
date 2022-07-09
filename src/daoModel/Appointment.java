package daoModel;

import java.time.LocalDateTime;

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
     @param start The start LocalDateTime to be constructed.
     */
    public Appointment(int apptId, LocalDateTime start) {
        this.apptId = apptId;
        this.start = start;
    }

    /**  This method gets the customer name.
     @return Returns the customer name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**  This method gets the user name.
     @return Returns the user name.
     */
    public String getUserName() {
        return userName;
    }

    /**  This method gets the contact name.
     @return Returns the contact name.
     */
    public String getContactName() {
        return contactName;
    }

    /** This method gets the appointment ID.
     @return Returns the appointment ID.
     */
    public int getApptId() {
        return apptId;
    }

    /** This method gets the title.
     @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /** This method gets the description.
     @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**  This method gets the location.
     @return Returns the location.
     */
    public String getLocation() {
        return location;
    }

    /**  This method gets the type.
     @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**  This method gets the start LocalDateTime.
     @return Returns the start LocalDateTime.
     */
    public LocalDateTime getStart() {
        return start; }

    /**  This method gets the end LocalDateTime.
     @return Returns the end LocalDateTime.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**  This method gets the customer ID.
     @return Returns the customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**  This method gets the user ID.
     @return Returns the user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**  This method gets the contact ID.
     @return Returns the contact ID.
     */
    public int getContactId() {
        return contactId;
    }

    /**  This method gets the start String.
     @return Returns the start LocalDate and start LocalTime in a string.
     */
    public String getStartString() {
        return start.toLocalDate().toString() + " " + start.toLocalTime().toString();
    }

    /**  This method gets the end String.
     @return Returns the end LocalDate and end LocalTime in a string.
     */
    public String getEndString() {
        return end.toLocalDate().toString() + " " + end.toLocalTime().toString();
    }

}