package Application;

import Connections.JavaDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class sqlCommands {

    public static int LoginValidation(String Username, String Password) throws SQLException {

        int loginTest = 0;
        int usernameTest = 0;
        int value;

        String sql = "SELECT * From User Where User_Name = ? And Password = ?";
        PreparedStatement ps = JavaDBC.connection.prepareStatement(sql);
        ps.setString(1, Username);
        ps.setString(2, Password);
        ResultSet rs = ps.executeQuery();

        if (rs.getFetchSize() > 0) {
            loginTest = 1;
        }

        if (loginTest != 1){
            sql = "SELECT * From User Where User_Name = ?";
            PreparedStatement ps1 = JavaDBC.connection.prepareStatement(sql);
            ps.setString(1, Username);
            ResultSet rs1 = ps1.executeQuery();

            if(rs1.getFetchSize() > 0){
                usernameTest = 1;
            }
        }

        if(loginTest == 1){
            value = 1;
        }
        else if (usernameTest == 0){
            value = 2;
        }
        else {
            value = 3;
        }

        return value;
    }
}