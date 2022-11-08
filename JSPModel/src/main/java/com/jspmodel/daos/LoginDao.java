package com.jspmodel.daos;

import java.sql.*;

public class LoginDao {
    public static boolean validate(String name, String pass, int q){
        boolean status = false;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.printf("Driver Registered");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://MSI\\MSSQLSERVER;databaseName=barberShop;encrypt=true;trustServerCertificate=true;","sa", "sa");
            System.out.printf("Connection successfully");

            PreparedStatement ps = con.prepareStatement("");
            System.out.printf(name + " " + pass);
            ps.setString(1, name);
            ps.setString(2,pass);
            ps.setInt(3, q);


            //ResultSet rs = ps.executeQuery();
            //status = rs.next();
            //System.out.printf(String.valueOf(status));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args) {
        System.out.printf(String.valueOf(validate("Hieu", "66866686", 1)));
    }
}
