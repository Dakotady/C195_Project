package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class Main extends Application implements Initializable {

    public static ZoneId getLocalUTC(){
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.toZoneId();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        primaryStage.setTitle("Appointment Manager");
        primaryStage.setScene(new Scene(root, 325, 265));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("This worked.");
    }
}
