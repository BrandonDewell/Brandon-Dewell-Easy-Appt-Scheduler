package daoView_Controller;

import daoImpl.AppointmentDAOImpl;
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

/** This class creates an app that displays lists of customers and appointments. */
public class MainMenuController implements Initializable {

    public TableView<Customer> custTable;
    public TableColumn<Customer, Integer> CRcustIDCol;
    public TableColumn<Customer, String> CRcustNameCol;
    public TableColumn<Customer, String> CRaddressCol;
    public TableColumn<Customer, String> CRstateProvinceCol;
    public TableColumn<Customer, String> CRpostalCodeCol;
    public TableColumn<Customer, String> CRcountryCol;
    public TableColumn<Customer, Integer> CRphoneNumCol;

    public TableView<Appointment> apptTable;
    public TableColumn<Appointment, Integer> SAapptIDCol;
    public TableColumn<Appointment, String> SAtitleCol;
    public TableColumn<Appointment, String> SAdescriptionCol;
    public TableColumn<Appointment, String> SAlocationCol;
    public TableColumn<Appointment, String> SAcontactNameCol;
    public TableColumn<Appointment, Integer> SAcontactCol;
    public TableColumn<Appointment, String> SAtypeCol;
    public TableColumn<Appointment, LocalDateTime> SAsDATCol;
    public TableColumn<Appointment, LocalDateTime> SAeDATCol;
    public TableColumn<Appointment, String> SAcustNameCol;
    public TableColumn<Appointment, Integer> SAcustIDCol;
    public TableColumn<Appointment, String> SAuserNameCol;
    public TableColumn<Appointment, Integer> SAuserIDCol;

    public Button addCustomer;
    public Button updateCustomer;
    public Button deleteCustomer;
    public Button addAppointment;
    public Button updateAppointment;
    public Button deleteAppointment;
    public RadioButton weekRB;
    public RadioButton monthRB;
    public RadioButton allRB;
    public ToggleGroup timePeriod;

    /** This initialize method is the first method to load in this class.
     It sets the Customer and Appointment tables with data from observable lists.
     @param url The url.
     @param resourceBundle The resourceBundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        CustomerDAOImpl dao = new CustomerDAOImpl();

        custTable.setItems(dao.getAllCustomersOL());
        CRcustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        CRcustNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        CRaddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        CRstateProvinceCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        CRpostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        CRcountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        CRphoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        custTable.getSortOrder().addAll(CRcustNameCol);

        AppointmentDAOImpl a = new AppointmentDAOImpl();

        apptTable.setItems(a.getAllAppointmentsOL());
        SAapptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        SAtitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        SAdescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        SAlocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        SAcontactNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        SAcontactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        SAtypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        SAsDATCol.setCellValueFactory(new PropertyValueFactory<>("startString"));
        SAeDATCol.setCellValueFactory(new PropertyValueFactory<>("endString"));
        SAcustNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        SAcustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        SAuserNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        SAuserIDCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        apptTable.getSortOrder().addAll(SAapptIDCol);
    }

    /** This event handler method loads the Customer window without sending any data over.
     @param actionEvent An event from an action.
     @throws IOException  If an input or output exception occurred.
     */
    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/Customer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /** This event handler method loads the Customer window and sends that customer's associated data over.  It checks if a
      selection was made and if not puts up an alert pop up window giving instructions to make a selection first.
     @param actionEvent An event from an action.
     @throws IOException  If an input or output exception occurred.
     */
    public void onActionUpdateCustomer(ActionEvent actionEvent) throws IOException {

        Customer temp = custTable.getSelectionModel().getSelectedItem();

        if (temp == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No selection was made");
            alert.setContentText("Please select a customer from the top table to update.");
            alert.showAndWait();

        }
        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/daoView_Controller/Customer.fxml"));
            loader.load();

            CustomerController CustController = loader.getController();
            CustController.sendCustomer(temp);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent root = loader.getRoot();
            stage.setTitle("Update Customer");
            Scene scene = new Scene(root, 600, 600);
            stage.setScene(scene);
            stage.show();
        }
    }

    /** This event handler method checks if a customer is selected from the top table and if not alerts the user with a pop up window
      giving instructions to select a customer.  When a customer is selected and this button is clicked, another alert window pops up
     confirming the choice of deleting a customer and their appointments (if any are scheduled) as a final precaution.  When confirmed,
     both the customer and any associated appointments with that customer are deleted.
     @param actionEvent An event from an action.
     */
    public void onActionDeleteCustomer(ActionEvent actionEvent) {

        Customer temp = custTable.getSelectionModel().getSelectedItem();
        if (temp == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No selection was made");
            alert.setContentText("Please select a customer from the top table to delete.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm you would like to delete the selected " +
                    "customer and their appointments (if any) by clicking the OK button.");
            alert.setTitle("Deleting a Customer");
            alert.setHeaderText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {

                AppointmentDAOImpl adao = new AppointmentDAOImpl();
                apptTable.refresh();
                adao.deleteCustAppts(temp.getCustomerId());
                apptTable.setItems(adao.getAllAppointmentsOL());

                CustomerDAOImpl dao = new CustomerDAOImpl();
                dao.delete(temp);
                custTable.setItems(dao.getAllCustomersOL());
            }
        }
    }

    /** This event handler method loads the Appointment window without sending any data over.
     @param actionEvent An event from an action.
     @throws IOException  If an input or output exception occurred.
     */
    public void onActionAddAppointment(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/Appointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /** This event handler method loads the Appointment window and sends that appointment's associated data over.  It checks if a
     selection was made and if not puts up an alert pop up window giving instructions to make a selection first.
     @param actionEvent An event from an action.
     @throws IOException  If an input or output exception occurred.
     */
    public void onActionUpdateAppointment(ActionEvent actionEvent) throws IOException {

        Appointment temp = apptTable.getSelectionModel().getSelectedItem();

        if (temp == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No selection was made.");
            alert.setContentText("Please select an appointment from the bottom table to update.");
            alert.showAndWait();

        }
        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/daoView_Controller/Appointment.fxml"));
            loader.load();
            AppointmentController ApptController = loader.getController();

            ApptController.sendAppointment(temp);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent root = loader.getRoot();
            stage.setTitle("Update Appointment");
            Scene scene = new Scene(root, 600, 600);
            stage.setScene(scene);
            stage.show();
        }

    }

    /** This event handler method checks if an appointment is selected from the bottom table and if not alerts the user with a pop up window
     giving instructions to select an appointment.  When an appointment is selected and this button is clicked, another alert window pops up
     confirming the choice of deleting an appointment as a final precaution.  When confirmed the appointment is deleted.
     @param actionEvent An event from an action.
     */
    public void onActionDeleteAppointment(ActionEvent actionEvent) {

        Appointment temp = apptTable.getSelectionModel().getSelectedItem();
        if (temp == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No selection was made");
            alert.setContentText("Please select an appointment from the bottom table to delete.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm you would like to delete the selected " +
                    "appointment by clicking the OK button.");
            alert.setTitle("Deleting an Appointment");
            alert.setHeaderText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                AppointmentDAOImpl adao = new AppointmentDAOImpl();
                adao.delete(temp.getApptId());

                Alert alertInfo = new Alert(Alert.AlertType.INFORMATION, "Please click OK to continue.");
                alertInfo.setTitle("Deleted");
                alertInfo.setHeaderText("The Appointment ID number " + temp.getApptId() + " of type " + temp.getType() + " has been deleted.");
                alertInfo.showAndWait();
                apptTable.setItems(adao.getAllAppointmentsOL());
            }
        }
    }

    /** This event handler method for the Week radio button calls another method to check the database for appointments scheduled
     in the current week and displays only those filtered appointments in the bottom table.
     @param actionEvent An event from an action.
     @throws SQLException  If a database access exception occurred.
     */
    public void onActionWeekView(ActionEvent actionEvent) throws SQLException {

        AppointmentDAOImpl adao = new AppointmentDAOImpl();
        apptTable.setItems(adao.selectWeekViewOL());
        apptTable.getSortOrder().addAll(SAapptIDCol);

    }

    /** This event handler method for the Month radio button calls another method to check the database for appointments scheduled in
     the current month and displays only those filtered appointments in the bottom table.
     @param actionEvent An event from an action.
     @throws SQLException  If a database access exception occurred.
     */
    public void onActionMonthView(ActionEvent actionEvent) throws SQLException {

        AppointmentDAOImpl adao = new AppointmentDAOImpl();
        apptTable.setItems(adao.selectMonthViewOL());
        apptTable.getSortOrder().addAll(SAapptIDCol);

    }

    /** This event handler method for the All radio button calls another method to check the database for all of the appointments
     scheduled and displays those in the bottom table.
     @param actionEvent An event from an action.
     */
    public void onActionAllView(ActionEvent actionEvent) {

        AppointmentDAOImpl adao = new AppointmentDAOImpl();
        apptTable.setItems(adao.getAllAppointmentsOL());
        apptTable.getSortOrder().addAll(SAapptIDCol);
    }

    /** This event handler method for the Reports button loads the Reports menu window.
     @param actionEvent An event from an action.
     @throws IOException  If an input or output exception occurred.
     */
    public void onActionViewReports(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/Reports.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 650);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    /** This event handler method for the Exit button closes the program.
     @param actionEvent An event from an action.
     */
    public void onActionExit(ActionEvent actionEvent) {
            System.exit(0);
        }

}
