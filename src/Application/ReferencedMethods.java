package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.*;
import java.util.Objects;
import java.util.TimeZone;

/**
 * This is a misc class that is used to store methods used various times in the application.
 */
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
    private static Boolean hasInitialized;
    private static int nearAppointmentID;
    private static LocalDateTime nearAppointmentStartTime;

    /**
     * This sets the nearAppointmentID.
     * @param appointmentID
     */
    public static void setNearAppointmentID(int appointmentID){
        nearAppointmentID = appointmentID;
    }

    /**
     * This gets the near appointmentID.
     * @return
     */
    public static int getNearAppointmentID(){
        return nearAppointmentID;
    }

    /**
     * This sets the nearAppointmentStartTime.
     * @param startTime
     */
    public static void setNearAppointmentStartTime(LocalDateTime startTime){
        nearAppointmentStartTime = startTime;
    }

    /**
     * This gets the nearAppointmentStartTime.
     * @return
     */
    public static LocalDateTime getNearAppointmentStartTime(){
        return nearAppointmentStartTime;
    }

    /**
     * This sets the application has been Initialized for the first time.
     * @param mode
     */
    public static void setHasInitialized(Boolean mode){
        hasInitialized = mode;
    }

    /**
     * this returns if the application has been Initialized before.
     * @return
     */
    public static Boolean getHasInitialized(){
        return hasInitialized;
    }

    /**
     * This sets the user login information.
     * @param Username
     * @param UserID
     */
    public static void setUserInfo(String Username, int UserID){

        username = Username;
        userID = UserID;

    }

    /**
     * This gets the username used to login.
     * @return
     */
    public static String getUserName(){

        return username;
    }

    /**
     * This gets the userID used to login.
     * @return
     */
    public static int getUserID(){

        return userID;
    }


    /**
     * This sets the Appointment that has been selected.
     * @param selected
     */
    public static void setSelectedAppointment(Appointments selected){
        selectedAppointment = selected;
    }

    /**
     * This sets the customer that has been selected.
     * @param selected
     */
    public static void setSelectedCustomer(Customers selected){
        selectedCustomer = selected;
    }

    /**
     * this sets if the info forms need to be used to add or modify either appointments or customers.
     * @param state
     */
    public static void setFormState(String state){
        formState = state;
    }

    /**
     * This sets the DivisionID.
     * @param ID
     */
    public static void setDivisionID(int ID){
        divisionID = ID;
    }

    /**
     * This sets the state/ Division Name.
     * @param value
     */
    public static void setState(String value){
        state = value;
    }

    /**
     * This sets the country.
     * @param value
     */
    public static void setCountry(String value){
        country = value;
    }

    /**
     * This sets the countryID.
     * @param ID
     */
    public static void setCountryID(int ID){
        countryID = ID;
    }

    /**
     * This gets the selected appointment.
     * @return
     */
    public static Appointments getSelectedAppointment(){
        return selectedAppointment;
    }

    /**
     * This gets the selected customer.
     * @return
     */
    public static Customers getSelectedCustomer(){
        return selectedCustomer;
    }

    /**
     * This gets the formState.
     * @return
     */
    public static String getFormState(){
        return formState;
    }

    /**
     * This gets the DivisionID.
     * @return
     */
    public static int getDivisionID(){
        return divisionID;
    }

    /**
     * This gets the DivisionName/ State.
     * @return
     */
    public static String getState(){
        return state;
    }

    /**
     * This gets the country.
     * @return
     */
    public static String getCountry(){
        return country;
    }

    /**
     * This gets the CountryID.
     * @return
     */
    public static int getCountryID(){
        return countryID;
    }


    /**
     * This creates a new stage for the different FXML files.
     * @param actionEvent
     * @param fxml
     * @param width
     * @param height
     * @throws IOException
     */
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

    /**
     * This checks if an customer is tied to an appointment.
     * @param customer
     * @return
     */
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

    /**
     * This returns the local users ZoneID.
     * @return
     */
    public static ZoneId getLocalTimeZone(){
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.toZoneId();
    }

    /**
     * This gets the users local time as a Timestamp.
     * @return
     */
    public static Timestamp getCurrentLocalTime(){
        Timestamp localTime = new Timestamp(System.currentTimeMillis());
        return localTime;
    }

    /**
     * This converts times at various ZoneIDs to the users current local time.
     * @param timestamp
     * @param zoneId
     * @return
     */
    public static LocalDateTime localTimeConversion(Timestamp timestamp, ZoneId zoneId){

        ZonedDateTime zonedDateTime = timestamp.toLocalDateTime().atZone(zoneId);

        ZonedDateTime updatedTime = zonedDateTime.withZoneSameInstant(getLocalTimeZone());

        LocalDateTime currentTime = updatedTime.toLocalDateTime();

        return currentTime;
    }

    /**
     * This converts times to UTC time to be stored in the database.
     * @param localDateTime
     * @param zoneID
     * @return
     */
    public static Timestamp ConvertToUTC(LocalDateTime localDateTime, ZoneId zoneID){

        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneID);

        ZonedDateTime updatedTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));

        Timestamp utcTime = Timestamp.valueOf(updatedTime.toLocalDateTime());

        return utcTime;
    }

    /**
     * This sets the offset for the beginning of the week.
     * @param localDate
     * @return
     */
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

    /**
     * This gets the end of the week.
     * @param localDate
     * @return
     */
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

    /**
     * This checks if the selected appointment time is outside of the Business hours.
     * @param localDateTime
     * @return
     */
    public static Boolean checkForEstOutOfBusiness(LocalDateTime localDateTime){
        Boolean test = false;

        ZonedDateTime currentLocalTime = localDateTime.atZone(getLocalTimeZone());

        ZonedDateTime whenConvertToEst = currentLocalTime.withZoneSameInstant(ZoneId.of("America/New_York"));

        LocalDateTime value = whenConvertToEst.toLocalDateTime();

        if (value.toLocalTime().isBefore(LocalTime.parse("08:00")) || value.toLocalTime().isAfter(LocalTime.parse("22:00"))){

            test = true;
        }

        return test;
    }

    /**
     * This checks if appointment times of matching customers overlap.
     * @param start
     * @param end
     * @param customerID
     * @return
     */
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
