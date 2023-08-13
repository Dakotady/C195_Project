package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.TimeZone;

public class ReferencedMethods {


    private static Customers selectedCustomer;
    private static String formState;
    private static int divisionID;
    private static String state;
    private static String country;
    private static int countryID;
    private static String username;
    private static int userID;

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

    public static Timestamp getCurrentLocalTime(){
        Timestamp localTime = new Timestamp(System.currentTimeMillis());
        return localTime;
    }


    public static void setSelectedCustomer(Customers selected){
        selectedCustomer = selected;
    }

    public static void setFormState(String state){
        formState = state;
    }

    public static void setDivisionID(int ID){
        divisionID = ID;
    }

    public static void setState(String value){
        state = value;
    }

    public static void setCountry(String value){
        country = value;
    }

    public static void setCountryID(int ID){
        countryID = ID;
    }

    public static Customers getSelectedCustomer(){
        return selectedCustomer;
    }

    public static String getFormState(){
        return formState;
    }

    public static int getDivisionID(){
        return divisionID;
    }

    public static String getState(){
        return state;
    }

    public static String getCountry(){
        return country;
    }

    public static int getCountryID(){
        return countryID;
    }


    public void newStage(ActionEvent actionEvent, String fxml, Integer width, Integer height) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene addPart = new Scene(root, width, height);
        stage.setTitle("");
        stage.setScene(addPart);

        if (fxml.equals("/FxmlScreens/MainScreen.fxml") || fxml.equals("/FxmlScreens/Reports.fxml")){
            stage.setFullScreen(true);
        }

        stage.show();

    }

    public static Boolean checkCustomerToAppointment(Customers customer){

        Boolean value = false;
        int customerID;

        customerID = customer.customerID;

        for (Appointments appointment : ListModifications.getAllAppointments()) {

            if (appointment.customerID == customerID) {
                value = true;
                break;
            }
        }

        return value;
    }


}
