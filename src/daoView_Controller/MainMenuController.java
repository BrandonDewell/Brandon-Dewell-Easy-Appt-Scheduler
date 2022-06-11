package daoView_Controller;

import daoImpl.CustomerDAOImpl;
import daoModel.Appointment;
import daoModel.Customer;
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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    Scene scene;
    public TableView<Customer> custTable;
    public TableColumn<Customer, Integer> CRcustIDCol;
    public TableColumn<Customer, String> CRcustNameCol;
    public TableColumn<Customer, String> CRaddressCol;
    public TableColumn<Customer, String> CRstateProvinceCol;
    public TableColumn<Customer, String> CRpostalCodeCol;
    public TableColumn<Customer, Integer> CRphoneNumCol;
    public TableView<Appointment> apptTable;
    public TableColumn<Appointment, Integer> SAapptIDCol;
    public TableColumn<Appointment, String> SAtitleCol;
    public TableColumn<Appointment, String> SAdescriptionCol;
    public TableColumn<Appointment, String> SAlocationCol;
    public TableColumn<Appointment, Integer> SAcontactCol;
    public TableColumn<Appointment, String> SAtypeCol;
    public TableColumn<Appointment, LocalDateTime> SAsDATCol;
    public TableColumn<Appointment, LocalDateTime> SAeDATCol;
    public TableColumn<Appointment, Integer> SAcustIDCol;
    public TableColumn<Appointment, Integer> SAuserIDCol;
    public Button addCustomer;
    public Button updateCustomer;
    public Button deleteCustomer;
    public Button addAppointment;
    public Button updateAppointment;
    public Button deleteAppointment;
    public RadioButton weekRB;
    public ToggleGroup timePeriod;
    public RadioButton monthRB;
    public RadioButton allRB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Main Menu is initialized  -- initialize(URL url, ResourceBundle resourceBundle) called from MainMenuController.java");

        CustomerDAOImpl dao = new CustomerDAOImpl();

        custTable.setItems(dao.getAllCustomersOL());
        CRcustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        CRcustNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        CRaddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        CRstateProvinceCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        CRpostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        CRphoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        Appointment a = new Appointment();
        apptTable.setItems(a.getAllAppointments());
        SAapptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        SAtitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        SAdescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        SAlocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        SAcontactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        SAtypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        SAsDATCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        SAeDATCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        SAcustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        SAuserIDCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Customer button clicked.  -- onActionAddCustomer(ActionEvent actionEvent) called in MainMenuController.java");  // to test that the event handler is working

        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/Customer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionUpdateCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Update Customer button clicked.  -- onActionUpdateCustomer(ActionEvent actionEvent) called in MainMenuController.java");

        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/Customer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Update Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionDeleteCustomer(ActionEvent actionEvent) throws SQLException {
        System.out.println("Delete Customer button clicked.  -- onActionDeleteCustomer(ActionEvent actionEvent) called in MainMenuController.java");

        Customer temp = custTable.getSelectionModel().getSelectedItem();
        if (temp == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No selection was made");
            alert.setContentText("Please select a customer from the top table to delete.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm you would like to delete the selected " +
                    "customer by clicking the OK button.");
            alert.setTitle("Deleting a Customer");
            alert.setHeaderText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                CustomerDAOImpl dao = new CustomerDAOImpl();
                dao.delete(custTable.getSelectionModel().getSelectedItem());
                custTable.setItems(dao.getAllCustomersOL());
            }
        }
    }


    public void onActionAddAppointment(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Appointment button clicked.  -- onActionAddAppointment(ActionEvent actionEvent) called in MainMenuController.java");

        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/Appointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionUpdateAppointment(ActionEvent actionEvent) throws IOException {
        System.out.println("Update Appointment button clicked.  -- onActionUpdateAppointment(ActionEvent actionEvent) called in MainMenuController.java");

        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/Appointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Update Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionDeleteAppointment(ActionEvent actionEvent) {
        System.out.println("Delete Appointment button clicked.  -- onActionDeleteAppointment(ActionEvent actionEvent) called in MainMenuController.java");
    }

    public void onActionWeekView(ActionEvent actionEvent) {
        System.out.println("Week radio button clicked.  -- onActionWeekView(ActionEvent actionEvent) called in MainMenuController.java");
    }

    public void onActionMonthView(ActionEvent actionEvent) {
        System.out.println("Month radio button clicked.  -- onActionMonthView(ActionEvent actionEvent) called in MainMenuController.java");
    }

    public void onActionAllView(ActionEvent actionEvent) {
        System.out.println("All radio button clicked.  -- onActionAllView(ActionEvent actionEvent) called in MainMenuController.java");
    }
}
