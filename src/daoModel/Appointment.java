package daoModel;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    //private LocalDateTime sLDT, eLDT;

    // constructor
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

    public Appointment(int apptId, String title, int customerId, int userId, String desc, String loc, String contactName, String type, LocalDate sDate, LocalTime sTime, LocalDate eDate, LocalTime eTime) {
    }

    public Appointment(int apptId, String title, int customerId, int userId, String desc, String loc, String contactName, String type, LocalDateTime sLDT, LocalDateTime eLDT) {

    }

    public Appointment(int apptId, int custId, int userId, int contactId, String title, String desc, String loc, String type, String custName, String userName, String contactName, Timestamp start, Timestamp end) {
    }

    public Appointment(int apptId, LocalDateTime start) {
        this.apptId = apptId;
        this.start = start;
    }


    // methods
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

    public LocalDateTime getStart() { return start; }

    public LocalDateTime getEnd() {
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

    public String getStartString() {
        return start.toLocalDate().toString() + " " + start.toLocalTime().toString();
    }

    public String getEndString() {
        return end.toLocalDate().toString() + " " + end.toLocalTime().toString();
    }

    /*public LocalDateTime getsLDT() {
        return start;
    }

    public LocalDateTime geteLDT() {return end; }*/

    @Override
    public String toString(){
        return (type);
    }

}