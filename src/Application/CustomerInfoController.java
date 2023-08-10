package Application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    public void confirmOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/CustomerOverview.fxml", 700, 550);

    }

    public void cancelOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/CustomerOverview.fxml", 700, 550);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            sqlCommands.populateCountries();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        country.setItems(ListModifications.getAllCountries());

    }

    public void stateOnShowing(Event actionEvent) throws SQLException {

        if (country.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a country first.");
            alert.showAndWait();
        }else if (ListModifications.getAllDivisions().size() == 0){

            int countryID = sqlCommands.getCountryCode(country.getValue().toString());

            sqlCommands.populateDivisions(countryID);

            state.setItems(ListModifications.getAllDivisions());

        }

    }

    public void countryOnAction(ActionEvent actionEvent) {

        ListModifications.clearDivisions();

    }
}
