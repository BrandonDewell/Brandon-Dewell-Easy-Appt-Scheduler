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

public class MainMenuController implements Initializable {

    Scene scene;
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
        CRcountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        CRphoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        AppointmentDAOImpl a = new AppointmentDAOImpl();

        apptTable.setItems(a.getAllAppointmentsOL());
        SAapptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        SAtitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        SAdescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        SAlocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        SAcontactNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        SAcontactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        SAtypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        SAsDATCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        SAeDATCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        SAcustNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        SAcustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        SAuserNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
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

        Customer temp = custTable.getSelectionModel().getSelectedItem();  // 1. get the selected item and assign it to a temporary variable.

        if (temp == null) {        // 2. check if temp is null, then if null pop up an error message box telling the user to make sure to select an item.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No selection was made");
            alert.setContentText("Please select a customer from the top table to update.");
            alert.showAndWait();

        }
        else {                   // 3. else do the rest including sending data and loading the update customer stage.
            FXMLLoader loader = new FXMLLoader();                                           // 4. created the FXMLLoader object.
            loader.setLocation(getClass().getResource("/daoView_Controller/Customer.fxml"));  // 5. let that loader object know which view to use.
            loader.load();                                                                  // 6. load the object.

            CustomerController CustController = loader.getController();  // 7. call the loader object's getController() method using the loader reference variable CustController.
             // 8. use indexOf() to get the index of the selected item in the allParts observableList and assign it to tempIndex.  **** we dont use indexOf() with a database.

            CustController.sendCustomer(temp);        // 9. move the selected object to the controller
              // and also move the index of the selected object.  ** we dont deal with the index.

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();                      // 10. then set the stage and scene
            Parent root = loader.getRoot();
            stage.setTitle("Update Customer");
            Scene scene = new Scene(root, 600, 600);
            stage.setScene(scene);
            stage.show();
        }
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
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                AppointmentDAOImpl adao = new AppointmentDAOImpl();
                adao.delete(temp.getCustomerId());
                apptTable.setItems(adao.getAllAppointmentsOL());

                CustomerDAOImpl dao = new CustomerDAOImpl();
                dao.delete(temp);  // tried casting customer object temp to customerDAOImpl object, but wont work.
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

        System.out.println("Update Customer button clicked.  -- onActionUpdateCustomer(ActionEvent actionEvent) called in MainMenuController.java");
        Appointment temp = apptTable.getSelectionModel().getSelectedItem();
          // 1. get the selected item and assign it to a temporary variable.

        if (temp == null) {        // 2. check if temp is null, then if null pop up an error message box telling the user to make sure to select an item.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No selection was made.");
            alert.setContentText("Please select an appointment from the bottom table to update.");
            alert.showAndWait();

        }
        else {                   // 3. else do the rest including sending data and loading the update customer stage.
            FXMLLoader loader = new FXMLLoader();                                           // 4. created the FXMLLoader object.
            loader.setLocation(getClass().getResource("/daoView_Controller/Appointment.fxml"));  // 5. let that loader object know which view to use.
            loader.load();                                                                  // 6. load the object.

            AppointmentController ApptController = loader.getController();  // 7. call the loader object's getController() method using the loader reference variable CustController.
            // 8. use indexOf() to get the index of the selected item in the allParts observableList and assign it to tempIndex.  **** we dont use indexOf() with a database.

            ApptController.sendAppointment(temp);        // 9. move the selected object to the controller
            // and also move the index of the selected object.  ** we dont deal with the index.

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();                      // 10. then set the stage and scene
            Parent root = loader.getRoot();
            stage.setTitle("Update Appointment");
            Scene scene = new Scene(root, 600, 600);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onActionDeleteAppointment(ActionEvent actionEvent) {
        System.out.println("Delete Appointment button clicked.  -- onActionDeleteAppointment(ActionEvent actionEvent) called in MainMenuController.java");

        Appointment temp = apptTable.getSelectionModel().getSelectedItem();
        if (temp == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No selection was made");
            alert.setContentText("Please select an appointment from the bottom table to delete.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm you would like to delete the selected " +
                    "customer by clicking the OK button.");
            alert.setTitle("Deleting a Customer");
            alert.setHeaderText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                AppointmentDAOImpl adao = new AppointmentDAOImpl();
                adao.delete(temp.getApptId());
                apptTable.setItems(adao.getAllAppointmentsOL());

                /*CustomerDAOImpl dao = new CustomerDAOImpl();
                dao.delete(temp);  // tried casting customer object temp to customerDAOImpl object, but wont work.
                custTable.setItems(dao.getAllCustomersOL());*/
            }
        }
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
