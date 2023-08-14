package Application;

import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
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
    public RadioButton appointmentFilterWeek;
    public ToggleGroup AppointmentFilter;
    public RadioButton appointmentFilterMonth;
    public RadioButton appointmentAll;
    private Object Stage;


    public void exitOnClicked(ActionEvent actionEvent) {

        System.exit(1);
    }

    public void addAppointmentOnClicked(ActionEvent actionEvent) throws IOException {

        ReferencedMethods.setFormState("add");

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/AppointmentInfo.fxml", 600, 500);
    }

    public void modifyAppointmentOnClicked(ActionEvent actionEvent) throws IOException {


        Appointments selected = (Appointments) appointmentTable.getSelectionModel().getSelectedItem();
        ReferencedMethods.setSelectedAppointment(selected);

        if (selected == null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No Appointment has been selected. Please select a Appointment to modify.");
            alert.showAndWait();
        } else {

            ReferencedMethods.setFormState("modified");
            new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/AppointmentInfo.fxml", 600, 500);
        }


    }

    public void deleteAppointmentOnClicked(ActionEvent actionEvent) {
    }

    public void customerListOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/CustomerOverview.fxml", 700, 550);

    }

    public void reportsOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/Reports.fxml", 1500, 500);
    }

    public void appointmentFilterWeekOnClicked(ActionEvent actionEvent) {

        Timestamp currentTime = ReferencedMethods.getCurrentLocalTime();

        FilteredList<Appointments> filteredAppointments = new FilteredList<>(ListModifications.getAllAppointments(),
                i-> i.start.toLocalDateTime().getDayOfYear() >=  currentTime.toLocalDateTime().getDayOfYear() && i.start.toLocalDateTime().getDayOfYear()
                        <= currentTime.toLocalDateTime().getDayOfYear() + 7 && i.start.toLocalDateTime().getYear() == currentTime.toLocalDateTime().getYear());

        appointment_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("appointmentID"));
        title_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
        description_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
        location_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
        contact_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("contactID"));
        type_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
        startDateAndTime_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("start"));
        endDateAndTime_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("end"));
        customer_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));
        user_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("userID"));
        appointmentTable.setItems(filteredAppointments);

    }

    public void appointmentFilterMonthOnClicked(ActionEvent actionEvent) {
        Timestamp currentTime = ReferencedMethods.getCurrentLocalTime();

        FilteredList<Appointments> filteredAppointments = new FilteredList<>(ListModifications.getAllAppointments(),
                i-> i.start.toLocalDateTime().getMonth() ==  currentTime.toLocalDateTime().getMonth() && i.start.toLocalDateTime().getYear()
                        == currentTime.toLocalDateTime().getYear());

        appointment_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("appointmentID"));
        title_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
        description_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
        location_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
        contact_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("contactID"));
        type_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
        startDateAndTime_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("start"));
        endDateAndTime_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("end"));
        customer_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));
        user_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("userID"));
        appointmentTable.setItems(filteredAppointments);

    }

    public void appointmentAllOnClicked(ActionEvent actionEvent) {

        appointment_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("appointmentID"));
        title_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
        description_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
        location_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
        contact_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("contactID"));
        type_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
        startDateAndTime_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("start"));
        endDateAndTime_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("end"));
        customer_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));
        user_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("userID"));
        appointmentTable.setItems(ListModifications.getAllAppointments());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ListModifications.clearAppointments();
        ListModifications.clearCustomers();

        try {
            sqlCommands.populateAppointments();
        } catch (SQLException Issue) {
            Issue.printStackTrace();
        }


        try {
            sqlCommands.populateCustomers();
        } catch (SQLException Issue) {
            Issue.printStackTrace();
        }

        appointment_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("appointmentID"));
        title_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
        description_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
        location_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
        contact_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("contactID"));
        type_Col.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
        startDateAndTime_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("start"));
        endDateAndTime_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("end"));
        customer_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));
        user_ID_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("userID"));
        appointmentTable.setItems(ListModifications.getAllAppointments());
    }

}
