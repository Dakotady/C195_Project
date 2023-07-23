package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainScreenController {
    public TableView appointmentTable;
    public TableColumn appointment_ID_Col;
    public TableColumn title_Col;
    public TableColumn description_Col;
    public TableColumn location_Col;
    public TableColumn contact_Col;
    public TableColumn type_Col;
    public TableColumn startDateAndTime_Col;
    public TableColumn endDateAndTime_Col;
    public TableColumn customer_ID_Col;
    public TableColumn user_ID_Col;
    public Button exit;
    public Button addAppointment;
    public Button modifyAppointment;
    public Button deleteAppointment;
    public Button customerList;
    public Button reports;
    private Object Stage;



    public void exitOnClicked(ActionEvent actionEvent) {

        System.exit(1);
    }

    public void addAppointmentOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "AppointmentInfo.fxml", 600, 500);

    }

    public void modifyAppointmentOnClicked(ActionEvent actionEvent) throws IOException {

       new ReferencedMethods().newStage(actionEvent, "AppointmentInfo.fxml", 600, 500);

    }

    public void deleteAppointmentOnClicked(ActionEvent actionEvent) {
    }

    public void customerListOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "CustomerOverview.fxml", 700, 550);

    }

    public void reportsOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "Reports.fxml", 1500, 500);
    }
}
