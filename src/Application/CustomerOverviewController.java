package Application;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class controls the CustomerOverview.fxml file.
 */
public class CustomerOverviewController implements Initializable {
    public TableView customerTable;
    public TableColumn customerID_Col;
    public TableColumn name_Col;
    public TableColumn phone_Col;
    public TableColumn address_Col;
    public TableColumn postalCode_Col;
    public TableColumn divisionID_Col;
    public Button addCustomer;
    public Button modifyCustomer;
    public Button deleteCustomer;
    public Button back;

    /**
     * This goes back to the MainScreen.fxml file.
     * @param actionEvent
     * @throws IOException
     */
    public void backOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/MainScreen.fxml", 1600, 800);
    }

    /**
     * This opens the CustomerInfo.fxml file, and sets the FormState to add.
     * @param actionEvent
     * @throws IOException
     */
    public void addCustomerOnClicked(ActionEvent actionEvent) throws IOException {

        ReferencedMethods.setFormState("add");
        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/CustomerInfo.fxml", 600, 300);
    }

    /**
     * This opens the CustomerInfo.fxml file, and gets the selected customer.
     * @param actionEvent
     * @throws IOException
     */
    public void modifyCustomerOnClicked(ActionEvent actionEvent) throws IOException {

        Customers selected = (Customers) customerTable.getSelectionModel().getSelectedItem();
        ReferencedMethods.setSelectedCustomer(selected);

        if (selected == null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No customer has been selected. Please select a customer to modify.");
            alert.showAndWait();
        } else {

            ReferencedMethods.setFormState("modify");
            new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/CustomerInfo.fxml", 600, 300);
        }


    }

    /**
     * This deletes the customer if it has no appointments.
     * @param actionEvent
     * @throws SQLException
     */
    public void deleteCustomerOnClicked(ActionEvent actionEvent) throws SQLException {

        Boolean customerExist;

        Customers selected = (Customers) customerTable.getSelectionModel().getSelectedItem();
        ReferencedMethods.setSelectedCustomer(selected);

        customerExist = ReferencedMethods.checkCustomerToAppointment(selected);

        if (customerExist){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The customer cannot be deleted because it is tied to a appointment");
            alert.showAndWait();
        }else {

            Boolean response;


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("are you sure you want to delete the customer " + selected.customerName);
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
            response = result == ButtonType.OK;

            if (response) {
                sqlCommands.deleteCustomer(selected.customerID);
                ListModifications.getAllCustomers().remove(selected);
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        customerID_Col.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("customerID"));
        name_Col.setCellValueFactory(new PropertyValueFactory<Customers, String>("customerName"));
        phone_Col.setCellValueFactory(new PropertyValueFactory<Customers, String>("phoneNum"));
        address_Col.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));
        postalCode_Col.setCellValueFactory(new PropertyValueFactory<Customers, String>("postalCode"));
        divisionID_Col.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("divisionID"));
        customerTable.setItems(ListModifications.getAllCustomers());


    }
}
