package Application;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    public void backOnClicked(ActionEvent actionEvent) throws IOException {

        ListModifications.clearAppointments();

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/MainScreen.fxml", 1600, 800);
    }

    public void addCustomerOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/CustomerInfo.fxml", 600, 300);
    }

    public void modifyCustomerOnClicked(ActionEvent actionEvent) throws IOException {

        new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/CustomerInfo.fxml", 600, 300);
    }

    public void deleteCustomerOnClicked(ActionEvent actionEvent) {

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
