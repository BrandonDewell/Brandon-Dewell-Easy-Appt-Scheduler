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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.time.ZoneId.systemDefault;

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
        zoneLabel.setText(rb.getString("Location") + systemDefault().toString());
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
                    alert.setTitle(rb.getString("Error"));  // the key does the translation
                    alert.setHeaderText(rb.getString("Fields"));
                    alert.setContentText(rb.getString("Please"));
                    ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(rb.getString("OK"));
                    alert.showAndWait();
                } else {
                    for (User u : userList){
                        if ((u.getUserName().equals(userName)) && (u.getPassword().equals(pw))){
                            userFound = true; // get the user id and report it to use
                            break;  // breaks out of the for loop
                        }
                    }
                   // Timestamp time = Timestamp.from(ZonedDateTime.now().toInstant());
                  //  Timestamp time = Timestamp.from(ZonedDateTime.now().toInstant());
                    ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
                    ZonedDateTime zdtUTC = zdt.withZoneSameInstant(ZoneId.of("UTC"));
                    String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnnnn z" ).format(zdtUTC);  // TODO will this work or does it need to be a timestamp?  requirement C from rubric.  Also, am I saving it to the right location?
                    // appending of login auditing
                    String loginAttempt = "login_activity.txt";
                    FileWriter appendLoginAttempt = new FileWriter(loginAttempt, true);

                    if (userFound){

                        // login auditing
                        PrintWriter loginAudit = new PrintWriter(appendLoginAttempt);
                        loginAudit.println("User " + userName + " successfully logged in on " + time);
                        loginAudit.close();

                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();  // get the stage early here at this point.
                        stage.hide();  // hide the stage from being shown in the background when the 15 minute appt pop up comes up.

                       Appointment a = AppointmentDAOImpl.upcomingApptInfo();  // returns minutes between now and the appt time, and the apptId, and assigns those to a.

                        // 15 minute alert check
                        if(a == null) {
                            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION, "Please click OK to continue.");
                            alertInfo.setTitle("No upcoming appointments.");
                            alertInfo.setHeaderText("There are no upcoming appointments within the next 15 minutes.");
                            Optional<ButtonType> resultInfo = alertInfo.showAndWait();

                        } else {

                            int apptId = a.getApptId();
                            LocalTime apptTime = a.getStart().toLocalTime();

                            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION, "Please click OK to continue.");
                            alertInfo.setTitle("An upcoming appointment was found.");
                            alertInfo.setHeaderText("The Appointment ID number " + apptId + " scheduled for today's date " + LocalDate.now() + " and the time " + apptTime + " is coming up soon.");

                            Optional<ButtonType> resultInfo = alertInfo.showAndWait();
                        }

                        // load the main menu window
                        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
                    //  Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();  // I moved this to line 95 to get the stage early so I can hide it (which was done on line 96) from being in the background of the 15 minute
                                                                                                        // pop up alert instead of showing it in the background while the alert was up.
                        Scene scene = new Scene(root, 1500, 700);
                        stage.setTitle("Main Menu");
                        stage.setScene(scene);
                        stage.show();
                    } else {

                        // login auditing
                        PrintWriter loginAudit = new PrintWriter(appendLoginAttempt);
                        loginAudit.println("User " + userName + " had a failed log-in attempt on " + time);
                        loginAudit.close();

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

}
