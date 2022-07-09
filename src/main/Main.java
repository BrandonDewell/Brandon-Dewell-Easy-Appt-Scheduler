package main;

import daoInt.FISqlSelAll;
import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class is the main method. */
public class Main extends Application {

    /** This method creates a stage to set a scene.
        @param stage The container holding the scenes.
     */
    @Override
    public void start(Stage stage) throws Exception {

        ResourceBundle rb = ResourceBundle.getBundle("main/Natural", Locale.getDefault());

        FISqlSelAll sqlAll = s -> "SELECT * FROM " + s;
        sqlAll.getSqlAll("USERS");

            try{
                Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/LogInMenu.fxml"));
                stage.setTitle(rb.getString("LogIn"));
                stage.setScene(new Scene(root, 400, 200));
                stage.show();
            } catch (IOException ex){
                ex.printStackTrace();
            }
    }

    /** This main method connects and disconnects to and from the database. This loads upon execution of the program.
        @param args Creates and assigns values for the main program to execute.
     */
    public static void main(String[] args) {
     //   Locale.setDefault(new Locale("fr"));  // Uncomment and change this between "en" and "fr" to do testing with languages without having to restart the computer.

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

}
