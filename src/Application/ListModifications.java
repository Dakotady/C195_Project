package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListModifications {

    private static final ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    public static void clearAppointments(){
        allAppointments.clear();
    }

    public static void addAppointment(Appointments newAppointment){

        allAppointments.add(newAppointment);

    }

    public static ObservableList<Appointments> getAllAppointments(){

        return allAppointments;

    }


}
