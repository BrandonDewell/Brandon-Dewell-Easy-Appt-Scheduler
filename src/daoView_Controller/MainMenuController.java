package daoView_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
// test

public class MainMenuController implements Initializable {

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
        System.out.println("Main Menu is initialized");
    }

    public void onActionAddCustomer(ActionEvent actionEvent) {
    }

    public void onActionUpdateCustomer(ActionEvent actionEvent) {
    }

    public void onActionDeleteCustomer(ActionEvent actionEvent) {
    }

    public void onActionAddAppointment(ActionEvent actionEvent) {
    }

    public void onActionUpdateAppointment(ActionEvent actionEvent) {
    }

    public void onActionDeleteAppointment(ActionEvent actionEvent) {
    }

    public void onActionWeekView(ActionEvent actionEvent) {
    }

    public void onActionMonthView(ActionEvent actionEvent) {
    }

    public void onActionAllView(ActionEvent actionEvent) {
    }
}
