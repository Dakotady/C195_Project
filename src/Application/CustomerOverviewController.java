package Application;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class CustomerOverviewController {
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
}
