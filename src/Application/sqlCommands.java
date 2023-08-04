package Application;

import Connections.JavaDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public abstract class sqlCommands {

    public static int LoginValidation(String Username, String Password) throws SQLException {

        JavaDBC.openConnection();

        int loginTest = 0;
        int usernameTest = 0;
        int value = 0;

        //This checks if the if the users login information is correct and if so returns the users ID.

        String sql = "SELECT * From users Where User_Name = ? And Password = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setString(1, Username);
        ps.setString(2, Password);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            String ID = rs.getString("User_ID");
            String username = rs.getString("User_Name");
            String password = rs.getString("Password");
            value = Integer.parseInt(ID);

            if (!username.equals(Username)){
                value = -2;
            }
            else if (!password.equals(Password)){
                value = -1;
            }

            System.out.println(ID + " " + username + password);
        }

        //This checks if the Username exist in the users table, and if it does match then the password is incorrect.
        if (value == 0){

            sql = "SELECT * From users Where User_Name = ?";
            PreparedStatement ps1 = JavaDBC.connection.prepareStatement(sql);
            ps1.setString(1, Username);
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()){
                String username = rs1.getString("User_Name");
                value = -1;
            }
        }

        // this is returned if the username does not exist.
        if (value == 0){

            value = -2;

        }

        JavaDBC.closeConnection();

        return value;
    }

    public static void populateAppointments() throws SQLException {

        JavaDBC.openConnection();

        String sql = "Select * From appointments ";
            PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {



                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Timestamp create = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdated = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                System.out.println(appointmentID);
                System.out.println(title);
                System.out.println(description);
                System.out.println(location);
                System.out.println(type);
                System.out.println(start);
                System.out.println(end);
                System.out.println(create);
                System.out.println(createdBy);
                System.out.println(lastUpdated);
                System.out.println(lastUpdatedBy);
                System.out.println(customerID);
                System.out.println(userID);
                System.out.println(contactID);

               Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, create, createdBy, lastUpdated, lastUpdatedBy, customerID, userID, contactID);

               ListModifications.addAppointment(appointments);

            }

        JavaDBC.closeConnection();

    }
}