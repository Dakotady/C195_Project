package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListModifications {

    private static final ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    private static final ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    public static void clearAppointments(){
        allAppointments.clear();
    }

    public static void clearCustomers() {
        allCustomers.clear();
    }

    public static void addAppointment(Appointments newAppointment){

        allAppointments.add(newAppointment);

    }

    public static void addCustomer(Customers newCustomer){

        allCustomers.add(newCustomer);

    }

    public static ObservableList<Appointments> getAllAppointments(){

        return allAppointments;

    }

    public static ObservableList<Customers> getAllCustomers(){

        return allCustomers;

    }


}
