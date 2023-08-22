package Application;

import Connections.FileIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javax.swing.text.StyledEditorKit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    public ComboBox<Month> months;
    public ComboBox<Integer> years;
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
    public Button report1;
    public Button report2;
    public Button report3;

    public void backOnClicked(ActionEvent actionEvent) throws IOException {
        ListModifications.clearAppointments();
        ListModifications.clearContacts();

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/MainScreen.fxml", 1600, 800);

    }

    public void report1OnClicked(ActionEvent actionEvent) {

        ObservableList<Appointments> appointments = ListModifications.getAllAppointments();
        ObservableList<Appointments> value = FXCollections.observableArrayList();

        try {
            int year = years.getValue();
            Month month = months.getValue();

            for (Appointments appointment : appointments) {

                if (appointment.start.toLocalDateTime().getYear() == year && appointment.start.toLocalDateTime().getMonth().equals(month)){
                    value.add(appointment);
                }

            }

            appointmentsByMonth.setText(String.valueOf(value.size()));

        }catch (Exception valueNull){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please populate both the month and year for report.");
            alert.showAndWait();
        }



    }

    public void report2OnClicked(ActionEvent actionEvent) {

        ObservableList<Appointments> appointments = ListModifications.getAllAppointments();
        ObservableList<Appointments> value = FXCollections.observableArrayList();

        String contactName = null;
        int contactID = 0;
        try {
            contactName = contact.getValue().toString();
            contactID = sqlCommands.getContactID(contactName);

            for (Appointments appointment : appointments) {

                if (appointment.contactID == contactID){
                    value.add(appointment);
                }
            }

            appointmentID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("appointmentID"));
            title_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
            description_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
            type_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
            startDateAndTime_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("start"));
            endDateAndTime_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("end"));
            customerID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));
            appointmentTableReport.setItems(value);

        }catch (Exception valueNull){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a contact name.");
            alert.showAndWait();
        }




    }

    public void report3OnClicked(ActionEvent actionEvent) throws FileNotFoundException {

        try {


            ObservableList<LoginActivity> loginActivities = ListModifications.getAllLoginActivity();
            ObservableList<LoginActivity> value = FXCollections.observableArrayList();
            loginActivities.clear();
            FileIO.readFile();

            LocalDate localDate = datePicker.getValue();

            for (LoginActivity login : loginActivities) {

                if (localDate.isEqual(login.localDateTime.toLocalDate()) && login.attempt == 0){

                    value.add(login);
                }

            }

            totalFailedLogins.setText(String.valueOf(value.size()));

        }catch (Exception valueNull){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select the date to review.");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Integer> year = FXCollections.observableArrayList();
        ObservableList<Month> month = FXCollections.observableArrayList();

        year.clear();
        month.clear();

        ObservableList<Appointments> appointments = ListModifications.getAllAppointments();

        Boolean checkYear = false;
        Boolean checkMonth = false;

        for (Appointments appointment : appointments ) {

            if (year.size() > 0){
                for ( Integer y : year ) {

                    if (appointment.start.toLocalDateTime().getYear() == y){

                        checkYear = true;
                    }

                }
                if (!checkYear){
                    year.add(appointment.start.toLocalDateTime().getYear());
                }
                checkYear = false;
            }else {
                year.add(appointment.start.toLocalDateTime().getYear());
            }

            if (month.size() > 0){
                for ( Month m : month ) {

                    if (appointment.start.toLocalDateTime().getMonth().equals(m)){

                        checkMonth = true;
                    }
                }
                if (!checkMonth){
                    month.add(appointment.start.toLocalDateTime().getMonth());
                }
                checkMonth = false;
            }else {
                month.add(appointment.start.toLocalDateTime().getMonth());
            }

        }

        months.setItems(month);
        years.setItems(year);

        try {
            sqlCommands.populateContacts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        contact.setItems(ListModifications.getAllContacts());


    }
}
