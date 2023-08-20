package Application;

import Connections.JavaDBC;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
                Timestamp startTemp = rs.getTimestamp("Start");
                Timestamp endTemp = rs.getTimestamp("End");
                Timestamp createTemp = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdatedTemp = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Timestamp start = Timestamp.valueOf(ReferencedMethods.localTimeConversion(startTemp, ZoneId.of("UTC")));
                Timestamp end = Timestamp.valueOf(ReferencedMethods.localTimeConversion(endTemp, ZoneId.of("UTC")));
                Timestamp create = Timestamp.valueOf(ReferencedMethods.localTimeConversion(createTemp, ZoneId.of("UTC")));
                Timestamp lastUpdated = Timestamp.valueOf(ReferencedMethods.localTimeConversion(lastUpdatedTemp, ZoneId.of("UTC")));


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
            Timestamp createTemp = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdatedTemp = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");

            Timestamp create = Timestamp.valueOf(ReferencedMethods.localTimeConversion(createTemp, ZoneId.of("UTC")));
            Timestamp lastUpdated = Timestamp.valueOf(ReferencedMethods.localTimeConversion(lastUpdatedTemp, ZoneId.of("UTC")));

            Customers customer = new Customers(customerID, customerName, address, postalCode, phoneNum, create, createdBy, lastUpdated,
                    lastUpdatedBy, divisionID);

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

    public static void populateContacts() throws SQLException {

        JavaDBC.openConnection();

        String sql = "Select * From contacts";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            String contactName = rs.getString("Contact_Name");

            ListModifications.addContacts(contactName);
        }

        JavaDBC.closeConnection();

    }

    public static String convertContactID(int ID) throws SQLException {

        JavaDBC.openConnection();
        String contactName = null;

        String sql = "Select * From contacts where Contact_ID = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            contactName = rs.getString("Contact_Name");

        }

        JavaDBC.closeConnection();

        return contactName;
    }

    public static int getContactID(String contactName) throws SQLException {

        JavaDBC.openConnection();
        int contactID = 0;

        String sql = "Select * From contacts where Contact_Name = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setString(1, contactName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            contactID = rs.getInt("Contact_ID");

        }

        JavaDBC.closeConnection();

        return contactID;
    }

    public static void populateUsers() throws SQLException {

        JavaDBC.openConnection();

        String sql = "Select * From users";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            String userName = rs.getString("User_Name");

            ListModifications.addUsers(userName);
        }

        JavaDBC.closeConnection();

    }

    public static String convertUserID(int ID) throws SQLException {

        JavaDBC.openConnection();
        String userName = null;

        String sql = "Select * From users where User_ID = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            userName = rs.getString("User_Name");

        }

        JavaDBC.closeConnection();

        return userName;
    }

    public static int getUserID(String userName) throws SQLException {

        JavaDBC.openConnection();
        int userID = 0;

        String sql = "Select * From users where User_Name = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            userID = rs.getInt("User_ID");

        }

        JavaDBC.closeConnection();

        return userID;
    }

    public static int updateAppointment(int appointmentID, String title, String description, String location, String type, String contactName,
                                        LocalDateTime appointmentStart, LocalDateTime appointmentEnd, String customerName, String userName)
                                        throws SQLException {

        int contactID = getContactID(contactName);
        int customerID = ListModifications.convertCustomerName(customerName);
        int userID = getUserID(userName);

        Timestamp startAsUTC = ReferencedMethods.ConvertToUTC(appointmentStart, ReferencedMethods.getLocalTimeZone());
        Timestamp endAsUTC = ReferencedMethods.ConvertToUTC(appointmentEnd, ReferencedMethods.getLocalTimeZone());
        Timestamp lastUpdateTime = ReferencedMethods.ConvertToUTC(LocalDateTime.now(), ReferencedMethods.getLocalTimeZone());

        String lastUpdatedBy = ReferencedMethods.getUserName();

        JavaDBC.openConnection();

        String sql = "Update appointments Set Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?," +
                " Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? Where Appointment_ID = ?";

        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, startAsUTC);
        ps.setTimestamp(6, endAsUTC);
        ps.setTimestamp(7, lastUpdateTime);
        ps.setString(8, lastUpdatedBy);
        ps.setInt(9, customerID);
        ps.setInt(10, userID);
        ps.setInt(11, contactID);
        ps.setInt(12, appointmentID);

        int affectedRows = ps.executeUpdate();
        JavaDBC.closeConnection();

        return affectedRows;
    }

    public static int updateCustomer(int customerID, String customerName, String address, String postalCode, String phoneNum, String state,
                                      String country) throws SQLException {

        int divisionID = getDivisionID(state, country);
        Timestamp time = ReferencedMethods.ConvertToUTC(ReferencedMethods.getCurrentLocalTime().toLocalDateTime(),ReferencedMethods.getLocalTimeZone());
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

        int affectedRows = ps.executeUpdate();
        JavaDBC.closeConnection();

        return affectedRows;
    }

    public static int addAppointment(String title, String description, String location, String type, String contactName,
                                     LocalDateTime appointmentStart, LocalDateTime appointmentEnd, String customerName, String userName)
                                     throws SQLException {

        int contactID = getContactID(contactName);
        int customerID = ListModifications.convertCustomerName(customerName);
        int userID = getUserID(userName);

        Timestamp startAsUTC = ReferencedMethods.ConvertToUTC(appointmentStart, ReferencedMethods.getLocalTimeZone());
        Timestamp endAsUTC = ReferencedMethods.ConvertToUTC(appointmentEnd, ReferencedMethods.getLocalTimeZone());
        Timestamp lastUpdateTime = ReferencedMethods.ConvertToUTC(LocalDateTime.now(), ReferencedMethods.getLocalTimeZone());

        String lastUpdatedBy = ReferencedMethods.getUserName();

        JavaDBC.openConnection();

        String sql = "Insert into appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, " +
                "Customer_ID, User_ID, Contact_ID) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, startAsUTC);
        ps.setTimestamp(6, endAsUTC);
        ps.setTimestamp(7, lastUpdateTime);
        ps.setString(8, lastUpdatedBy);
        ps.setTimestamp(9, lastUpdateTime);
        ps.setString(10, lastUpdatedBy);
        ps.setInt(11, customerID);
        ps.setInt(12, userID);
        ps.setInt(13, contactID);

        int affectedRows = ps.executeUpdate();

        JavaDBC.closeConnection();

        return affectedRows;
    }

    public static int addCustomer(String customerName, String address, String postalCode, String phoneNum, String state,
                                  String country) throws SQLException{

        int divisionID = getDivisionID(state, country);
        Timestamp time = ReferencedMethods.ConvertToUTC(ReferencedMethods.getCurrentLocalTime().toLocalDateTime(),ReferencedMethods.getLocalTimeZone());
        String user = ReferencedMethods.getUserName();

        JavaDBC.openConnection();

        String sql = "Insert into customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                "Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNum);
        ps.setTimestamp(5, time);
        ps.setString(6, user);
        ps.setTimestamp(7, time);
        ps.setString(8, user);
        ps.setInt(9, divisionID);

        int affectedRows = ps.executeUpdate();
        JavaDBC.closeConnection();

        return affectedRows;
    }

    public static void deleteAppointment(int appointmentID) throws SQLException{

        JavaDBC.openConnection();

        String sql = "Delete From appointments Where Appointment_ID = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentID);
        int affectedRows = ps.executeUpdate();

        JavaDBC.closeConnection();

    }

    public static void deleteCustomer(int customerID) throws SQLException {

        JavaDBC.openConnection();

        String sql = "Delete From customers Where Customer_ID = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        int affectedRows = ps.executeUpdate();

        JavaDBC.closeConnection();

    }
}