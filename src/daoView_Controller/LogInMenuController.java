package daoView_Controller;

import daoImpl.UserDAOImpl;
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
import java.time.ZoneId;
import java.util.Locale;
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
            ObservableList<User> userList = udao.getAllUsersOL();
            boolean userFound = false;

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
                            userFound = true;
                            break;
                        }
                    }
                    if (userFound){
                        Parent root = FXMLLoader.load(getClass().getResource("/daoView_Controller/MainMenu.fxml"));
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 1500, 700);
                        stage.setTitle("Main Menu");
                        stage.setScene(scene);
                        stage.show();
                    } else {
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
