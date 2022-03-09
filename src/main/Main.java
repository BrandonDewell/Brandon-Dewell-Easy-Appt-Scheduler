package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FirstScreen.fxml"));
        stage.setTitle("First Screen");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        JDBC.openConnection();  // 3/9/22 11:52am not sure where to put this in relation to the launch(args); line, but it should be in the main method.
        launch(args);
        JDBC.closeConnection();
    }
}
