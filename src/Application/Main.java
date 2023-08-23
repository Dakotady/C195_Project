package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This class runs at the beginning of the application. The JavaDocs are located in a folder named JavaDocs located here: src/JavaDocs
 */
public class Main extends Application{

    /**
     * LAMBDA: This creates a Interface for the 2nd Lambda expression. The expression will return a LocalDateTime.
     */
    public interface mainInterface {

        LocalDateTime passLocalDateTimeNow();

    }

    /**
     * This opens loginScreen.fxml.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FxmlScreens/LoginScreen.fxml")));
        primaryStage.setTitle("Appointment Manager");
        primaryStage.setScene(new Scene(root, 325, 265));
        primaryStage.show();

    }

    /**
     * This is ran when the application if first ran. If the users pc is set to French it will set a ResourceBundle to convert the LoginScreen to French.
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {


        Locale French = new Locale("fr", "FR");

        //Locale.setDefault(French);



        if ((Locale.getDefault().toString()).equals("fr_FR")) {
            ResourceBundle rb = ResourceBundle.getBundle("Application/Lang", Locale.getDefault());

            System.out.println(rb.getString("Hello"));
        }

        System.out.println((Locale.getDefault().toString()));


        launch(args);
    }
}
