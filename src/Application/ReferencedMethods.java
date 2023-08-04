package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ReferencedMethods {

    public void newStage(ActionEvent actionEvent, String fxml, Integer width, Integer height) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene addPart = new Scene(root, width, height);
        stage.setTitle("");
        stage.setScene(addPart);

        if (fxml.equals("MainScreen.fxml") || fxml.equals("Reports.fxml")){
            stage.setFullScreen(true);
        }

        stage.show();

    }


}
