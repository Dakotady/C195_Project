package Applicaiton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        primaryStage.setTitle("Appointment Manager");
        primaryStage.setScene(new Scene(root, 600, 265));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
