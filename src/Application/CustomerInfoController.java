package Application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class controls the CustomerInfo.fxml file.
 */
public class CustomerInfoController implements Initializable {
    public TextField customerID;
    public TextField customerAddress;
    public TextField customerName;
    public TextField phoneNumber;
    public TextField postalCode;
    public ComboBox country;
    public ComboBox state;
    public Button confirm;
    public Button cancel;

    /**
     * This saves the new or modified customer information.
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     */
    public void confirmOnClicked(ActionEvent actionEvent) throws IOException, SQLException {

        if (ReferencedMethods.getFormState().equals("add")) {
            if ((!state.getSelectionModel().isEmpty() || !country.getSelectionModel().isEmpty())
                    && (!customerName.getText().isEmpty()) && !customerAddress.getText().isEmpty()  && !phoneNumber.getText().isEmpty()
                    && !postalCode.getText().isEmpty()) {

                int add = sqlCommands.addCustomer(customerName.getText(), customerAddress.getText(), postalCode.getText(), phoneNumber.getText(),
                        state.getValue().toString(), country.getValue().toString());

                if (add > 0) {
                    ListModifications.clearCustomers();
                    sqlCommands.populateCustomers();

                    new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/CustomerOverview.fxml", 700, 550);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The customer did not add properly. Please validate all the fields have been populated correctly.");
                alert.showAndWait();
            }
        }

        if (ReferencedMethods.getFormState().equals("modify")) {
            if ((!state.getSelectionModel().isEmpty() || !country.getSelectionModel().isEmpty())
                    && (!customerName.getText().isEmpty()) && !customerAddress.getText().isEmpty() && !phoneNumber.getText().isEmpty()
                    && !postalCode.getText().isEmpty()) {

                int update = sqlCommands.updateCustomer(Integer.parseInt(customerID.getText()), customerName.getText(), customerAddress.getText(),
                        postalCode.getText(), phoneNumber.getText(), state.getValue().toString(), country.getValue().toString());

                if (update > 0) {

                    ListModifications.clearCustomers();
                    sqlCommands.populateCustomers();

                    new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/CustomerOverview.fxml", 700, 550);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The customer did not update properly. Please validate all the fields have been populated correctly.");
                alert.showAndWait();
            }
        }

        ListModifications.clearCountries();

    }

    /**
     * This cancels the customer addition or modification.
     * @param actionEvent
     * @throws IOException
     */
    public void cancelOnClicked(ActionEvent actionEvent) throws IOException {

        boolean response;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you wish to exit without saving?");
        alert.setContentText("Choose Ok to continue or Cancel to continue.");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        response = result == ButtonType.OK;

        if (response) {

            ListModifications.clearCountries();
            new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/CustomerOverview.fxml", 700, 550);
        }

    }

    /**
     * This sets the division names depending on what country is selected.
     * @param actionEvent
     * @throws SQLException
     */
    public void stateOnShowing(Event actionEvent) throws SQLException {

        if (country.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a country first.");
            alert.showAndWait();
        } else if (ListModifications.getAllDivisions().size() == 0) {

            int countryID = sqlCommands.getCountryCode(country.getValue().toString());

            sqlCommands.populateDivisions(countryID);

            state.setItems(ListModifications.getAllDivisions());

        }

    }

    /**
     * This will clear the divisions if the country is changed.
     * @param actionEvent
     */
    public void countryOnAction(ActionEvent actionEvent) {

        ListModifications.clearDivisions();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Customers customer = ReferencedMethods.getSelectedCustomer();

        try {
            sqlCommands.populateCountries();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        country.setItems(ListModifications.getAllCountries());

        if (ReferencedMethods.getFormState().equals("modify")) {

            customerID.setText(String.valueOf(customer.getCustomerID()));
            customerName.setText(customer.getCustomerName());
            phoneNumber.setText(customer.getPhoneNum());
            customerAddress.setText(customer.getAddress());
            postalCode.setText(customer.getPostalCode());

            try {
                sqlCommands.convertDivisionID(customer.getDivisionID());
                country.setValue(ReferencedMethods.getCountry());
                state.setValue(ReferencedMethods.getState());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}

