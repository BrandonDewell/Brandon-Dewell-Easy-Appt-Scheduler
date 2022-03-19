package main;

import helper.CustomersQuery;
import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LogIn.fxml"));
        stage.setTitle("Log In");
        stage.setScene(new Scene(root, 300, 200));
        stage.show();
    }

    public static void main(String[] args) {
        JDBC.openConnection();
        //Locale.setDefault(new Locale("fr"));  // To test for French language

        // TimeZone info from TimeZone webinar
        // System.out.println(ZoneId.systemDefault());  // Displays Time Zone in Java

        // ZoneId.getAvailableZoneIds().stream().sorted().forEach(System.out::println);  // Displays a list of all the available Time Zones supported by JDK 17.

        // ZoneId.getAvailableZoneIds().stream().filter(z->z.contains("America")).sorted().forEach(System.out::println);  // same as above but with a Time Zone filter for America.

        LocalDate myLD = LocalDate.of(2022, 03, 17);  // use a combo box to replace the things to the right of the equals sign so the info is inputted by the user.  we are adding this manually here instead.
        LocalTime myLT = LocalTime.of(22, 0);  // same comment as above.
        LocalDateTime myLDT = LocalDateTime.of(myLD, myLT);  // creates a LocalDateTime object
        ZoneId myZoneId = ZoneId.systemDefault();  // gives us our computer's ZoneId
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        System.out.println(myZDT);
        System.out.println(myZDT.toLocalDate());  // retrieves just the date portion of the ZDT object
        System.out.println(myZDT.toLocalTime());  // retrieves just the time portion of the ZDT object
        System.out.println(myZDT.toLocalDate().toString() + " " + myZDT.toLocalTime().toString());  // now a string instead of an object.  can use this in an INSERT statement to use in the mySQL DB.

        System.out.println("User time: " + myZDT);
        ZoneId utcZoneId = ZoneId.of("UTC");  // now begins the process of starting to convert from local time to UTC.
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);  // myZDT.toInstant() creates an instant object with the UTC ZoneId.
        System.out.println("User time to UTC: " + utcZDT);

        myZDT = ZonedDateTime.ofInstant(utcZDT.toInstant(), myZoneId);  // resets timezone zero AKA utc to local time AKA myZoneId.
        System.out.println("UTC to user time: " + myZDT);

       /* int rowsAffected = CustomersQuery.insert("Brandon Dewell","12941 Bartlett Drive", "46037", "317-696-8955", utcZDT, "Brandon Dewell", utcZDT, "Brandon Dewell"); // test insert function

        if(rowsAffected > 0){
            System.out.println("Insert successful!");
        }
        else{
            System.out.println("Insert failed!");
        }*/


        launch(args);
        JDBC.closeConnection();
    }

}
