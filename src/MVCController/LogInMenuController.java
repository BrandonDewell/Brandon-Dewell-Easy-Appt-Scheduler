package MVCController;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInMenuController implements Initializable {
    //public Label TheLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Log In screen is initialized!");
        //TheLabel.setText("Initialized!");
    }

    public void onButtonAction(ActionEvent actionEvent) {
        System.out.println("Sign In button is clicked");

        //TheLabel.setText("You clicked it!");
    }

}
