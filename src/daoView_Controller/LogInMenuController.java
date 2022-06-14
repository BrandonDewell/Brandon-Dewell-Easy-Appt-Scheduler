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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInMenuController implements Initializable {
    public Label ZoneLabel;
    public Button displayMainMenuB;
    public TextField userNameTxt;
    public TextField passwordTxt;
    public Label userNameLabel;
    public Label passwordLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Log In screen is initialized!");  // TODO pull location data and display it on the "Location" label
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
                alert.setTitle("Error");
                alert.setHeaderText("Fields must not be left blank.");
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

}
