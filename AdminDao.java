package com.jspmodel.daos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.jspmodel.models.Booking;
import com.jspmodel.models.User;
public class AdminDao {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Registerd");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbershop", "root", "123456");
        System.out.println("Connection successfully");
        return con;
    }
    public static void insertRole(User u) throws SQLException, ClassNotFoundException
    {
        Connection con = AdminDao.getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO Role(id, name) VALUES(?,?);");
        ps.setInt(1, u.getId());
        // gia su role_id = 0 la ng dung, role_id = 1 la admin;
        if(u.getRole_id() == 0)
            ps.setString(2, "user");
        else
            ps.setString(2, "admin");
        ps.executeUpdate();
        con.close();
    }
    public static boolean validate(User u) throws SQLException, ClassNotFoundException{
        Connection con = AdminDao.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT name FROM Role WHERE id = ?");
        ps.setInt(1, u.getId());
        ResultSet rs = ps.executeQuery();
        rs.next();
        // gia su neu la admin thi name = admin
        return rs.getString("name").equals("admin");
    }
    public static void deleteBooking(int id, User u) throws SQLException, ClassNotFoundException{
        if(validate(u))
            BookingDao.deleteBooking(id);
    }
    public static ArrayList<Booking> getBookingRecords(int start, int total, User u) throws SQLException, ClassNotFoundException {
        if(validate(u))
            return BookingDao.getBookingRecords(start, total);
        return null;
    }
    //test
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        User u = new User(2, 1, "user_name", "pass_word", "hoang", "124356");        
        insertRole(u);
        ArrayList<Booking> arr = getBookingRecords(1, 3, u);
        for(Booking b : arr)
        {
            System.out.println(b.getId());
        }
        deleteBooking(2, u);
    }
}

