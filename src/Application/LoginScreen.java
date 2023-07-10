package Application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {

    public Text zoneID;

    @FXML

    public void exitApplicationOnClicked() {
        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zoneID.setText(Main.getLocalUTC().getId().toString());
    }
}
