package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListModifications {

    private static final ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    private static final ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    private static final ObservableList<String> allCountries = FXCollections.observableArrayList();

    private static final ObservableList<String> allDivisions = FXCollections.observableArrayList();


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

    public static ObservableList<Appointments> getAllAppointments(){
        return allAppointments;
    }

    public static ObservableList<Customers> getAllCustomers(){
        return allCustomers;
    }

    public static ObservableList<String> getAllCountries(){
        return allCountries;
    }

    public static ObservableList<String> getAllDivisions() { return  allDivisions; }


}
