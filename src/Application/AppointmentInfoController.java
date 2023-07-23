package Application;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class AppointmentInfoController {
    public TextField appointmentIDText;
    public TextField title;
    public TextField description;
    public ComboBox contact;
    public DatePicker startDate;
    public DatePicker endDate;
    public ComboBox customerID;
    public Text customerName;
    public Button cancel;
    public ComboBox startTime;
    public ComboBox endTime;
    public ComboBox userID;
    public Text userName;
    public Button confirm;
    public TextField locationText;

    public void cancelOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "MainScreen.fxml", 1600, 800);
    }

    public void confirmOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "MainScreen.fxml", 1600, 800);

    }
}
