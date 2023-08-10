package Application;

import Connections.JavaDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class Main extends Application implements Initializable {

    public static String username;
    public static int userID;

    public static void setUserInfo(String Username, int UserID){

        username = Username;
        userID = UserID;

    }

    public static String getUserName(){

        return username;
    }

    public static int getUserID(){

        return userID;
    }


    public static ZoneId getLocalUTC(){
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.toZoneId();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FxmlScreens/LoginScreen.fxml")));
        primaryStage.setTitle("Appointment Manager");
        primaryStage.setScene(new Scene(root, 325, 265));
        primaryStage.show();

    }


    public static void main(String[] args) {


        Locale French = new Locale("fr", "FR");

        //Locale.setDefault(French);



        if ((Locale.getDefault().toString()).equals("fr_FR")) {
            ResourceBundle rb = ResourceBundle.getBundle("Application/Lang", Locale.getDefault());

            System.out.println(rb.getString("Hello"));
        }

        System.out.println((Locale.getDefault().toString()));


        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}
