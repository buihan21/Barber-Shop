package com.jspmodel.daos;

import com.jspmodel.db.DBConnect;
import com.jspmodel.models.User;

import java.sql.*;

//public class LoginDao {
//    public static boolean validate(String name, String pass){
//        boolean status = false;
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            System.out.println("Driver Registered");
//            Connection con = DriverManager.getConnection("jdbc:sqlserver://MSI\\MSSQLSERVER;databaseName=barberShop;encrypt=true;trustServerCertificate=true;","sa", "sa");
//            System.out.println("Connection successfully");
//
//            String sql = "SELECT role_id, user_name, pass_word" + " FROM [User] WHERE user_name = ? AND pass_word = ?";
//            PreparedStatement stm = con.prepareStatement(sql);
//            stm.setString(1, name);
//            stm.setString(2, pass);
//
//            ResultSet rs = stm.executeQuery();
//            status = rs.next();
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return status;
//    }

//    public static void main(String[] args) {
//        System.out.print(validate("NgocJr", "12092002"));
//    }
//}

public class LoginDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean checkLogin(String name, String pass){
        boolean status = false;
        try {
            String query =  "SELECT role_id, user_name, pass_word" + " FROM [User] WHERE user_name = ? AND pass_word = ?";
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            status = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
