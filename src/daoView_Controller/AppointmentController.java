package daoView_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {
    public Label appointmentLabel;
    public TextField appointmentIDTextField;
    public TextField titleTextField;
    public TextField locationTextField;
    public ComboBox contactComboBox;
    public TextField descriptionTextField;
    public ComboBox typeComboBox;
    public DatePicker startDateDatePicker;
    public ComboBox customerIDComboBox;
    public ComboBox userIDComboBox;
    public ComboBox startTimeComboBox;
    public DatePicker endDateDatePicker;
    public ComboBox endTimeComboBox;
    public Button saveButton;
    public Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionSave(ActionEvent actionEvent) {
        System.out.println("Appointment save button clicked");
    }

    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        // System.out.println("Appointment cancel button clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}
