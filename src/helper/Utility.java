package helper;

import java.time.LocalDate;
import java.time.YearMonth;

public class Utility {

    public static boolean isCurrentMonth(LocalDate ld){

        YearMonth yearMonthCurrent = YearMonth.now();
        YearMonth yearMonthTest = YearMonth.of(ld.getYear(), ld.getMonth());

        return yearMonthCurrent.equals(yearMonthTest);  // compares data in each object and returns it.

    }

}
