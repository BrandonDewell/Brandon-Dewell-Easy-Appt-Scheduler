package daoView_Controller;

import daoImpl.CustomerDAOImpl;
import daoModel.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    public TextField customerIDTextField;
    public TextField customerNameTextField;
    public ComboBox countryComboBox;
    public TextField addressTextField;
    public ComboBox stateProvinceComboBox;
    public TextField postalCodeTextField;
    public TextField phoneNumberTextField;
    public Button saveButton;
    public Button cancelButton;
    public Label customerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    
    public void onActionSave(ActionEvent actionEvent) {
        ObservableList<Customer> cList = CustomerDAOImpl.getAllCustomers();
                for(Customer c : cList){
                    System.out.println("Customer ID : " + c.getCustomerId() + " Name : " + c.getCustomerName());
                }

    }

    public void onActionCancel(ActionEvent actionEvent) {
    }
}
