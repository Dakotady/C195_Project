package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.Objects;
import java.util.TimeZone;

public class ReferencedMethods {


    private static Customers selectedCustomer;
    private static Appointments selectedAppointment;
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



    public static void setSelectedAppointment(Appointments selected){
        selectedAppointment = selected;
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

    public static Appointments getSelectedAppointment(){
        return selectedAppointment;
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


    public static ZoneId getLocalTimeZone(){
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.toZoneId();
    }

    public static Timestamp getCurrentLocalTime(){
        Timestamp localTime = new Timestamp(System.currentTimeMillis());
        return localTime;
    }

    public static LocalDateTime localTimeConversion(Timestamp timestamp, ZoneId zoneId){

        ZonedDateTime zonedDateTime = timestamp.toLocalDateTime().atZone(zoneId);

        ZonedDateTime updatedTime = zonedDateTime.withZoneSameInstant(getLocalTimeZone());

        LocalDateTime currentTime = updatedTime.toLocalDateTime();

        return currentTime;
    }

    public static Timestamp ConvertToUTC(LocalDateTime localDateTime, ZoneId zoneID){

        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneID);

        ZonedDateTime updatedTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));

        Timestamp utcTime = Timestamp.valueOf(updatedTime.toLocalDateTime());

        return utcTime;
    }

    public static int minDayOfWeek(LocalDate localDate){

        int temp = localDate.getDayOfWeek().getValue();
        int value = 0;

        if (temp == 7){
            value = 0;
        }else if (temp == 6){
            value = 6;
        }else if (temp == 5){
            value = 5;
        }else if (temp == 4){
            value = 4;
        }else if (temp == 3){
            value = 3;
        }else if (temp == 2){
            value = 2;
        }else if (temp == 1){
            value = 1;
        }

        return value;
    }

    public static int maxDayOfWeek(LocalDate localDate){

        int temp = localDate.getDayOfWeek().getValue();
        int value = 0;

        if (temp == 7){
            value = 7;
        }else if (temp == 6){
            value = 1;
        }else if (temp == 5){
            value = 2;
        }else if (temp == 4){
            value = 3;
        }else if (temp == 3){
            value = 4;
        }else if (temp == 2){
            value = 5;
        }else if (temp == 1){
            value = 6;
        }

        return value;
    }

    public static Boolean checkForEstOutOfBussiness(LocalDateTime localDateTime){
        Boolean test = false;

        ZonedDateTime currentLocalTime = localDateTime.atZone(getLocalTimeZone());

        ZonedDateTime whenConvertToEst = currentLocalTime.withZoneSameInstant(ZoneId.of("America/New_York"));

        LocalDateTime value = whenConvertToEst.toLocalDateTime();

        if (value.toLocalTime().isBefore(LocalTime.parse("08:00")) || value.toLocalTime().isAfter(LocalTime.parse("22:00"))){

            test = true;
        }

        return test;
    }

    public static Boolean checkForCustomerOverlaps(LocalDateTime start, LocalDateTime end, int customerID){
        Boolean test = false;
        int testAppointmentID = -1;


        ZonedDateTime startZone = start.atZone(getLocalTimeZone());
        ZonedDateTime endZone = end.atZone(getLocalTimeZone());

        for (Appointments appointment : ListModifications.getAllAppointments()) {

            if (formState.equals("modify")) {
                Appointments currentAppointment = getSelectedAppointment();
                testAppointmentID = currentAppointment.appointmentID;
            }

            if (testAppointmentID > -1) {

                if (appointment.appointmentID != testAppointmentID) {
                    if (customerID == appointment.customerID) {

                        ZonedDateTime appointmentStartZone = appointment.start.toLocalDateTime().atZone(getLocalTimeZone());
                        ZonedDateTime appointmentEndZone = appointment.end.toLocalDateTime().atZone(getLocalTimeZone());

                        if ((appointmentStartZone.isBefore(startZone) && appointmentEndZone.isAfter(startZone)) || (
                                appointmentStartZone.isBefore(endZone) && appointmentEndZone.isAfter(endZone)) || (appointmentStartZone.isBefore(startZone) &&
                                appointmentEndZone.isAfter(endZone)) || (appointmentStartZone.isAfter(startZone) && appointmentEndZone.isBefore(endZone))
                                || appointmentStartZone.equals(startZone) || appointmentEndZone.equals(endZone)) {

                            test = true;
                        }
                    }
                }
            }else {

                if (customerID == appointment.customerID) {

                    ZonedDateTime appointmentStartZone = appointment.start.toLocalDateTime().atZone(getLocalTimeZone());
                    ZonedDateTime appointmentEndZone = appointment.end.toLocalDateTime().atZone(getLocalTimeZone());

                    if ((appointmentStartZone.isBefore(startZone) && appointmentEndZone.isAfter(startZone)) || (
                            appointmentStartZone.isBefore(endZone) && appointmentEndZone.isAfter(endZone)) || (appointmentStartZone.isBefore(startZone) &&
                            appointmentEndZone.isAfter(endZone)) || (appointmentStartZone.isAfter(startZone) && appointmentEndZone.isBefore(endZone))
                            || appointmentStartZone.equals(startZone) || appointmentEndZone.equals(endZone)) {

                        test = true;
                    }
                }

            }
        }

        return test;
    }

}
