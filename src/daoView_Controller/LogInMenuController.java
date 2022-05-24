package daoView_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInMenuController implements Initializable {
    public Label ZoneLabel;
    public Button displlayMainMenuB;
    public TextField userNameTxt;
    public TextField passwordTxt;
    public Label userNameLabel;
    public Label passwordLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Log In screen is initialized!");
    }

    public void onButtonAction(ActionEvent actionEvent) {
        System.out.println("Sign In button is clicked");
    }

}
