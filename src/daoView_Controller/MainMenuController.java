package daoView_Controller;

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
import java.util.ResourceBundle;
// test

public class MainMenuController implements Initializable {

    Scene scene;
    public TableView custTable;
    public TableColumn CRcustIDCol;
    public TableColumn CRcustNameCol;
    public TableColumn CRaddressCol;
    public TableColumn CRstateProvinceCol;
    public TableColumn CRpostalCodeCol;
    public TableColumn CRphoneNumCol;
    public TableView apptTable;
    public TableColumn SAapptIDCol;
    public TableColumn SAtitleCol;
    public TableColumn SAdescriptionCol;
    public TableColumn SAlocationCol;
    public TableColumn SAcontactCol;
    public TableColumn SAtypeCol;
    public TableColumn SAsDATCol;
    public TableColumn SAeDATCol;
    public TableColumn SAcustIDCol;
    public TableColumn SAuserIDCol;
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
        custTable.setItems(Customer.getAllCustomers());
        CRcustIDCol.setCellValueFactory(new PropertyValueFactory<>("cId"));
        CRcustNameCol.setCellValueFactory(new PropertyValueFactory<>("cName"));
        CRaddressCol.setCellValueFactory(new PropertyValueFactory<>("cAddr"));
        CRstateProvinceCol.setCellValueFactory(new PropertyValueFactory<>("cState/Prov"));
        CRpostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("cPostalCode"));
        CRphoneNumCol.setCellValueFactory(new PropertyValueFactory<>("cPhoneNum"));

        System.out.println("Main Menu is initialized");
    }

    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        // System.out.println("Add Customer button clicked");  // to test that the event handler is working
        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/Customer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionUpdateCustomer(ActionEvent actionEvent) {
        System.out.println("Update Customer button clicked");
    }

    public void onActionDeleteCustomer(ActionEvent actionEvent) {
        System.out.println("Delete Customer button clicked");
    }

    public void onActionAddAppointment(ActionEvent actionEvent) throws IOException {
        // System.out.println("Add Appointment button clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/Appointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionUpdateAppointment(ActionEvent actionEvent) {
        System.out.println("Update Appointment button clicked");
    }

    public void onActionDeleteAppointment(ActionEvent actionEvent) {
        System.out.println("Delete Appointment button clicked");
    }

    public void onActionWeekView(ActionEvent actionEvent) {
        System.out.println("Week radio button clicked");
    }

    public void onActionMonthView(ActionEvent actionEvent) {
        System.out.println("Month radio button clicked");
    }

    public void onActionAllView(ActionEvent actionEvent) {
        System.out.println("All radio button clicked");
    }
}
