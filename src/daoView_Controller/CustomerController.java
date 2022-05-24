package daoView_Controller;

import daoImpl.CustomerDAOImpl;
import daoModel.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
        System.out.println("Customer save button clicked");

        /* ObservableList<Customer> cList = CustomerDAOImpl.getAllCustomers();
                for(Customer c : cList){
                    System.out.println("Customer ID : " + c.getCustomerId() + " Name : " + c.getCustomerName());
                }*/

    }

    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        // System.out.println("Customer cancel button clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}
