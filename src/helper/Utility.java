package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;

/** This class provides methods to do things that don't quite fit into other classes but are useful static methods. */
public class Utility {

    /** This method compares data in the current year and month with the ld parameter.
     @param ld The LocalDate object parameter.
     @return Returns true or false.
     */
    public static boolean isCurrentMonth(LocalDate ld){

        YearMonth yearMonthCurrent = YearMonth.now();
        YearMonth yearMonthTest = YearMonth.of(ld.getYear(), ld.getMonth());

        return yearMonthCurrent.equals(yearMonthTest);  // compares data in each object and returns it.
    }

    /** This method creates an observable list of times for selection from the time drop down combo boxes in the add/update appointment window.
     @param osZId The zone id of the operating system of the user.
     @param businessZId The time zone Eastern Time of where the business is located.
     @param businessHourStart The opening time of the business.
     @param workHours The amount of hours the business is open.
     @return timeList Returns the observable list named timeList.
     */
    public static ObservableList<LocalTime> generateDynamicTimeList(ZoneId osZId, ZoneId businessZId, LocalTime businessHourStart, int workHours) {
        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
        ZonedDateTime businessZDT = ZonedDateTime.of(LocalDate.now(), businessHourStart, businessZId);  // today's date, business hour that it opens at, and eastern time.
        ZonedDateTime localZDT = ZonedDateTime.ofInstant(businessZDT.toInstant(), osZId);  // businessZDT.toInstant() gives the opening day and hour of the business converted to UTC, osZId is my system default.  The ofInstant() converts the business hours to
        // my system default and assigns it to the variable localZDT.
        // ofInstant accepts an instant (which is an offset of the businessZDT created via the .toInstant() method) and the system default ZoneId (osZId) as arguments.  ofInstant creates a ZonedDatetime object using an offset against UTC.
        int localStartingHour = localZDT.getHour();  // opening hour in my system default time zone.
        int totalHours = localStartingHour + workHours;  // total hours the office is open daily (8am ET to 10pm ET which is 14 total hour open.  there are 13 hours that are starting times.  so 8+13 = 21 in ET, and in PT it is open at 5am so 5+13 = 18.  London
        // is UTC +1 (British Standard Time BST) and starts at 2pm )
        int midnightOrGreater = 0;
        for (int i = localStartingHour; i <= totalHours; i++) {
            if (i < 24) {
                timeList.add(LocalTime.of(i, 0));
            }
            if (i > 23) {
                timeList.add(LocalTime.of(midnightOrGreater, 0));
                midnightOrGreater += 1;
            }
        }
        // for (LocalTime lt : timeList)  // This enhanced for loop is just for printing out the times so I can see them on the console output.  Thats why it is commented out here.
        //  System.out.println(lt + " in the Time Zone in " + osZId);

        return timeList;
    }


    // Here is another way of setting up the time list to adjust the available times for scheduling appointments based on the user's timezone:

    /** This method creates a timeList of available times for selection from the time drop down combo boxes in the add/update appointment window.
     @param osZId The zone id of the operating system of the user.
     @param businessZId The time zone Eastern Time of where the business is located.
     @param businessHourStart The opening time of the business.
     @param workQuarterHours The amount of 15 minute segments that the business is open.
     @return timeList Returns the observable list named timeList.
     */
    public static ObservableList<LocalTime> generateDynamicTimeListOL(ZoneId osZId, ZoneId businessZId, LocalTime businessHourStart, int workQuarterHours) {
        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
        ZonedDateTime businessZDT = ZonedDateTime.of(LocalDate.now(), businessHourStart, businessZId);  // today's date, business hour that it opens at, and eastern time.
        ZonedDateTime localZDT = businessZDT.withZoneSameInstant(osZId);

        /*for(int i = 0; i <= workHours; i++){          // this version uses whole hours only.
            timeList.add(localZDT.toLocalTime());
            localZDT = localZDT.plusHours(1);  // this has the built in functionality to work with days as it is coming from a zoneddatetime object.  This way doesn't need to worry about the if i< 24 and if i > 23 from above
        }
        return timeList;*/

        for(int i = 0; i <= workQuarterHours; i++){
            timeList.add(localZDT.toLocalTime());
            localZDT = localZDT.plusMinutes(15);  // this has the built in functionality to work with days as it is coming from a zoneddatetime object.  This way doesn't need to worry about the if i< 24 and if i > 23 from above
        }
        return timeList;
    }

    /*public static ObservableList<Month> getAllMonthsOL() {
        ObservableList<Month> allMonthsOL = FXCollections.observableArrayList();

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
                *//*LocalDateTime sLDT = start.toLocalDateTime();
                LocalDateTime eLDT = end.toLocalDateTime();*//*
                Appointment a = new Appointment(apptId, custId, userId, contactId, title, desc, loc, type, custName, userName, contactName, start, end);

                //System.out.println("Timestamp start = " + start);   // Timestamp outputs as 2020-05-29 12:00:00.0 so date and time are both represented in a timestamp.
                //allMonthsOL.add(a.);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allMonthsOL;
    }
    }*/
}
