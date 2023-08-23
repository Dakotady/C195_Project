package Application;

import Connections.FileIO;
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

/**
 * This class controls the the mainScreen.fxml.
 */
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


    /**
     * This closes the application.
     * @param actionEvent
     */
    public void exitOnClicked(ActionEvent actionEvent) {

        System.exit(1);
    }

    /**
     * This opens the AppointmentInfo.fxml and sets the formState to add.
     * @param actionEvent
     * @throws IOException
     */
    public void addAppointmentOnClicked(ActionEvent actionEvent) throws IOException {

        ReferencedMethods.setFormState("add");

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/AppointmentInfo.fxml", 600, 500);
    }

    /**
     * This opens the AppointmentInfo.fxml, gets the selected appointment form the table, and sets the formState to modify.
     * @param actionEvent
     * @throws IOException
     */
    public void modifyAppointmentOnClicked(ActionEvent actionEvent) throws IOException {

        ReferencedMethods.setFormState("modify");
        Appointments selected = (Appointments) appointmentTable.getSelectionModel().getSelectedItem();
        ReferencedMethods.setSelectedAppointment(selected);

        if (selected == null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No Appointment has been selected. Please select a Appointment to modify.");
            alert.showAndWait();
        } else {

            ReferencedMethods.setFormState("modify");
            new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/AppointmentInfo.fxml", 600, 500);
        }


    }

    /**
     * This deletes the selected appointment.
     * @param actionEvent
     * @throws SQLException
     */
    public void deleteAppointmentOnClicked(ActionEvent actionEvent) throws SQLException {

        Boolean response;

        Appointments selected = (Appointments) appointmentTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete AppointmentID = " + selected.appointmentID +
                ", Appointment Type = " + selected.type + " ?");
        alert.setContentText("Choose Ok to continue or Cancel to continue.");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        response = result == ButtonType.OK;

        if (response) {

            sqlCommands.deleteAppointment(selected.appointmentID);
            ListModifications.getAllAppointments().remove(selected);
        }
    }

    /**
     * This opens the CustomerOverview.fxml.
     * @param actionEvent
     * @throws IOException
     */
    public void customerListOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/CustomerOverview.fxml", 700, 550);

    }

    /**
     * This opens the Reports.fxml.
     * @param actionEvent
     * @throws IOException
     */
    public void reportsOnClicked(ActionEvent actionEvent) throws IOException {

        FileIO.readFile();
        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/Reports.fxml", 1500, 500);
    }

// Lambda

    /**
     * LAMBDA : This utilizes a Lambda expression that assets the creation of a filtered list that sorts by current appointments by week.
     * @param actionEvent
     */
    public void appointmentFilterWeekOnClicked(ActionEvent actionEvent) {

        Timestamp currentTime = ReferencedMethods.getCurrentLocalTime();

        FilteredList<Appointments> filteredAppointments = new FilteredList<>(ListModifications.getAllAppointments(),
                i-> i.start.toLocalDateTime().getDayOfYear() >=  currentTime.toLocalDateTime().getDayOfYear() -
                        ReferencedMethods.minDayOfWeek(currentTime.toLocalDateTime().toLocalDate()) && i.start.toLocalDateTime().getDayOfYear() <=
                        currentTime.toLocalDateTime().getDayOfYear() + ReferencedMethods.maxDayOfWeek(currentTime.toLocalDateTime().toLocalDate())
                        && i.start.toLocalDateTime().getYear() == currentTime.toLocalDateTime().getYear());

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

    /**
     * LAMBDA : This utilizes a Lambda expression that assets the creation of a filtered list that sorts by current appointments by month.
     * @param actionEvent
     */
    public void appointmentFilterMonthOnClicked(ActionEvent actionEvent) {
        Timestamp currentTime = ReferencedMethods.getCurrentLocalTime();

        //Lambda
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

    /**
     * This returns all the appointments.
     * @param actionEvent
     */
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

    /**
     * LAMBDA: This will call the interface on the Main.java and will return a LocalDateTime. This will also check if there is an appointment within 15 min.
     * @param url
     * @param resourceBundle
     */
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

        if (!ReferencedMethods.getHasInitialized()){

            //Lambda
            Main.mainInterface localTime = () -> LocalDateTime.now();

            if(ListModifications.CheckAppointmentIsNear(ReferencedMethods.getUserID(), localTime.passLocalDateTimeNow())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("You have an appointment within 15 minutes: AppointmentID " + ReferencedMethods.getNearAppointmentID() +
                        ", Start date and time " + ReferencedMethods.getNearAppointmentStartTime().toLocalDate() + " " +
                        ReferencedMethods.getNearAppointmentStartTime().toLocalTime());
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("You do not have any upcoming appointments");
                alert.showAndWait();
            }

            ReferencedMethods.setHasInitialized(true);


        }
    }

}
