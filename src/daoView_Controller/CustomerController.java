package daoView_Controller;

import daoImpl.CountryDAOImpl;
import daoImpl.CustomerDAOImpl;
import daoImpl.FirstLevelDivisionDAOImpl;
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

    private Customer selectedCustomer;

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
            Country country = countryComboBox.getValue();
            String address = addressTextField.getText();
            FirstLevelDivision stateProv = stateProvinceComboBox.getValue();
            String postCode = postalCodeTextField.getText();
            String phone = phoneNumberTextField.getText();

            if(name.isBlank() || address.isBlank() || postCode.isBlank() || phone.isBlank() || stateProv == null){  // combo boxes use
                // comboBox == null to do error checks instead of textfield.isBlank()
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Fields must not be left blank.\nDrop down selection must be made.");
                alert.setContentText("Please enter a valid value for each text field.\nCustomer Name, Address, Postal Code, and Phone " +
                        "Number must use characters.\nPlease make a selection in the drop down boxes.");
                alert.showAndWait();
            } else {
                if (selectedCustomer == null) {  // Add situation
                    Customer c = new Customer(0, name, address, postCode, phone, stateProv.getDivisionId(), 0, "", "");  // The customerId, countryId, division, and country parameters are not really important and
                    // I don't care about that info so I can "leave" them blank.
                    CustomerDAOImpl dao = new CustomerDAOImpl();
                    dao.insert(c);  // TODO check insert code
                } else{  // Update situation
                    Customer c = new Customer(selectedCustomer.getCustomerId(), name, address, postCode, phone, stateProv.getDivisionId(), 0, "", "");
                    CustomerDAOImpl dao = new CustomerDAOImpl();
                    dao.update(c);
                }

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

    public void onActionCountry(ActionEvent actionEvent) {

        Country c = countryComboBox.getValue();
        FirstLevelDivisionDAOImpl firstLevelDivisionDAO = new FirstLevelDivisionDAOImpl();
        stateProvinceComboBox.setItems(firstLevelDivisionDAO.getAllFirstLevelDivisionsOL(c.getCountryId()));

    }

    public void sendCustomer(Customer inCustomer) {
        selectedCustomer = inCustomer;

        customerIDTextField.setText(String.valueOf(selectedCustomer.getCustomerId()));
        customerNameTextField.setText(String.valueOf(selectedCustomer.getCustomerName()));
        addressTextField.setText(String.valueOf(selectedCustomer.getAddress()));
        postalCodeTextField.setText(String.valueOf(selectedCustomer.getPostalCode()));
        phoneNumberTextField.setText(String.valueOf(selectedCustomer.getPhone()));

        for(Country c : countryComboBox.getItems()){
            if (selectedCustomer.getCountryId() == c.getCountryId()){
                countryComboBox.setValue(c);
                break;
            }
        }

        FirstLevelDivisionDAOImpl firstLevelDivisionDAO = new FirstLevelDivisionDAOImpl();
        stateProvinceComboBox.setItems(firstLevelDivisionDAO.getAllFirstLevelDivisionsOL(selectedCustomer.getCountryId()));

        for(FirstLevelDivision f : stateProvinceComboBox.getItems()){
            if(selectedCustomer.getDivisionId() == f.getDivisionId()){
                stateProvinceComboBox.setValue(f);
                break;
            }
        }

        //retrieved id of inCustomer, converted that id which is an int to a string (via the valueOf method) so we can assign it to a text field.
        /*modifyPartNameTxt.setText(selectedPart.getName());
        modifyPartInvTxt.setText(String.valueOf(selectedPart.getStock()));
        modifyPartPriceTxt.setText(String.valueOf(selectedPart.getPrice()));
        modifyPartMaxTxt.setText(String.valueOf(selectedPart.getMax()));
        modifyPartMinTxt.setText(String.valueOf(selectedPart.getMin()));

        if(selectedPart instanceof InHouse) {
            modifyPartMachineTxt.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
            modifyPartMachineLbl.setText("Machine ID");
            modifyPartInHouseRBtn.setSelected(true);
        }
        else {
            modifyPartMachineTxt.setText(((Outsourced) selectedPart).getCompanyName());
            modifyPartMachineLbl.setText("Company Name");
            modifyPartOutsourcedRBtn.setSelected(true);
        }*/
    }
}