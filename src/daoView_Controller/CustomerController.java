package daoView_Controller;

import daoImpl.CountryDAOImpl;
import daoModel.Country;
import daoModel.Customer;
import daoModel.FirstLevelDivision;
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

public class CustomerController implements Initializable {

    public TextField customerIDTextField;
    public TextField customerNameTextField;
    public ComboBox<Country> countryComboBox;
    public TextField addressTextField;
    public ComboBox<FirstLevelDivision> stateProvinceComboBox;
    public TextField postalCodeTextField;
    public TextField phoneNumberTextField;
    public Button saveButton;
    public Button cancelButton;
    public Label customerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("  -- initialize(URL url, ResourceBundle resourceBundle) called from CustomerController.java");

        CountryDAOImpl countryDAO = new CountryDAOImpl();
        countryComboBox.setItems(countryDAO.getAllCountriesOL());
    }


    public void onActionSave(ActionEvent actionEvent) {
        System.out.println("Customer save button clicked.  -- onActionSave(ActionEvent actionEvent) called in CustomerController.java");
  // from meeting with Wanda on 6/6 at 8am: get all the info from the customer fields and save in an object. ************************
        //Customer customer = new Customer();
        try {

            String name = customerNameTextField.getText();
            Country country = countryComboBox.getValue();  // not sure if this is done right since its
            // using a combo box, and I'm not sure of the syntax to get the selection.
            String address = addressTextField.getText();
            FirstLevelDivision stateProv = stateProvinceComboBox.getValue();  // not sure if this is done right since its
            // using a combo box, and I'm not sure of the syntax to get the selection.
            String postCode = customerNameTextField.getText();
            String phone = phoneNumberTextField.getText();

            if(name.isBlank() || address.isBlank() || postCode.isBlank() || phone.isBlank()){  // how to do this with a combo box?
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Fields must not be left blank.\nDrop down selection must be made.");
                alert.setContentText("Please enter a valid value for each text field.\nCustomer Name, Address, Postal Code, and Phone " +
                        "Number must use characters.\nPlease make a selection in the drop down boxes.");
                alert.showAndWait();
                return;
            } else {
                Customer c = new Customer(name, address, postCode, phone, stateProv, country);

                Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1200, 700);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*ObservableList<Customer> cList = Customer.getAllCustomersOL();
                for(Customer c : cList){
                    System.out.println("Customer ID : " + c.getCustomerId() + " Name : " + c.getCustomerName());
                }*/

    }

    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        System.out.println("Customer cancel button clicked.  -- onActionCancel(ActionEvent actionEvent) called in CustomerController.java");

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

}
