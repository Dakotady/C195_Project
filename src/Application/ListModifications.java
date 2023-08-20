package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ListModifications {

    private static final ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    private static final ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

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


    public static void clearAppointments(){
        allAppointments.clear();
    }

    public static void clearCustomers() {
        allCustomers.clear();
    }

    public static void clearCountries(){
        allCountries.clear();
    }

    public static void clearDivisions() { allDivisions.clear(); }

    public static void clearContacts() { allContacts.clear(); }

    public static void clearCustomerNames() { allCustomerNames.clear(); }

    public static void clearUsers() { allUsers.clear(); }

    public static void addAppointment(Appointments newAppointment){
        allAppointments.add(newAppointment);
    }

    public static void addCustomer(Customers newCustomer){
        allCustomers.add(newCustomer);
    }

    public static void addCountry(String country){
        allCountries.add(country);
    }

    public static void addDivisions(String division) { allDivisions.add(division); }

    public static void addContacts(String contact) { allContacts.add(contact); }

    public static void addCustomerName(String name) { allCustomerNames.add(name); }

    public static void addUsers(String user) { allUsers.add(user); }

    public static ObservableList<Appointments> getAllAppointments(){
        return allAppointments;
    }

    public static ObservableList<Customers> getAllCustomers(){
        return allCustomers;
    }

    public static ObservableList<String> getAllCountries(){
        return allCountries;
    }

    public static ObservableList<String> getAllDivisions() { return allDivisions; }

    public static ObservableList<String> getAllContacts() { return allContacts; }

    public static ObservableList<String> getAllCustomerNames() { return allCustomerNames; }

    public static ObservableList<String> getAllUsers() { return  allUsers; }

    public static ObservableList<LocalTime> getAllTimes() { return allTimes; }

    public static void populateCustomerNames(){

        for (Customers customer : getAllCustomers()) {

            addCustomerName(customer.customerName);
        }

    }

    public static String convertCustomerID(int ID){

        String value = null;

        for (Customers customer : getAllCustomers()) {

            if (customer.customerID == ID){

                value = customer.customerName;
            }
        }

        return value;
        }

    public static int convertCustomerName(String name){

        int value = 0;

        for (Customers customer : getAllCustomers()) {

            if (customer.customerName.equals(name)){
                value = customer.customerID;
            }
        }

        return value;
    }
}
