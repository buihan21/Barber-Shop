package com.jspmodel.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.jspmodel.models.User;

public class UserDao {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Registerd");
        Connection con = DriverManager.getConnection("jdbc:sqlserver://MSI\\MSSQLSERVER;databaseName=barberShop;encrypt=true;trustServerCertificate=true;","sa", "sa");
        System.out.println("Connection successfully");
        return con;
    }

    public static int insertUser(User u) throws ClassNotFoundException, SQLException {
        Connection con = UserDao.getConnection();
        System.out.println("Conection Established Successfully");
        PreparedStatement ps = con.prepareStatement("insert into User(id, role_id, user_name ,pass_word, name, phone_number) values(?,?,?,?,?,?)");
        ps.setInt(1, u.getId());
        ps.setInt(2, u.getRole_id());
        ps.setString(3, u.getUser_name());
        ps.setString(4, u.getPass_word());
        ps.setString(5, u.getName());
        ps.setString(6, u.getPhone_number());
        int y = ps.executeUpdate();
        con.close();
        return y;
    }

    public static User UserSearch(int u_id) throws ClassNotFoundException, SQLException {

        Connection con = UserDao.getConnection();
        System.out.println("Conection Established Successfully");
        User u = new User();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM User WHERE id=?");
        ps.setInt(1, u_id);
        ResultSet rs = ps.executeQuery();
// co the them thong tin lay ra sau
        while (rs.next()) {
            u.setUser_name(rs.getString("user_name"));
        }

        System.out.println("name " + u.getUser_name());
        con.close();
        return u;
    }

    public static int updateUserInfo(User u) throws ClassNotFoundException, SQLException {

        Connection con = UserDao.getConnection();
        System.out.println("Conection Established Successfully updateUserInfo");
        //id, name, age, mobNo, address, gender, bloodgroup, symptoms, department, doa, username, password

        PreparedStatement ps = con.prepareStatement("update User set id= ?, role_id = ?, user_name = ?,pass_word = ?, name = ?, phone_number = ? where id=?");
        ps.setInt(1, u.getId());
        ps.setInt(2, u.getRole_id());
        ps.setString(3, u.getUser_name());
        ps.setString(4, u.getPass_word());
        ps.setString(5, u.getName());
        ps.setString(6, u.getPhone_number());
        ps.setInt(7, u.getId());
        int u5 = ps.executeUpdate();
        con.close();
        return u5;
    }

    public static int deleteuser(int u_id) throws ClassNotFoundException, SQLException {
        Connection con = UserDao.getConnection();
        System.out.println("Conection Established Successfully");
        PreparedStatement ps = con.prepareStatement("delete from User where id=?");
        ps.setInt(1, u_id);
        int f2 = ps.executeUpdate();
        con.close();
        return f2;
    }

    public static ArrayList<User> getUserRecords(int start, int total) throws SQLException, ClassNotFoundException {
        ArrayList<User> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("select * from User limit " + (start - 1) + "," + total);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User e2 = new User();
            e2.setId(rs.getInt("id"));
            e2.setRole_id((rs.getInt("role_id")));
            e2.setUser_name(rs.getString("user_name"));
            e2.setPass_word(rs.getString("password"));
            e2.setName(rs.getString("name"));
            e2.setPhone_number(rs.getString("phone_number"));
            list.add(e2);
        }
        con.close();
        return list;
    }
// test
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        User u = new User(2, 2, "user_name", "pass_word", "hoang", "124356");
        insertUser(u);
        User u1 = UserSearch(2);
        User u2 = new User(3, 3, "user_name", "pass_word", "a", "1243567");
        insertUser(u2);
        ArrayList<User> a = new ArrayList<>();
        for(User u3 : a)
        {
            System.out.println(u3.getName());
        }
        deleteuser(3);
    }
}
