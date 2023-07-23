package Application;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;

public class ReportsController {
    public ComboBox months;
    public ComboBox years;
    public Text appointmentsByMonth;
    public TableView appointmentTableReport;
    public TableColumn appointmentID_Col;
    public TableColumn title_Col;
    public TableColumn type_Col;
    public TableColumn description_Col;
    public TableColumn startDateAndTime_Col;
    public TableColumn endDateAndTime_Col;
    public TableColumn customerID_Col;
    public ComboBox contact;
    public DatePicker datePicker;
    public Text totalFailedLogins;
    public Button back;

    public void backOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "MainScreen.fxml", 1600, 800);

    }
}
