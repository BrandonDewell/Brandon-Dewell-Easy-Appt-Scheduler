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

/** This class takes user input values and adds or updates customers. */
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

    /** This initialize method is the first method to load in this class and it sets up the country combo box with its observable list.
     @param url The url.
     @param resourceBundle The resourceBundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CountryDAOImpl countryDAO = new CountryDAOImpl();
        countryComboBox.setItems(countryDAO.getAllCountriesOL());
    }

    /** This event handler method for the Save button saves a customer.
     It has try/catch statements for ignoring IOException errors. It displays ALERT pop up windows with information on correct input values
     when the user enters incorrect data.  User entered data is checked for empty fields and combo boxes and an Alert pop up window
     is applied if found, awaiting the user's correction.  If a customer is being updated the information is automatically entered
     into the appropriate locations, otherwise a customer is being added and those locations are empty.  The controller performs the update
     or insert into the database and sends the user back to the Main Menu.
     @param actionEvent An event from an action.
     */
    public void onActionSave(ActionEvent actionEvent) {

        try {

            String name = customerNameTextField.getText();
            Country country = countryComboBox.getValue();
            String address = addressTextField.getText();
            FirstLevelDivision stateProv = stateProvinceComboBox.getValue();
            String postCode = postalCodeTextField.getText();
            String phone = phoneNumberTextField.getText();

            if(name.isBlank() || address.isBlank() || postCode.isBlank() || phone.isBlank() || stateProv == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Fields must not be left blank.\nDrop down selection must be made.");
                alert.setContentText("""
                        Please enter a valid value for each text field.
                        Customer Name, Address, Postal Code, and Phone Number must use characters.
                        Please make a selection in the drop down boxes.""");
                alert.showAndWait();
            } else {
                if (selectedCustomer == null) {
                    Customer c = new Customer(0, name, address, postCode, phone, stateProv.getDivisionId(), 0, "", "");
                    CustomerDAOImpl dao = new CustomerDAOImpl();
                    dao.insert(c);
                } else {
                    Customer c = new Customer(selectedCustomer.getCustomerId(), name, address, postCode, phone, stateProv.getDivisionId(), 0, "", "");
                    CustomerDAOImpl dao = new CustomerDAOImpl();
                    dao.update(c);
                }

                Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1500, 700);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** This event handler method for the Cancel button applies an Alert pop up window and if the user clicks OK, loads the main menu.
     @param actionEvent An event from an action.
     @throws IOException  If an input or output exception occurred.
     */
    public void onActionCancel(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear any information you entered.  Would you like to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1500, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }

    /** This event handler method sets the options in the State/Providence combo box based on when a Country has been selected in this combo box.
     * @param actionEvent  An event from an action.
     * */
    public void onActionCountry(ActionEvent actionEvent) {

        Country c = countryComboBox.getValue();
        FirstLevelDivisionDAOImpl firstLevelDivisionDAO = new FirstLevelDivisionDAOImpl();
        stateProvinceComboBox.setItems(firstLevelDivisionDAO.getAllFirstLevelDivisionsOL(c.getCountryId()));
    }

    /** This method receives a reference for a customer object and sets its values to text for text fields and combo boxes.
     @param inCustomer The customer that is sent.
     */
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
    }
}