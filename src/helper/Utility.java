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

        return yearMonthCurrent.equals(yearMonthTest);
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
        ZonedDateTime businessZDT = ZonedDateTime.of(LocalDate.now(), businessHourStart, businessZId);
        ZonedDateTime localZDT = ZonedDateTime.ofInstant(businessZDT.toInstant(), osZId);
        int localStartingHour = localZDT.getHour();
        int totalHours = localStartingHour + workHours;
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
        return timeList;
    }

    /** This method creates a timeList of available times for selection from the time drop down combo boxes in the add/update appointment window.
     @param osZId The zone id of the operating system of the user.
     @param businessZId The time zone Eastern Time of where the business is located.
     @param businessHourStart The opening time of the business.
     @param workQuarterHours The amount of 15 minute segments that the business is open.
     @return timeList Returns the observable list named timeList.
     */
    public static ObservableList<LocalTime> generateDynamicTimeListOL(ZoneId osZId, ZoneId businessZId, LocalTime businessHourStart, int workQuarterHours) {
        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
        ZonedDateTime businessZDT = ZonedDateTime.of(LocalDate.now(), businessHourStart, businessZId);
        ZonedDateTime localZDT = businessZDT.withZoneSameInstant(osZId);

        for(int i = 0; i <= workQuarterHours; i++){
            timeList.add(localZDT.toLocalTime());
            localZDT = localZDT.plusMinutes(15);
        }
        return timeList;
    }
}
