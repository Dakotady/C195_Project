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

               Appointments appointment = new Appointments(appointmentID, title, description, location, type, start, end, create, createdBy, lastUpdated, lastUpdatedBy, customerID, userID, contactID);

               ListModifications.addAppointment(appointment);

            }

        JavaDBC.closeConnection();

    }

    public static void populateCustomers() throws SQLException{

        JavaDBC.openConnection();

        String sql = "Select * From customers";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phoneNum = rs.getString("Phone");
            Timestamp create = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");

            Customers customer = new Customers(customerID, customerName, address, postalCode, phoneNum, create, createdBy, lastUpdated, lastUpdatedBy, divisionID);

            ListModifications.addCustomer(customer);
        }

        JavaDBC.closeConnection();

    }

    public static void populateCountries() throws SQLException {

        JavaDBC.openConnection();

        String sql = "Select Country From Countries";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            String country = rs.getString("Country");

            ListModifications.addCountry(country);
        }

        JavaDBC.closeConnection();

    }

    public static int getCountryCode(String country) throws SQLException {
        int value = 0;

        JavaDBC.openConnection();

        String sql = "Select Country_ID From countries Where Country = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setString(1, country);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            value = rs.getInt("Country_ID");
        }

        JavaDBC.closeConnection();

        return value;
    }

    public static void populateDivisions(int countryID) throws SQLException {

        JavaDBC.openConnection();

        String sql = "Select Division From first_level_divisions Where Country_ID = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            String division = rs.getString("Division");

            ListModifications.addDivisions(division);
        }

        JavaDBC.closeConnection();

    }

    public static void convertDivisionID(int divisionID) throws SQLException {

        JavaDBC.openConnection();

        String sql = "Select * From first_level_divisions Where Division_ID = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            ReferencedMethods.setState(rs.getString("Division"));
            ReferencedMethods.setCountryID(rs.getInt("Country_ID"));
        }

        sql = "Select * From countries Where Country_ID = ?";
        ps = JavaDBC.connection.prepareStatement(sql);
        ps.setInt(1, ReferencedMethods.getCountryID());
        rs = ps.executeQuery();

        while (rs.next()){

            ReferencedMethods.setCountry(rs.getString("Country"));
        }

        JavaDBC.closeConnection();
    }

    public static int getDivisionID(String state, String country) throws SQLException {

        int countryID = getCountryCode(country);
        int divisionID = 0;

        JavaDBC.openConnection();

        String sql = "Select * From first_level_divisions Where Country_ID = ? And Division = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setInt(1, countryID);
        ps.setString(2, state);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            divisionID = rs.getInt("Division_ID");
        }

        JavaDBC.closeConnection();

        return divisionID;
    }

    public static int updateCustomer(int customerID, String customerName, String address, String postalCode, String phoneNum, String state,
                                      String country) throws SQLException {

        int divisionID = getDivisionID(state, country);
        Timestamp time = ReferencedMethods.getCurrentLocalTime();
        String user = ReferencedMethods.getUserName();

        JavaDBC.openConnection();

        String sql = "Update customers Set Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ? " +
                ", Division_ID = ? Where Customer_ID = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNum);
        ps.setTimestamp(5, time);
        ps.setString(6, user);
        ps.setInt(7, divisionID);
        ps.setInt(8, customerID);

        int rowAffected = ps.executeUpdate();

        return rowAffected;
    }

}