package com.jspmodel.daos;

import com.jspmodel.db.DBConnect;
//import com.jspmodel.models.User;

import com.jspmodel.db.DBConnect;

import java.sql.*;

public class LoginDao {
    public static boolean checkLogin(String name, String pass) throws Exception {
        boolean status = false;
        try {
            PreparedStatement stm;
            try (Connection con = new DBConnect().getConnection()) {
                System.out.println("Connection successfully");

                String sql = "SELECT user_name, pass_word" + " FROM [User] WHERE user_name = ? AND pass_word = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, pass);

                ResultSet rs = stm.executeQuery();
                status = rs.next();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args) throws Exception {
        System.out.print(checkLogin("admin", "admin"));
    }
}

//public class LoginDao {
//    Connection conn = null;
//    PreparedStatement ps = null;
//    ResultSet rs = null;
//
//    public boolean checkLogin(String name, String pass){
//        boolean status = false;
//        try {
//            String query = "SELECT user_name, pass_word FROM [User] WHERE user_name = ? AND pass_word = ?";
//            conn = new DBConnect().getConnection();
//            ps = conn.prepareStatement(query);
//            ps.setString(1, name);
//            ps.setString(2, pass);
//            rs = ps.executeQuery();
//            status = rs.next();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return status;
//    }
//}
