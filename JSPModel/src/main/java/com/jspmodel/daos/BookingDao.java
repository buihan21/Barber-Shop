
package com.jspmodel.daos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Month;
import com.jspmodel.models.Booking;

public class BookingDao {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Registerd");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbershop", "root", "123456");
        System.out.println("Connection successfully");
        return con;
    }

    public static int insertBooking(Booking u) throws ClassNotFoundException, SQLException {
        Connection con = BookingDao.getConnection();
        System.out.println("Conection Established Successfully");
        PreparedStatement ps = con.prepareStatement("insert into Booking(id, user_id, service_id, employee_id, set_hour, note, created_at) values(?,?,?,?,?,?,?)");
        ps.setInt(1, u.getId());
        ps.setInt(2, u.getUser_id());
        ps.setInt(3, u.getService_id());
        ps.setInt(4, u.getEmployee_id());
        ps.setObject(5, u.getSet_hour());
        ps.setString(6, u.getNote());
        ps.setObject(7, u.getCreated_at());
        int y = ps.executeUpdate();
        con.close();
        return y;
    }

    public static Booking BookingSearch(int b_id) throws ClassNotFoundException, SQLException {
        Connection con = BookingDao.getConnection();
        System.out.println("Conection Established Successfully");
        Booking b = new Booking();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Booking WHERE id=?");
        ps.setInt(1, b_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            b.setId(rs.getInt("id"));
            b.setUser_id((rs.getInt("user_id")));
            b.setService_id(rs.getInt("service_id"));
            b.setEmployee_id(rs.getInt("employee_id"));
            b.setSet_hour((LocalDateTime)  rs.getObject("set_hour"));
            b.setNote(rs.getString("note"));
            b.setCreated_at((LocalDateTime) rs.getObject("created_at"));
        }
        System.out.println("note: " + b.getNote());
        con.close();
        return b;
    }

    public static int updateBookingInfo(Booking u) throws ClassNotFoundException, SQLException {

        Connection con = BookingDao.getConnection();
        System.out.println("Conection Established Successfully updateBookingInfo");
        PreparedStatement ps = con.prepareStatement("update Booking set id= ?, user_id=?, service_id=?, employee_id=?, set_hour=?, note=?, created_at=? where id=?");
        ps.setInt(1, u.getId());
        ps.setInt(2, u.getUser_id());
        ps.setInt(3, u.getService_id());
        ps.setInt(4, u.getEmployee_id());
        ps.setObject(5, u.getSet_hour());
        ps.setString(6, u.getNote());
        ps.setObject(7, u.getCreated_at());
        ps.setInt(8, u.getId());
        int b = ps.executeUpdate();
        con.close();
        return b;
    }

    public static int deleteBooking(int b_id) throws ClassNotFoundException, SQLException {
        Connection con = BookingDao.getConnection();
        System.out.println("Conection Established Successfully");
        PreparedStatement ps = con.prepareStatement("delete from Booking where id=?");
        ps.setInt(1, b_id);
        int b = ps.executeUpdate();
        con.close();
        return b;
    }

    public static ArrayList<Booking> getBookingRecords(int start, int total) throws SQLException, ClassNotFoundException {
        ArrayList<Booking> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("select * from Booking limit " + (start - 1) + "," + total);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Booking b = new Booking();
            b.setId(rs.getInt("id"));
            b.setUser_id((rs.getInt("user_id")));
            b.setService_id(rs.getInt("service_id"));
            b.setEmployee_id(rs.getInt("employee_id"));
            b.setSet_hour((LocalDateTime)rs.getObject("set_hour"));
            b.setNote(rs.getString("note"));
            b.setCreated_at((LocalDateTime) rs.getObject("created_at"));
            list.add(b);
        }
        con.close();
        return list;
    }
// test
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        LocalDateTime l1 = LocalDateTime.of(2022, Month.MARCH, 21, 9, 0, 0);
        LocalDateTime l2 = LocalDateTime.of(2022, Month.MARCH, 14, 15, 30, 0);
        LocalDateTime l3 = LocalDateTime.of(2022, Month.APRIL, 4, 10, 45, 0);
        Booking b = new Booking(1,1,1,1, l1, "happy ending", l2);
        Booking b1 = new  Booking(2,2,4,5, l1, "very happy ending", l3);
        Booking b2 = new  Booking(3,2,3,1, l2, "bad ending", l3);
        insertBooking(b);
        insertBooking(b1);
        insertBooking(b2);
        Booking bs = BookingSearch(1);
        Booking b2_fixed = new Booking(3,2,3,1, l2, "null", l3);
        updateBookingInfo(b2_fixed);
        ArrayList<Booking> a = getBookingRecords(1, 3);
        for(Booking b3 : a)
        {
            System.out.println(b3.getNote());
        }
        deleteBooking(3);
    }
}
