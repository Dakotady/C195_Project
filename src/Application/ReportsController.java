package Application;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

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
}
