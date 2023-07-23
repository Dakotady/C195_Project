package Application;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CustomerInfoController {
    public TextField customerID;
    public TextField customerAddress;
    public TextField customerName;
    public TextField phoneNumber;
    public TextField postalCode;
    public ComboBox country;
    public ComboBox state;
    public Button confirm;
    public Button cancel;

    public void confirmOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "CustomerOverview.fxml", 700, 550);
    }

    public void cancelOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "CustomerOverview.fxml", 700, 550);
    }
}
