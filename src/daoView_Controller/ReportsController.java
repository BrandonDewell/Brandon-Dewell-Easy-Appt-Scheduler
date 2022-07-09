package daoView_Controller;

import daoImpl.AppointmentDAOImpl;
import daoImpl.ContactDAOImpl;
import daoModel.Appointment;
import daoModel.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class presents three reports in one window.  The reports are:  The total number of appointments by month and type, the
 schedule of appointments by contact, and a list of all contacts and their associated email addresses. */
public class ReportsController implements Initializable {

    public ComboBox<String> monthComboBox;
    public ComboBox<String> typeComboBox;
    public Button resultButton;
    public Label ResultLabel;

    public ComboBox<Contact> contactComboBox;
    public TableView<Appointment> ApptsByContactTable;
    public TableColumn<Appointment, Integer> ApptIDCol;
    public TableColumn<Appointment, String> TitleCol;
    public TableColumn<Appointment, String> TypeCol;
    public TableColumn<Appointment, String> DescCol;
    public TableColumn<Appointment, LocalDateTime> SDATCol;
    public TableColumn<Appointment, LocalDateTime> EDATCol;
    public TableColumn<Appointment, Integer> CustIDCol;

    public TableView<Contact> ContactNameEmailTable;
    public TableColumn<Contact, String> ContactNameCol;
    public TableColumn<Contact, String> EmailCol;

    public Button CancelButton;
    public ObservableList<String> monthOL = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    /** This initialize method is the first method to load in this class and it sets up the top report's combo boxes with their
     observable lists.  The middle report's combo box and table are initialized here.  The bottom table is filled in with its
     observable list.
     @param url The url.
     @param resourceBundle The resourceBundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AppointmentDAOImpl dao = new AppointmentDAOImpl();
        monthComboBox.setItems(monthOL);
        typeComboBox.setItems(dao.getAllAppointmentTypesOL());

        ContactDAOImpl contactDAO = new ContactDAOImpl();
        contactComboBox.setItems(contactDAO.getAllContactsOL());
        ApptsByContactTable.setItems(dao.getAllAppointmentsOL());
        ApptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        SDATCol.setCellValueFactory(new PropertyValueFactory<>("startString"));
        EDATCol.setCellValueFactory(new PropertyValueFactory<>("endString"));
        CustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        ApptsByContactTable.getSortOrder().addAll(ApptIDCol);

        ContactNameEmailTable.setItems(contactDAO.getAllContactsOL());
        ContactNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    /** This event handler method for the Month combo box has a drop down list of all the months to choose from.
     @param actionEvent An event from an action.
     */
    public void onActionMonth(ActionEvent actionEvent) {
    }

    /** This event handler method for the Type combo box has a drop down list of all the Types of appointments in the database
     to choose from.
     @param actionEvent An event from an action.
     */
    public void onActionType(ActionEvent actionEvent) {
    }

    /** This event handler method for the Click to get Result button ensures a selection is made for both combo boxes in this top report
     by popping up an alert with instructions if a user failed to make a selection in one or both of them and clicked the button.  When both
     have selections made and the button is clicked, the label to the right is changed to add a value.
     @param actionEvent An event from an action. */
    public void onActionResult(ActionEvent actionEvent) {

        String month = monthComboBox.getValue();
        String type = typeComboBox.getValue();
        if((month == null) || (type == null)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please make a selection from each box.");
            alert.setContentText("Please make a selection for each of the drop-down boxes in the top section.");
            alert.showAndWait();
            return;
        }
        int numb = AppointmentDAOImpl.numberByMonthAndType(month, type);
        ResultLabel.setText("The total = " + numb);

    }

    /** This event handler method for the Contact combo box has an observable list of contacts to make a selection from.  It utilizes a
     lambda expression to filter the list based on the contact ID selected from the combo box, and displays that filtered list in the table.
     @param actionEvent An event from an action.
     */
    public void onActionContact(ActionEvent actionEvent) {

        Contact c = contactComboBox.getValue();
        if(c == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Fields must not be left blank.\nA drop down selection must be made.");
            alert.setContentText("""
                    Please enter a valid value for each text field.
                    Title, Description, Location, and Type must use characters.
                    Please make a selection in the drop down boxes.
                    Select a date by clicking on the date chooser button.""");
            alert.showAndWait();
            return;
        }
        AppointmentDAOImpl adao = new AppointmentDAOImpl();
        ObservableList<Appointment> filteredAppointmentsOL = adao.getAllAppointmentsOL().filtered(a -> {
            if(a.getContactId() == c.getContactId()){
                return true;
            }
            return false;
        });

        ApptsByContactTable.setItems(filteredAppointmentsOL);
        ApptsByContactTable.getSortOrder().addAll(ApptIDCol);
    }

    /** This event handler method for the Cancel button applies an Alert pop up window and if the user clicks OK, loads the main menu.
     @param actionEvent An event from an action.
     @throws IOException  If an input or output exception occurred.
     */
    public void onActionCancel(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear any information you selected.  Would you like to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1500, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }
}