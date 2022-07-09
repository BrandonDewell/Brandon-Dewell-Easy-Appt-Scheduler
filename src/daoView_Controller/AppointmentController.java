package daoView_Controller;

import daoImpl.AppointmentDAOImpl;
import daoImpl.ContactDAOImpl;
import daoImpl.CustomerDAOImpl;
import daoImpl.UserDAOImpl;
import daoModel.Appointment;
import daoModel.Contact;
import daoModel.Customer;
import daoModel.User;
import helper.Utility;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class takes user input values and adds or updates appointments. */
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

    /** This initialize method is the first method to load in this class and it sets up combo boxes with observable lists.
     @param url The url.
     @param resourceBundle The resourceBundle.
     */
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
        ZoneId businessZId = ZoneId.of("America/New_York");
        startTimeComboBox.setItems(Utility.generateDynamicTimeListOL(osZId, businessZId, LocalTime.of(8, 0), 52));
        endTimeComboBox.setItems(Utility.generateDynamicTimeListOL(osZId, businessZId, LocalTime.of(8, 15), 52));

    }

    /** This event handler method for the Save button saves an appointment.
     It has try statements for checking for input errors by the user and a catch statement for IOExceptions.  It displays Alert pop up windows
     with information on correct input values when the user enters incorrect data.  If an appointment is being updated the information
     is automatically entered into the appropriate locations, otherwise an appointment is being added and those locations are empty.  The
     controller checks if there is an overlap in the time of a previously scheduled appointment and displays a popup error if so,
     otherwise it performs the update or insert into the database and sends the user back to the Main Menu.
     @param actionEvent An event from an action.
     */
    public void onActionSave(ActionEvent actionEvent) {
        System.out.println("Appointment save button clicked.  -- onActionSave(ActionEvent actionEvent) called in AppointmentController.java\"");

        try {

            String title = titleTextField.getText();  // whether user enters info or it was sent over.
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

            if (title.isBlank() || cust == null || u == null || desc.isBlank() || loc.isBlank() || contact == null || type.isBlank() || sDate == null || sTime == null || eDate == null || eTime == null) {
                // comboBoxes use comboBox == null to do error checks instead of textfield.isBlank()
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Fields must not be left blank.\nA drop down selection must be made.\nA date must be selected.");
                alert.setContentText("""
                        Please enter a valid value for each text field.
                        Title, Description, Location, and Type must use characters.
                        Please make a selection in the drop down boxes.
                        Select a date by clicking on the calendar icon.""");
                alert.showAndWait();
                return;
            } else if (sTime.isAfter(eTime) || sTime.equals(eTime)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Appointment start time must be before the appointment end time.\nStart and end times of an appointment must not be the same.");
                alert.setContentText("Please choose a correct value for each drop-down box.");
                alert.showAndWait();
                return;
            } else if (sDate.isAfter(eDate)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("The appointment must end on the same day as the start date or later.");
                alert.setContentText("Please choose a correct value for each date.");
                alert.showAndWait();
                return;
            } else {
                LocalDateTime sLDT = LocalDateTime.of(sDate, sTime);
                LocalDateTime eLDT = LocalDateTime.of(eDate, eTime);

                // This is where the determination is made whether the add or update button was clicked in the main menu.
                //if (selectedAppointment == null) {  // Add Appointment situation (does an insert) since no appointment was selected on the main menu and sent over to this screen, and therefore selectedAppointment was not assigned anything.

                // appt overlap check
                ObservableList<Appointment> allAppts = dao.getAllAppointmentsOL();
                boolean isOverlap = false;  // AKA a sentinel
                int apptid = 0;
                if(selectedAppointment != null){
                    apptid = selectedAppointment.getApptId();
                }

                for (Appointment a : allAppts) {

                    if(a.getApptId() == apptid) {  // TODO if apptid found, ignore it.  Add another customer if case if this comes back from evaluators.  ex: add another if statement if(a.getCustId != custid) { continue; }
                        continue;
                    }

                    if (a.getStart().isEqual(sLDT) || a.getEnd().isEqual(eLDT)) {  // a.getStart() gets the DB appts and compares those start times to the start time being inputted.  In this situation the start dates/times for both appointments being compared
                        // are the same, creating an overlap.  ex:  DB 1pm start time = user input 1pm start time    OR    DB 2pm end time = user input 2pm end time.
                        isOverlap = true;
                        break;
                    }
                    if (a.getStart().isBefore(sLDT) && a.getEnd().isAfter(sLDT)) {  // the new appointment starts within the start and ends within the end time of the already scheduled appt, creating an overlap.  ex: DB 10-11am, user input is 10:15-10:30am.
                        isOverlap = true;
                        break;
                    }
                    if (a.getStart().isAfter(sLDT) && a.getEnd().isBefore(eLDT)) {  // the new appointment starts before and ends after the start and end time of the already scheduled appt, creating an overlap.  ex: DB 8-9am, user input is 7:30am-9:30am.
                        isOverlap = true;
                        break;
                    }
                    if (a.getStart().isBefore(sLDT) && sLDT.isBefore(a.getEnd())) {  // user inputted start time falls between the DB start and end times.
                        isOverlap = true;
                        break;
                    }
                    if (a.getStart().isBefore(eLDT) && eLDT.isBefore(a.getEnd())) {  // user inputted end time falls between the DB start and end times.
                        isOverlap = true;
                        break;
                    }
                }
                // throw up error popup
                if (isOverlap) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Appointments must not overlap.");
                    alert.setContentText("Please choose a correct value for each date and time.");
                    alert.showAndWait();
                    return;
                } else {
                    if (selectedAppointment == null) {
                        int rowsAffected = dao.insert(title, desc, loc, type, sLDT, eLDT, cust.getCustomerId(), u.getUserId(), contact.getContactId());
                        if (rowsAffected > 0) {
                            System.out.println("**************************appt insert successful");
                        }

                    } else {
                        int rowsAffected = dao.update(selectedAppointment.getApptId(), title, desc, loc, type, sLDT, eLDT, cust.getCustomerId(), u.getUserId(), contact.getContactId());
                        if (rowsAffected > 0) {
                            System.out.println("*************************appt update successful");
                        }
                    }
                }
            }

                /*else {  // Update situation because a selection was made in the main menu.
                    // appt overlap check

                    ObservableList<Appointment> allAppts = dao.getAllAppointmentsOL();
                    boolean isOverlap = false;  // AKA a sentinel

                    for(Appointment a : allAppts){
                        if (a.getStart().isEqual(sLDT)){  // start dates/times for both appointments are the same, creating an overlap.
                            isOverlap = true;
                            break;
                        }
                        else if (a.getStart().isBefore(sLDT) && a.getEnd().isAfter(sLDT)){  // the new appointment starts within the start and end time of the already scheduled appt, creating an overlap.
                            isOverlap = true;
                            break;
                        }
                        else if (a.getStart().isAfter(sLDT) && a.getEnd().isBefore(eLDT)){  // the new appointment starts before and ends after the start and end time of the already scheduled appt, creating an overlap.
                            isOverlap = true;
                            break;
                        }

                        *//*for(Appointment a : allAppts){
                            if (a.getStart().isEqual(selectedAppointment.getStart())){  // start dates/times for both appointments are the same, creating an overlap.
                                isOverlap = true;
                                break;
                            }
                            if (a.getStart().isBefore(selectedAppointment.getStart()) && a.getEnd().isAfter(selectedAppointment.getStart())){  // the new appointment starts within the start and end time of the already scheduled appt, creating an overlap.
                                isOverlap = true;
                                break;
                            }
                            if (a.getStart().isAfter(selectedAppointment.getStart()) && a.getEnd().isBefore(selectedAppointment.getEnd())){  // the new appointment starts before and ends after the start and end time of the already scheduled appt, creating an overlap.
                                isOverlap = true;
                                break;
                            }*//*

                        if(isOverlap){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Appointments must not overlap.");
                            alert.setContentText("Please choose a correct value for each date and time.");
                            alert.showAndWait();
                            return;
                        }  else {

                            }
                        }
                    }*/
            // }

            Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1500, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** This event handler method for the Cancel button applies an Alert pop up window and if the user clicks OK, loads the main menu.
     @param actionEvent An event from an action.
     @throws IOException  If an input or output exception occurred.
     */
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
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

    /** This event handler method for the customer combo box displays the observable list of customers.
     @param actionEvent An event from an action.
     */
    public void onActionCustomerID(ActionEvent actionEvent) {
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        contactComboBox.setItems(contactDAO.getAllContactsOL());
    }

    /** This method receives a reference for an appointment object and sets its values to text for text fields,  combo boxes, and date picker boxes.
     @param inAppointment The appointment that is sent.
     */
    public void sendAppointment(Appointment inAppointment) {
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

    /** This event handler method for the Start Date date picker box assigns a date to the text field part of the box when a date
        is chosen from the drop down calendar.
     @param actionEvent  An event from an action. */
    public void onActionStartDate(ActionEvent actionEvent) {
        System.out.println("onActionStartDate event handler activated.");
        //if(startDateDatePicker.getValue() < endDateDatePicker.){

    }

        /*LocalDate dpStart = startDateDatePicker.getValue();
        DatePicker dpEnd = new DatePicker();
        endDateDatePicker.setItems(dpStart);*/

        /*Country c = countryComboBox.getValue();
        FirstLevelDivisionDAOImpl firstLevelDivisionDAO = new FirstLevelDivisionDAOImpl();
        stateProvinceComboBox.setItems(firstLevelDivisionDAO.getAllFirstLevelDivisionsOL(c.getCountryId()));*/

}