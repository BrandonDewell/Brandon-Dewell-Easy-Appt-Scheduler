package daoView_Controller;

import daoImpl.AppointmentDAOImpl;
import daoImpl.UserDAOImpl;
import daoModel.Appointment;
import daoModel.User;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class LogInMenuController implements Initializable {
    public Button displayMainMenuB;
    public TextField userNameTxt;
    public TextField passwordTxt;
    public Label userNameLabel;
    public Label passwordLabel;
    public Label logInLabel;
    public Label zoneLabel;
    ResourceBundle rb = ResourceBundle.getBundle("main/Natural", Locale.getDefault());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameLabel.setText(rb.getString("Username"));
        passwordLabel.setText(rb.getString("Password"));
        displayMainMenuB.setText(rb.getString("SignIn"));
        logInLabel.setText(rb.getString("LogIn"));
        zoneLabel.setText(rb.getString("Location") + ZoneId.systemDefault().toString());
    }

    public void onActionSignIn(ActionEvent actionEvent) {
        System.out.println("Sign In button is clicked");

            UserDAOImpl udao = new UserDAOImpl();
            AppointmentDAOImpl adao = new AppointmentDAOImpl();
            ObservableList<User> userList = udao.getAllUsersOL();
            boolean userFound = false;
            boolean upcomingAppt = false;

            try {
                String userName = userNameTxt.getText();
                String pw = passwordTxt.getText();

                if (userName.isEmpty() || pw.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(rb.getString("Error"));
                    alert.setHeaderText(rb.getString("Fields"));
                    alert.setContentText(rb.getString("Please"));
                    ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(rb.getString("OK"));
                    alert.showAndWait();
                } else {
                    for (User u : userList){
                        if ((u.getUserName().equals(userName)) && (u.getPassword().equals(pw))){
                            userFound = true; // get the user id and report it to use
                            break;
                        }
                    }
                    if (userFound){  // TODO write to a file for a GOOD login attempt.  know user id and do 15 min alert check .  get all appts with user id  check each if the start time is > now, and start is < now + 15 min.  do this before loading next screen.

                       Appointment a = AppointmentDAOImpl.upcomingApptInfo();  // returns minutes between now and the appt time, and the apptId, and assigns those to a.
                        if(a == null) {

                            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION, "Please click OK to continue.");
                            alertInfo.setTitle("No upcoming appointments");
                            alertInfo.setHeaderText("There are no upcoming appointments within the next 15 minutes.");
                            Optional<ButtonType> resultInfo = alertInfo.showAndWait();

                        } else {

                            int apptId = a.getApptId();
                           // int minBetween = a.get
                            // LocalDate apptDate = a.getStart().toLocalDate();
                            LocalTime apptTime = a.getStart().toLocalTime();

                            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION, "Please click OK to continue.");
                            alertInfo.setTitle("An appointment found");
                            //alertInfo.setHeaderText("The Appointment ID number " + apptId + " scheduled for today's date " + apptDate + " and the time " + apptTime + " is coming up soon.");
                            alertInfo.setHeaderText("The Appointment ID number " + apptId + " scheduled for today's date " + LocalDate.now() + " and the time " + apptTime + " is coming up soon.");

                            Optional<ButtonType> resultInfo = alertInfo.showAndWait();
                        }

                      //  alertInfo.setHeaderText("The Appointment ID number " + temp.getApptId() + " of type " + temp.getType() + " has been deleted.");


                        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 1500, 700);
                        stage.setTitle("Main Menu");
                        stage.setScene(scene);
                        stage.show();
                    } else {  // TODO write to a file for a bad login attempt
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(rb.getString("Error"));
                        alert.setHeaderText(rb.getString("Incorrect"));
                        alert.setContentText(rb.getString("PleaseTryAgain"));
                        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(rb.getString("OK"));
                        alert.showAndWait();

                        userNameTxt.setText("");
                        passwordTxt.setText("");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }



    // copy of above before language changes are made




    /*public void onActionSignIn(ActionEvent actionEvent) {
        System.out.println("Sign In button is clicked");

            UserDAOImpl udao = new UserDAOImpl();
            ObservableList<User> userList = udao.getAllUsersOL();
            boolean userFound = false;

            try {
                String userName = userNameTxt.getText();
                String pw = passwordTxt.getText();

                if (userName.isEmpty() || pw.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(rb.getString("Error"));
                    alert.setHeaderText(rb.getString("Fields must not be left blank."));
                    alert.setContentText("Please enter a valid value for each text field.\nUser Name and Password must use characters.");
                    alert.showAndWait();
                } else {
                    for (User u : userList){
                        if ((u.getUserName().equals(userName)) && (u.getPassword().equals(pw))){
                            userFound = true;
                            break;
                        }
                    }
                    if (userFound){
                        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 1200, 700);
                        stage.setTitle("Main Menu");
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Incorrect User Name or Password");
                        alert.setContentText("Please try again.");
                        alert.showAndWait();

                        userNameTxt.setText("");
                        passwordTxt.setText("");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/



}
