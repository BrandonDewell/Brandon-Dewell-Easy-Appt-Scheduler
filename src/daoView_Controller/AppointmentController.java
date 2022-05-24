package daoView_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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

    }

    public void onActionCancel(ActionEvent actionEvent) {

    }
}
