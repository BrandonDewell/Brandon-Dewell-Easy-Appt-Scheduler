package daoView_Controller;

import daoImpl.AppointmentDAOImpl;
import daoImpl.ContactDAOImpl;
import daoImpl.UserDAOImpl;
import daoModel.Contact;
import helper.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    public ComboBox<String> monthComboBox;
    public ComboBox<String> typeComboBox;
    public Label ResultLabel;

    public ComboBox<Contact> contactComboBox;
    public TableView ApptsByContactTable;
    public TableColumn ApptIDCol;
    public TableColumn TitleCol;
    public TableColumn TypeCol;
    public TableColumn DescCol;
    public TableColumn SDATCol;
    public TableColumn EDATCol;
    public TableColumn CustIDCol;

    public TableView ContactNameEmailTable;
    public TableColumn ContactNameCol;
    public TableColumn EmailCol;

    public Button CancelButton;
    public ObservableList<String> MonthList = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports is initialized  -- initialize(URL url, ResourceBundle resourceBundle) called from ReportsController.java");

        AppointmentDAOImpl dao = new AppointmentDAOImpl();
        typeComboBox.setItems(dao.getAllAppointmentTypesOL());

        ContactDAOImpl contactDAO = new ContactDAOImpl();
        contactComboBox.setItems(contactDAO.getAllContactsOL());

        monthComboBox.setItems(MonthList);


    }

    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        System.out.println("Reports cancel button clicked");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear any information you selected.  Would you like to continue?");
        Optional<ButtonType> result = alert.showAndWait();                  // optional container we named result contains enumerations for button types.
        if (result.isPresent() && result.get() == ButtonType.OK) {            // isPresent returns a boolean if there is something inside the optional container.
            // therefore its a check on whether someone clicked a button.  result.get() checks to see what type of button is clicked, the ok button or cancel button.
            Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1500, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onActionMonth(ActionEvent actionEvent) {
        /*Utility u = new Utility();
        monthComboBox.setItems(u.getAllMonthsOL());*/

    }

    public void onActionType(ActionEvent actionEvent) {
    }

    public void onActionContact(ActionEvent actionEvent) {
    }
}
