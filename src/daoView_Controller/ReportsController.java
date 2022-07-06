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

public class ReportsController implements Initializable {

    public ComboBox<String> monthComboBox;
    public ComboBox<String> typeComboBox;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports is initialized  -- initialize(URL url, ResourceBundle resourceBundle) called from ReportsController.java");

        AppointmentDAOImpl dao = new AppointmentDAOImpl();
        monthComboBox.setItems(monthOL);
        typeComboBox.setItems(dao.getAllAppointmentTypesOL());
       // ResultLabel.setText(dao.g);

        ContactDAOImpl contactDAO = new ContactDAOImpl();
        contactComboBox.setItems(contactDAO.getAllContactsOL());
        ApptsByContactTable.setItems(dao.getAllAppointmentsOL());
        ApptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
       // SDATCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        SDATCol.setCellValueFactory(new PropertyValueFactory<>("startString"));
       // EDATCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        EDATCol.setCellValueFactory(new PropertyValueFactory<>("endString"));
        CustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        ApptsByContactTable.getSortOrder().addAll(ApptIDCol);


        ContactNameEmailTable.setItems(contactDAO.getAllContactsOL());
        ContactNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

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

        ObservableList<Appointment> filteredAppointmentsOL = FXCollections.observableArrayList();

        Contact c = contactComboBox.getValue();
        if(c == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Fields must not be left blank.\nA drop down selection must be made.");
            alert.setContentText("Please enter a valid value for each text field.\nTitle, Description, Location, and Type " +
                    "must use characters.\nPlease make a selection in the drop down boxes.\nSelect a date by clicking on the date chooser button.");
            alert.showAndWait();

            return;
        }
        AppointmentDAOImpl adao = new AppointmentDAOImpl();
        for(Appointment a : adao.getAllAppointmentsOL()){
            if(a.getContactId() == c.getContactId()){
                filteredAppointmentsOL.add(a);
            }
        }
        ApptsByContactTable.setItems(filteredAppointmentsOL);
        ApptsByContactTable.getSortOrder().addAll(ApptIDCol);


    }

    public void onActionResult(ActionEvent actionEvent) {

        String month = monthComboBox.getValue();
        String type = typeComboBox.getValue();
        if((month == null) || (type == null)){            // this section only applies if we MUST have both a month selection made and a type selection made.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please make a selection from each box.");
            alert.setContentText("Please make a selection for each of the drop-down boxes in the top section.");
            alert.showAndWait();
            return;
        }
        int numb = AppointmentDAOImpl.numberByMonthAndType(month, type);
        ResultLabel.setText("The total = " + Integer.toString(numb));

    }

    // SELECT monthname(start) FROM client_schedule.appointments;
    // select now() from dual;
}
