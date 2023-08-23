package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This class contains all of the ObservableLists that are referenced in various fxml files.
 */
public class ListModifications {

    private static final ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    private static final ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    private static final ObservableList<LoginActivity> allLoginActivity = FXCollections.observableArrayList();

    private static final ObservableList<String> allCountries = FXCollections.observableArrayList();

    private static final ObservableList<String> allDivisions = FXCollections.observableArrayList();

    private static final ObservableList<String> allContacts = FXCollections.observableArrayList();

    private static final ObservableList<String> allCustomerNames = FXCollections.observableArrayList();

    private static final ObservableList<String> allUsers = FXCollections.observableArrayList();

    private static final ObservableList<LocalTime> allTimes = FXCollections.observableArrayList(LocalTime.parse("00:00"), LocalTime.parse("00:30"),
    LocalTime.parse("01:00"), LocalTime.parse("01:30"), LocalTime.parse("02:00"), LocalTime.parse("02:30"), LocalTime.parse("03:00"),
            LocalTime.parse("03:30"), LocalTime.parse("04:00"), LocalTime.parse("04:30"), LocalTime.parse("05:00"), LocalTime.parse("05:30"),
            LocalTime.parse("06:00"), LocalTime.parse("06:30"), LocalTime.parse("07:00"), LocalTime.parse("07:30"), LocalTime.parse("08:00"),
            LocalTime.parse("08:30"), LocalTime.parse("09:00"), LocalTime.parse("09:30"), LocalTime.parse("10:00"), LocalTime.parse("10:30"),
            LocalTime.parse("11:00"), LocalTime.parse("11:30"), LocalTime.parse("12:00"), LocalTime.parse("12:30"), LocalTime.parse("13:00"),
            LocalTime.parse("13:30"), LocalTime.parse("14:00"), LocalTime.parse("14:30"), LocalTime.parse("15:00"), LocalTime.parse("15:30"),
            LocalTime.parse("16:00"), LocalTime.parse("16:30"), LocalTime.parse("17:00"), LocalTime.parse("17:30"), LocalTime.parse("18:00"),
            LocalTime.parse("18:30"), LocalTime.parse("19:00"), LocalTime.parse("19:30"), LocalTime.parse("20:00"), LocalTime.parse("20:30"),
            LocalTime.parse("21:00"), LocalTime.parse("21:30"), LocalTime.parse("22:00"), LocalTime.parse("22:30"), LocalTime.parse("23:00"),
            LocalTime.parse("23:30"));


    /**
     * This will clear allAppointments.
     */
    public static void clearAppointments(){
        allAppointments.clear();
    }

    /**
     * This will clear allCustomers.
     */
    public static void clearCustomers() {
        allCustomers.clear();
    }

    /**
     * This clears allLoginActivity.
     */
    public static void clearLoginActivity() { allLoginActivity.clear(); }

    /**
     * This clears allCountries.
     */
    public static void clearCountries(){
        allCountries.clear();
    }

    /**
     * This clears allDivisions.
     */
    public static void clearDivisions() { allDivisions.clear(); }

    /**
     * This clears allContacts.
     */
    public static void clearContacts() { allContacts.clear(); }

    /**
     * This clears allCustomerNames.
     */
    public static void clearCustomerNames() { allCustomerNames.clear(); }

    /**
     * This clears allUsers.
     */
    public static void clearUsers() { allUsers.clear(); }

    /**
     * This adds a new appointment.
     * @param newAppointment
     */
    public static void addAppointment(Appointments newAppointment){
        allAppointments.add(newAppointment);
    }

    /**
     * This adds a new customer.
     * @param newCustomer
     */
    public static void addCustomer(Customers newCustomer){
        allCustomers.add(newCustomer);
    }

    /**
     * This adds a new loginActivity.
     * @param newLogin
     */
    public static void addLoginActivity(LoginActivity newLogin){
        allLoginActivity.add(newLogin);
    }

    /**
     * This adds a new country.
     * @param country
     */
    public static void addCountry(String country){
        allCountries.add(country);
    }

    /**
     * This adds a new division.
     * @param division
     */
    public static void addDivisions(String division) { allDivisions.add(division); }

    /**
     * This adds a new contact.
     * @param contact
     */
    public static void addContacts(String contact) { allContacts.add(contact); }

    /**
     * This adds a new customerName.
     * @param name
     */
    public static void addCustomerName(String name) { allCustomerNames.add(name); }

    /**
     * This adds a new user.
     * @param user
     */
    public static void addUsers(String user) { allUsers.add(user); }

    /**
     * This gets allAppointments.
     * @return
     */
    public static ObservableList<Appointments> getAllAppointments(){
        return allAppointments;
    }

    /**
     * This gets allCustomers.
     * @return
     */
    public static ObservableList<Customers> getAllCustomers(){
        return allCustomers;
    }

    /**
     * This gets allLoginActivity.
     * @return
     */
    public static ObservableList<LoginActivity> getAllLoginActivity() { return allLoginActivity; }

    /**
     * This gets allCountries.
     * @return
     */
    public static ObservableList<String> getAllCountries(){
        return allCountries;
    }

    /**
     * This gets allDivisions.
     * @return
     */
    public static ObservableList<String> getAllDivisions() { return allDivisions; }

    /**
     * This gets allContacts.
     * @return
     */
    public static ObservableList<String> getAllContacts() { return allContacts; }

    /**
     * This gets allCustomerNames.
     * @return
     */
    public static ObservableList<String> getAllCustomerNames() { return allCustomerNames; }

    /**
     * This gets allUsers.
     * @return
     */
    public static ObservableList<String> getAllUsers() { return  allUsers; }

    /**
     * This gets allTimes.
     * @return
     */
    public static ObservableList<LocalTime> getAllTimes() { return allTimes; }

    /**
     * This populates a customerName.
     */
    public static void populateCustomerNames(){

        for (Customers customer : getAllCustomers()) {

            addCustomerName(customer.customerName);
        }

    }

    /**
     * This returns a customerName form a CustomerID.
     * @param ID
     * @return
     */
    public static String convertCustomerID(int ID){

        String value = null;

        for (Customers customer : getAllCustomers()) {

            if (customer.customerID == ID){

                value = customer.customerName;
            }
        }

        return value;
        }

    /**
     * This returns the CustomerID from the CustomerName.
     * @param name
     * @return
     */
    public static int convertCustomerName(String name){

        int value = 0;

        for (Customers customer : getAllCustomers()) {

            if (customer.customerName.equals(name)){
                value = customer.customerID;
            }
        }

        return value;
    }

    /**
     * This checks if the appointment time is within 15 min for the current user.
     * @param userID
     * @param localDateTime
     * @return
     */
    public static boolean CheckAppointmentIsNear(int userID, LocalDateTime localDateTime){

        Boolean value = false;

        for (Appointments appointment : allAppointments) {

            LocalTime appointmentLocalTime = appointment.start.toLocalDateTime().toLocalTime();

            if (appointment.userID == userID && localDateTime.toLocalDate().isEqual(appointment.start.toLocalDateTime().toLocalDate())){

                if (appointmentLocalTime.equals(localDateTime.toLocalTime()) || (appointmentLocalTime.isAfter(localDateTime.toLocalTime()) &&
                        appointmentLocalTime.isBefore(localDateTime.toLocalTime().plusMinutes(15))) ||
                        appointmentLocalTime.equals(localDateTime.toLocalTime().plusMinutes(15))){

                    ReferencedMethods.setNearAppointmentID(appointment.appointmentID);
                    ReferencedMethods.setNearAppointmentStartTime(appointment.start.toLocalDateTime());
                    value = true;
                }


            }
        }

        return value;
    }
}
