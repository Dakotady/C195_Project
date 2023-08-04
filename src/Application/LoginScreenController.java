package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {

    public Text zoneID;
    public Button exitApplication;
    public Button login;
    public Text userNameText;
    public Text passwordText;
    public Text timezoneText;
    public TextField UsernameTextField;
    public TextField PasswordTextField;

    public int userID;
    public String username;
    public String password;


    @FXML

    public void exitApplicationOnClicked() {
        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zoneID.setText(Main.getLocalUTC().getId().toString());

        if ((Locale.getDefault().toString()).equals("fr_FR")){

            ResourceBundle rb = ResourceBundle.getBundle("Application/Lang", Locale.getDefault());

            userNameText.setText(rb.getString("Username"));
            passwordText.setText(rb.getString("Password"));
            timezoneText.setText(rb.getString("Timezone"));
            login.setText(rb.getString("Login"));
            exitApplication.setText(rb.getString("Exit"));

        }
    }

    public void loginOnClicked(ActionEvent actionEvent) throws IOException, SQLException {

        Boolean error = false;
        int loginValidation;

        if (UsernameTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if ((Locale.getDefault().toString()).equals("fr_FR")){
                ResourceBundle rb = ResourceBundle.getBundle("Application/Lang", Locale.getDefault());
                alert.setContentText(rb.getString("Please") + " " + rb.getString("populate") + " " + rb.getString("a") + " " + rb.getString("Username") + " " + rb.getString("to") + " " + rb.getString("continue"));
            }
            else {
                alert.setContentText("Please populate a Username to continue.");
            }
            alert.showAndWait();
            error = true;
        }
        if (PasswordTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if ((Locale.getDefault().toString()).equals("fr_FR")){
                ResourceBundle rb = ResourceBundle.getBundle("Application/Lang", Locale.getDefault());
                alert.setContentText(rb.getString("Please") + " " + rb.getString("populate") + " " + rb.getString("a") + " " + rb.getString("Password") + " " + rb.getString("to") + " " + rb.getString("continue"));
            }
            else {
                alert.setContentText("Please populate a Password to continue.");
            }
            alert.showAndWait();
            error = true;
        }

        if (error.equals(false)){

            username = UsernameTextField.getText();
            password = PasswordTextField.getText();

            loginValidation = sqlCommands.LoginValidation(username, password);

            //System.out.println(loginValidation);

            //validates if the username/password is correct, and saves the userID for later
            if (loginValidation == -2){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                if ((Locale.getDefault().toString()).equals("fr_FR")){
                    ResourceBundle rb = ResourceBundle.getBundle("Application/Lang", Locale.getDefault());
                    alert.setContentText(rb.getString("Incorrect") + " " + rb.getString("Username"));
                }
                else {
                    alert.setContentText("Incorrect Username.");
                }
                alert.showAndWait();
                error = true;
            }
            else if (loginValidation == -1){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                if ((Locale.getDefault().toString()).equals("fr_FR")){
                    ResourceBundle rb = ResourceBundle.getBundle("Application/Lang", Locale.getDefault());
                    alert.setContentText(rb.getString("Incorrect") + " " + rb.getString("Password"));
                }
                else {
                    alert.setContentText("Incorrect Password.");
                }
                alert.showAndWait();
                error = true;
            }
            else{
                userID = loginValidation;
            }
        }

        // opens MainScreen if validation passes.
        if (error.equals(false)) {

            //sets users information
            Main.setUserInfo(username, userID);

            new ReferencedMethods().newStage(actionEvent, "/FxmlScreens/MainScreen.fxml", 1600, 800);

/*
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScreen.fxml")));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene addPart = new Scene(root, 1600, 800);
            stage.setTitle("");
            stage.setScene(addPart);
            stage.setFullScreen(true);
            stage.show();

 */
        }

    }
}
