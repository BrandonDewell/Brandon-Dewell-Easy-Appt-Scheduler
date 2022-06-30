package daoView_Controller;

import daoImpl.*;
import daoModel.Appointment;
import daoModel.Contact;
import daoModel.Customer;
import daoModel.User;
import helper.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    private Appointment selectedAppointment;

    public Label appointmentLabel;
    public TextField appointmentIDTextField;
    public TextField titleTextField;
    public TextField locationTextField;
    public ComboBox<Contact> contactComboBox;
    public TextField descriptionTextField;
    public TextField typeTextField;
    public DatePicker startDateDatePicker;
    public ComboBox<Customer> customerIDComboBox;
    public ComboBox<User> userIDComboBox;
    public ComboBox<LocalTime> startTimeComboBox;
    public DatePicker endDateDatePicker;
    public ComboBox<LocalTime> endTimeComboBox;
    public Button saveButton;
    public Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("-- initialize(URL url, ResourceBundle resourceBundle) called from AppointmentController.java");

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        customerIDComboBox.setItems(customerDAO.getAllCustomersOL());

        UserDAOImpl userDAO = new UserDAOImpl();
        userIDComboBox.setItems(userDAO.getAllUsersOL());

        ContactDAOImpl contactDAO = new ContactDAOImpl();
        contactComboBox.setItems(contactDAO.getAllContactsOL());


        ZoneId osZId = ZoneId.systemDefault();
        ZoneId businessZId =  ZoneId.of("America/New_York");
        startTimeComboBox.setItems(Utility.generateDynamicTimeList(osZId, businessZId, LocalTime.of(8,0), 13));
        endTimeComboBox.setItems(Utility.generateDynamicTimeList(osZId, businessZId, LocalTime.of(9,0), 13));

        /*for (int i = 0; i < 23; i++) {  // 8am earliest appt start time up to 9pm latest appt start time.
            startTimeComboBox.getItems().add(LocalTime.of(i, 0));
        }

        for (int i = 1; i < 24; i++) {  // initialize i with 1 because I have decided that each appt will be at least an hour long, and if the start time is at 00:00, the end time must be 01:00 at the earliest.
            endTimeComboBox.getItems().add(LocalTime.of(i, 0));
            if (i == 23) {
                endTimeComboBox.getItems().add(LocalTime.of(0, 0));

            }
        }*/

    }


    public void onActionSave(ActionEvent actionEvent) throws IOException {
        System.out.println("Appointment save button clicked.  -- onActionSave(ActionEvent actionEvent) called in AppointmentController.java\"");

        try {

            String title = titleTextField.getText();
            Customer cust = customerIDComboBox.getValue();
            User u = userIDComboBox.getValue();
            String desc = descriptionTextField.getText();
            String loc = locationTextField.getText();
            Contact contact = contactComboBox.getValue();
            String type = typeTextField.getText();
            LocalDate sDate = startDateDatePicker.getValue();
            LocalTime sTime = startTimeComboBox.getValue();
            LocalDate eDate = endDateDatePicker.getValue();
            LocalTime eTime = endTimeComboBox.getValue();


            AppointmentDAOImpl dao = new AppointmentDAOImpl();
           /* int rowsAffected = dao.insert(title, desc, loc, type, sLDT, eLDT, cust.getCustomerId(), u.getUserId(), contact.getContactId());
            if (rowsAffected > 0){
                System.out.println("insert successful");
            }*/

            if (title.isBlank() || cust == null || u == null || desc.isBlank() || loc.isBlank() || contact == null || type.isBlank() || sDate == null || sTime == null || eDate == null || eTime == null) {  // combo boxes use
                // comboBox == null to do error checks instead of textfield.isBlank()
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Fields must not be left blank.\nA drop down selection must be made.");
                alert.setContentText("Please enter a valid value for each text field.\nTitle, Description, Location, and Type " +
                        "must use characters.\nPlease make a selection in the drop down boxes.\nSelect a date by clicking on the date chooser button.");
                alert.showAndWait();
            } else if(sTime.isAfter(eTime) || sTime.equals(eTime)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Appointment start time must be before the appointment end time.\nStart and end times of an appointment must not be the same.");
                alert.setContentText("Please choose a correct value for each drop-down box.");
                alert.showAndWait();

            } /*else if (     // TODO take timezone i am in and convert to eastern time manually.  make a zoneddatetime object, convert to eastern time.  I can do a check on the inputted dates/times using popup windows, and make them use only .
                    LocalDateTime ldt =
                    ZonedDateTime zdt = ZonedDateTime.of
                )*/

            else {
                LocalDateTime sLDT = LocalDateTime.of(sDate, sTime);
                LocalDateTime eLDT = LocalDateTime.of(eDate, eTime);

                //ZonedDateTime zdt = sLDT.atZone();  // TODO what is the zone ID that I have to pass into the atZone method?

                if (selectedAppointment == null) {  // This is where the determination is made whether the add or update button was clicked in the main menu.  Nothing was selected so this is an Add Appointment situation.
                    //Appointment a = new Appointment(0, title, cust.getCustomerId(), u.getUserId(), desc, loc, contact.getContactName(), type, sLDT, eLDT);  // The customerId, countryId, division, and country parameters are not really important and
                    // I don't care about that info so I can "leave" them blank.

                    //AppointmentDAOImpl dao = new AppointmentDAOImpl();

                    //dao.insert(a);
                    int rowsAffected = dao.insert(title, desc, loc, type, sLDT, eLDT, cust.getCustomerId(), u.getUserId(), contact.getContactId());
                    if (rowsAffected > 0){
                        System.out.println("**************************appt insert successful");
                    }
                } else {  // Update situation
                   // Appointment a = new Appointment(selectedAppointment.getApptId(), title, cust.getCustomerId(), u.getUserId(), desc, loc, contact.getContactName(), type, sDate, sTime, eDate, eTime);
                    //AppointmentDAOImpl dao = new AppointmentDAOImpl();
                   // dao.update(a);
                    int rowsAffected = dao.update(selectedAppointment.getApptId(), title, desc, loc, type, sLDT, eLDT, cust.getCustomerId(), u.getUserId(), contact.getContactId());
                    if (rowsAffected > 0){
                        System.out.println("*************************appt update successful");
                    }
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

    public void onActionCancel (ActionEvent actionEvent) throws IOException {
        System.out.println("Appointment cancel button clicked");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear any information you entered.  Would you like to continue?");
        Optional<ButtonType> result = alert.showAndWait();                  // optional container we named result contains enumerations for button types.
        if (result.isPresent() && result.get() == ButtonType.OK) {            // isPresent returns a boolean if there is something inside the optional container.
            // therefore its a check on whether someone clicked a button.  result.get() checks to see what type of button is clicked, the ok button or cancel button.
            Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1500, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onActionCustomerID (ActionEvent actionEvent) throws IOException {
        Customer c = customerIDComboBox.getValue();
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        contactComboBox.setItems(contactDAO.getAllContactsOL());
    }

    public void sendAppointment (Appointment inAppointment){
        selectedAppointment = inAppointment;

        appointmentIDTextField.setText(String.valueOf(selectedAppointment.getApptId()));
        titleTextField.setText(String.valueOf(selectedAppointment.getTitle()));
        descriptionTextField.setText(String.valueOf(selectedAppointment.getDescription()));
        locationTextField.setText(String.valueOf(selectedAppointment.getLocation()));
        typeTextField.setText(String.valueOf(selectedAppointment.getType()));

        for (Customer c : customerIDComboBox.getItems()) {
            if (selectedAppointment.getCustomerId() == c.getCustomerId()) {
                customerIDComboBox.setValue(c);
            }
        }

        for (User u : userIDComboBox.getItems()) {
            if (selectedAppointment.getUserId() == u.getUserId()) {
                userIDComboBox.setValue(u);
            }
        }

        for (Contact c : contactComboBox.getItems()) {
            if (selectedAppointment.getContactId() == c.getContactId()) {
                contactComboBox.setValue(c);
                break;
            }
        }

        startDateDatePicker.setValue(selectedAppointment.getStart().toLocalDate());

        endDateDatePicker.setValue(selectedAppointment.getEnd().toLocalDate());

        startTimeComboBox.setValue(selectedAppointment.getStart().toLocalTime());

        endTimeComboBox.setValue(selectedAppointment.getEnd().toLocalTime());

    }

    public void onActionStartDate(ActionEvent actionEvent) {

        /*LocalDate dpStart = startDateDatePicker.getValue();  // TODO can I create an observable list of datepicker dates to get the end date to automatically be set to the start date?  should I just get rid of the end date date picker and its label?
        DatePicker dpEnd = new DatePicker();
        endDateDatePicker.setItems(dpStart);*/

        /*Country c = countryComboBox.getValue();
        FirstLevelDivisionDAOImpl firstLevelDivisionDAO = new FirstLevelDivisionDAOImpl();
        stateProvinceComboBox.setItems(firstLevelDivisionDAO.getAllFirstLevelDivisionsOL(c.getCountryId()));*/
    }
}
