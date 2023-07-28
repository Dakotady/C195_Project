package Application;

import Connections.JavaDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}