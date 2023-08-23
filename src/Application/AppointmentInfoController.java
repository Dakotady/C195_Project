package Application;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * This class controls the AppointmentInfo.fxml file.
 */
public class AppointmentInfoController implements Initializable {
    public TextField appointmentIDText;
    public TextField title;
    public TextField description;
    public ComboBox contact;
    public DatePicker appointmentDate;
    public ComboBox customerName;
    public Button cancel;
    public ComboBox startTime;
    public ComboBox endTime;
    public Button confirm;
    public TextField location;
    public ComboBox userName;
    public TextField type;

    /**
     * This cancels the addition or modification of the appointment.
     * @param actionEvent
     * @throws IOException
     */
    public void cancelOnClicked(ActionEvent actionEvent) throws IOException {
        ListModifications.clearAppointments();

        boolean response;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you wish to exit without saving?");
        alert.setContentText("Choose Ok to continue or Cancel to continue.");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        response = result == ButtonType.OK;

        if (response) {

            new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/MainScreen.fxml", 1600, 800);
        }

        ListModifications.clearCustomerNames();

    }

    /**
     * This saves either the addition or modification of the appointment.
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     */
    public void confirmOnClicked(ActionEvent actionEvent) throws IOException, SQLException {

        if (ReferencedMethods.getFormState().equals("add")) {
            if (!title.getText().isEmpty() && !description.getText().isEmpty() && !location.getText().isEmpty() && !type.getText().isEmpty() &&
                    !contact.getSelectionModel().isEmpty() && appointmentDate.getValue() != null && !startTime.getSelectionModel().isEmpty() &&
                    !endTime.getSelectionModel().isEmpty() && !customerName.getSelectionModel().isEmpty() && !userName.getSelectionModel().isEmpty()) {

                LocalDateTime startCheck = LocalDateTime.of(appointmentDate.getValue(), LocalTime.parse(startTime.getValue().toString()));
                LocalDateTime endCheck = LocalDateTime.of(appointmentDate.getValue(), LocalTime.parse(endTime.getValue().toString()));
                int customerID = ListModifications.convertCustomerName(customerName.getValue().toString());

                if (ReferencedMethods.checkForEstOutOfBusiness(startCheck)) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The start time needs to be between 08:00 and 22:00.");
                    alert.showAndWait();
                } else if (ReferencedMethods.checkForEstOutOfBusiness(endCheck)) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The end time needs to be between 08:00 and 22:00.");
                    alert.showAndWait();
                } else if (ReferencedMethods.checkForCustomerOverlaps(startCheck, endCheck, customerID)) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The current customer has an overlapping appointment.");
                    alert.showAndWait();
                } else {

                    int rowsAffected = sqlCommands.addAppointment(title.getText().toString(),
                            description.getText().toString(), location.getText().toString(), type.getText().toString(),
                            contact.getValue().toString(), startCheck, endCheck, customerName.getValue().toString(), userName.getValue().toString());

                    if (rowsAffected > 0) {

                        ListModifications.clearAppointments();
                        ListModifications.clearCustomerNames();
                        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/MainScreen.fxml", 1600, 800);
                    } else {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The Appointment did not add properly. Please validate all the fields have been populated correctly.");
                        alert.showAndWait();
                    }
                }
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The Appointment did not add properly. Please validate all the fields have been populated correctly.");
                alert.showAndWait();
            }
        }


        if (ReferencedMethods.getFormState().equals("modify")) {
            if (!title.getText().isEmpty() && !description.getText().isEmpty() && !location.getText().isEmpty() && !type.getText().isEmpty() &&
                    !contact.getSelectionModel().isEmpty() && appointmentDate.getValue() != null && !startTime.getSelectionModel().isEmpty() &&
                    !endTime.getSelectionModel().isEmpty() && !customerName.getSelectionModel().isEmpty() && !userName.getSelectionModel().isEmpty()) {

                LocalDateTime startCheck = LocalDateTime.of(appointmentDate.getValue(), LocalTime.parse(startTime.getValue().toString()));
                LocalDateTime endCheck = LocalDateTime.of(appointmentDate.getValue(), LocalTime.parse(endTime.getValue().toString()));
                int customerID = ListModifications.convertCustomerName(customerName.getValue().toString());

                if (ReferencedMethods.checkForEstOutOfBusiness(startCheck)) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The start time needs to be between 08:00 and 22:00.");
                    alert.showAndWait();
                } else if (ReferencedMethods.checkForEstOutOfBusiness(endCheck)) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The end time needs to be between 08:00 and 22:00.");
                    alert.showAndWait();
                } else if (ReferencedMethods.checkForCustomerOverlaps(startCheck, endCheck, customerID)) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The current customer has an overlapping appointment.");
                    alert.showAndWait();
                } else {

                    Appointments appointment = ReferencedMethods.getSelectedAppointment();

                    int rowsAffected = sqlCommands.updateAppointment(appointment.appointmentID, title.getText().toString(),
                            description.getText().toString(), location.getText().toString(), type.getText().toString(),
                            contact.getValue().toString(), startCheck, endCheck, customerName.getValue().toString(), userName.getValue().toString());

                    if (rowsAffected > 0) {

                        ListModifications.clearCustomerNames();
                        ListModifications.clearAppointments();
                        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/MainScreen.fxml", 1600, 800);
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The Appointment did not update properly. Please validate all the fields have been populated correctly.");
                        alert.showAndWait();

                    }
                }
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The Appointment did not update properly. Please validate all the fields have been populated correctly.");
                alert.showAndWait();
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ListModifications.clearContacts();
        ListModifications.clearUsers();
        try {
            sqlCommands.populateContacts();
            sqlCommands.populateUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ListModifications.populateCustomerNames();

        contact.setItems(ListModifications.getAllContacts());
        customerName.setItems(ListModifications.getAllCustomerNames());

        userName.setItems(ListModifications.getAllUsers());

        startTime.setItems(ListModifications.getAllTimes());
        endTime.setItems(ListModifications.getAllTimes());

        if (ReferencedMethods.getFormState().equals("modify")){
            Appointments appointment = ReferencedMethods.getSelectedAppointment();

            appointmentIDText.setText(String.valueOf(appointment.appointmentID));
            title.setText(appointment.title);
            description.setText(appointment.description);
            location.setText(appointment.location);
            type.setText((appointment.type));
            try {
                contact.setValue(sqlCommands.convertContactID(appointment.contactID));
                userName.setValue(sqlCommands.convertUserID(appointment.userID));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            appointmentDate.setValue(appointment.start.toLocalDateTime().toLocalDate());
            startTime.setValue(appointment.start.toLocalDateTime().toLocalTime());
            endTime.setValue(appointment.end.toLocalDateTime().toLocalTime());

            customerName.setValue(ListModifications.convertCustomerID(appointment.customerID));






        }
    }
}
