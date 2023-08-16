package Application;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AppointmentInfoController implements Initializable {
    public TextField appointmentIDText;
    public TextField title;
    public TextField description;
    public ComboBox contact;
    public DatePicker appointmentDate;
    public ComboBox customerID;
    public Button cancel;
    public ComboBox startTime;
    public ComboBox endTime;
    public Button confirm;
    public TextField locationText;
    public ComboBox userID;

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

    public void confirmOnClicked(ActionEvent actionEvent) throws IOException {
        ListModifications.clearAppointments();

        if (ReferencedMethods.getFormState().equals("add")) {
            if (!title.getText().isEmpty() && !description.getText().isEmpty() && !locationText.getText().isEmpty() &&
                    !contact.getSelectionModel().isEmpty() && appointmentDate.getValue() != null && !startTime.getSelectionModel().isEmpty() &&
                    !endTime.getSelectionModel().isEmpty() && !customerID.getSelectionModel().isEmpty() && !userID.getSelectionModel().isEmpty()) {

                new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/MainScreen.fxml", 1600, 800);

                ListModifications.clearCustomerNames();
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The Appointment did not add properly. Please validate all the fields have been populated correctly.");
                alert.showAndWait();
            }
        }


        if (ReferencedMethods.getFormState().equals("modify")) {
            if (!title.getText().isEmpty() && !description.getText().isEmpty() && !locationText.getText().isEmpty() &&
                    !contact.getSelectionModel().isEmpty() && appointmentDate.getValue() != null && !startTime.getSelectionModel().isEmpty() &&
                    !endTime.getSelectionModel().isEmpty() && !customerID.getSelectionModel().isEmpty() && !userID.getSelectionModel().isEmpty()) {

                new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/MainScreen.fxml", 1600, 800);

                ListModifications.clearCustomerNames();
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
        customerID.setItems(ListModifications.getAllCustomerNames());

        userID.setItems(ListModifications.getAllUsers());

        if (ReferencedMethods.getFormState().equals("modify")){
            Appointments appointment = ReferencedMethods.getSelectedAppointment();

            appointmentIDText.setText(String.valueOf(appointment.appointmentID));
            title.setText(appointment.title);
            description.setText(appointment.description);
            locationText.setText(appointment.location);
            try {
                contact.setValue(sqlCommands.convertContactID(appointment.contactID));
                userID.setValue(sqlCommands.convertUserID(appointment.userID));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            appointmentDate.setValue(appointment.start.toLocalDateTime().toLocalDate());
            startTime.setValue(appointment.start.toLocalDateTime().toLocalTime());
            endTime.setValue(appointment.end.toLocalDateTime().toLocalTime());

            customerID.setValue(ListModifications.convertCustomerID(appointment.customerID));






        }
    }
}
