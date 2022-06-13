package daoView_Controller;

import daoImpl.ContactDAOImpl;
import daoImpl.CustomerDAOImpl;
import daoImpl.FirstLevelDivisionDAOImpl;
import daoModel.Contact;
import daoModel.Country;
import daoModel.Customer;
import daoModel.User;
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
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {
    public Label appointmentLabel;
    public TextField appointmentIDTextField;
    public TextField titleTextField;
    public TextField locationTextField;
    public ComboBox<Contact> contactComboBox;
    public TextField descriptionTextField;
    public ComboBox<String> typeComboBox;
    public DatePicker startDateDatePicker;
    public ComboBox<Customer> customerIDComboBox;
    public ComboBox<User> userIDComboBox;
    public ComboBox<Time> startTimeComboBox;
    public DatePicker endDateDatePicker;
    public ComboBox<Time> endTimeComboBox;
    public Button saveButton;
    public Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Nothing being done here.  -- initialize(URL url, ResourceBundle resourceBundle) called from AppointmentController.java");
    }

    public void onActionSave(ActionEvent actionEvent) {
        System.out.println("Appointment save button clicked");
    }

    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        System.out.println("Appointment cancel button clicked");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear any information you entered.  Would you like to continue?");
        Optional<ButtonType> result = alert.showAndWait();                  // optional container we named result contains enumerations for button types.
        if(result.isPresent() && result.get() == ButtonType.OK){            // isPresent returns a boolean if there is something inside the optional container.
            // therefore its a check on whether someone clicked a button.  result.get() checks to see what type of button is clicked, the ok button or cancel button.
            Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1200, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onActionCustomerID(ActionEvent actionEvent) {
        Customer c = customerIDComboBox.getValue();
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        contactComboBox.setItems(contactDAO.getAllContactsOL());
    }
    /*public void onActionCountry(ActionEvent actionEvent) {
        Country c = countryComboBox.getValue();
        FirstLevelDivisionDAOImpl firstLevelDivisionDAO = new FirstLevelDivisionDAOImpl();
        stateProvinceComboBox.setItems(firstLevelDivisionDAO.getAllFirstLevelDivisionsOL(c.getCountryId()));
    }*/










}
