package com.jspmodel.daos;
import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;
import java.util.Timer;

import com.jspmodel.models.Booking;


public class BookingDao {
    public static Connection getConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver Registered");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://MSI\\MSSQLSERVER;databaseName=barberShop;encrypt=true;trustServerCertificate=true;","sa", "sa");
            System.out.println("Connection successfully");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int insertBooking(Booking a) throws SQLException {
        Connection conn = BookingDao.getConnection();
        System.out.println("Connection Established Successfully");

        PreparedStatement ps = Objects.requireNonNull(conn).prepareStatement("INSERT INTO Booking(user_id, service_id, employee_id, set_hour, set_date) values (?,?,?,?,?)");
        ps.setInt(1, a.getUser_id());
        ps.setInt(2, a.getService_id());
        ps.setInt(3, a.getEmployee_id());
        ps.setTime(4, a.getSet_hour());
        ps.setDate(5, a.getSet_date());
        int z = ps.executeUpdate();
        conn.close();
        return z;
    }

    public static int deleteBooking(int idBooking) throws SQLException {
        Connection con= BookingDao.getConnection();
        System.out.println("Connection Established Successfully");

        PreparedStatement ps= Objects.requireNonNull(con).prepareStatement("delete from [Booking]  where id=?");
        ps.setInt(1,idBooking);

        int f3 = ps.executeUpdate();
        con.close();
        return f3;
    }
    public static ArrayList<Booking> getList(){
        Connection con= BookingDao.getConnection();
        System.out.println("Connection Established Successfully");
        String sql = "SELECT * FROM [Booking]";
        ArrayList<Booking> list = new ArrayList<>();
        try {
            PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int user_id = rs.getInt("user_id");
                int service_id = rs.getInt("service_id");
                int employee_id = rs.getInt("employee_id");
                Time set_hour = rs.getTime("set_hour");
                Date set_date = rs.getDate("set_date");
                Booking a = new Booking(user_id, service_id, employee_id, set_hour, set_date);
                list.add(a);
                System.out.println(a);
                //System.out.println("Return list successful");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //bdau check từ set_hour trước, nếu set_hour mà có người đặt r thì check tiếp sang employee_id. Nếu employee cx trùng thì sẽ tbao book lại, còn ko thì sẽ insertBooking

    public static boolean checkBooking(Booking a){
        boolean status = true;
        ArrayList<Booking> list = getList();
        for (Booking booking : list) {
//            if (Objects.equals(a.getSet_date(), booking.getSet_date())) {
//                System.out.println(a.getSet_date() + " " +booking.getSet_date());
//                if (Objects.equals(a.getSet_hour(), booking.getSet_hour())) {
//                    System.out.println(a.getSet_hour()+" "+booking.getSet_hour());
//                    if (a.getEmployee_id() == booking.getEmployee_id()) {
//                        System.out.println(a.getEmployee_id()+" "+ booking.getEmployee_id());
//                        status = false;
//                        System.out.println("Booking again, Please!");
//                        break;
//                    } else {
//                        status = true;
//                    }
//                } else {
//                    status = true;
//                }
//            } else {
//                status = true;
//            }
            if (Objects.equals(a.getSet_date(), booking.getSet_date()) &&
                    Objects.equals(a.getSet_hour(), booking.getSet_hour()) &&
                    a.getEmployee_id() == booking.getEmployee_id()){
                status = false;
                System.out.println("Booking again, Please!");
                break;
            }
        }
        return status;
    }

    public static void main(String[] args) throws SQLException {
        Time book1 = Time.valueOf("09:00:00");
        Time book2 = Time.valueOf("10:00:00");
        Time time_testCheckBK = Time.valueOf("09:00:00");
        Date date1 = Date.valueOf("2022-11-11");
        Date date2 = Date.valueOf("2022-11-12");
        Date date_testCheckBK = Date.valueOf("2022-11-11");
        Booking b1 = new Booking(3,1,1,book1, date1);
        Booking b2 = new Booking(4,4,5,book2, date1);
        Booking b3 = new Booking(3,5,1,time_testCheckBK, date_testCheckBK);
        System.out.println(checkBooking(b3));
        System.out.println(b3);
//        getList();
//        insertBooking(b1);
//        insertBooking(b2);
//        deleteBooking(3);
//        System.out.println("Delete Successful");
//        System.out.println("Insert successful");
    }
}


//public class BookingDao {
//
//    public static Connection getConnection() throws Exception{
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //System.out.println("Driver Registered");
//        return DriverManager.getConnection("jdbc:sqlserver://MSI\\MSSQLSERVER;databaseName=barberShop;encrypt=true;trustServerCertificate=true;","sa", "sa");
//    }
//
//    public static int insertBooking(Booking u) throws Exception {
//        Connection con = BookingDao.getConnection();
//        System.out.println("Connection Established Successfully");
//        PreparedStatement ps = con.prepareStatement("insert into Booking(user_id, service_id, employee_id, set_hour, note, created_at) values(?,?,?,?,?,?)");
//       // ps.setInt(1, u.getId());
//        ps.setInt(1, u.getUser_id());
//        ps.setInt(2, u.getService_id());
//        ps.setInt(3, u.getEmployee_id());
//        ps.setObject(4, u.getSet_hour());
//        ps.setString(5, u.getNote());
//        ps.setObject(6, u.getCreated_at());
//        int y = ps.executeUpdate();
//        con.close();
//        return y;
//    }
//
//    public static Booking BookingSearch(int b_id) throws Exception {
//        Connection con = BookingDao.getConnection();
//        System.out.println("Connection Established Successfully");
//        Booking b = new Booking();
//        PreparedStatement ps = con.prepareStatement("SELECT * FROM Booking WHERE id=?");
//        ps.setInt(1, b_id);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            b.setId(rs.getInt("id"));
//            b.setUser_id((rs.getInt("user_id")));
//            b.setService_id(rs.getInt("service_id"));
//            b.setEmployee_id(rs.getInt("employee_id"));
//            b.setSet_hour((Time)  rs.getObject("set_hour"));
//            b.setNote(rs.getString("note"));
//            b.setCreated_at((LocalDateTime) rs.getObject("created_at"));
//        }
//        System.out.println("note: " + b.getNote());
//        con.close();
//        return b;
//    }
//
//    public static int updateBookingInfo(Booking u) throws Exception {
//
//        Connection con = BookingDao.getConnection();
//        System.out.println("Conection Established Successfully updateBookingInfo");
//        PreparedStatement ps = con.prepareStatement("update Booking set id= ?, user_id=?, service_id=?, employee_id=?, set_hour=?, note=?, created_at=? where id=?");
//        ps.setInt(1, u.getId());
//        ps.setInt(2, u.getUser_id());
//        ps.setInt(3, u.getService_id());
//        ps.setInt(4, u.getEmployee_id());
//        ps.setObject(5, u.getSet_hour());
//        ps.setString(6, u.getNote());
//        ps.setObject(7, u.getCreated_at());
//        ps.setInt(8, u.getId());
//        int b = ps.executeUpdate();
//        con.close();
//        return b;
//    }
//
//    public static int deleteBooking(int b_id) throws Exception {
//        Connection con = BookingDao.getConnection();
//        System.out.println("Connection Established Successfully");
//        PreparedStatement ps = con.prepareStatement("delete from Booking where id=?");
//        ps.setInt(1, b_id);
//        int b = ps.executeUpdate();
//        con.close();
//        return b;
//    }
//
//    public static ArrayList<Booking> getBookingRecords(int start, int total) throws Exception {
//        ArrayList<Booking> list = new ArrayList<>();
//        Connection con = getConnection();
//        PreparedStatement ps = con.prepareStatement("select * from Booking limit " + (start - 1) + "," + total);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            Booking b = new Booking();
//            b.setId(rs.getInt("id"));
//            b.setUser_id((rs.getInt("user_id")));
//            b.setService_id(rs.getInt("service_id"));
//            b.setEmployee_id(rs.getInt("employee_id"));
//            b.setSet_hour((Time)rs.getObject("set_hour"));
//            b.setNote(rs.getString("note"));
//            b.setCreated_at((LocalDateTime) rs.getObject("created_at"));
//            list.add(b);
//        }
//        con.close();
//        return list;
//    }
//    // test
//    public static void main(String[] args) throws Exception {
//        LocalDateTime l1 = LocalDateTime.of(2022, Month.MARCH, 21, 9, 0, 0);
//        LocalDateTime l2 = LocalDateTime.of(2022, Month.MARCH, 14, 15, 30, 0);
//        LocalDateTime l3 = LocalDateTime.of(2022, Month.APRIL, 4, 10, 45, 0);
//        Time book1 = Time.valueOf("09:00:00");
//        Time book2 = Time.valueOf("10:00:00");
//        Time book3 = Time.valueOf("11:00:00");
//        Booking b = new Booking(1,1,1, book1, "happy ending", l1);
//        Booking b1 = new  Booking(2,4,5, book2, "very happy ending", l2);
//        Booking b2 = new  Booking(2,3,1, book3, "bad ending", l3);
//        System.out.println("insert successful");
//
//        insertBooking(b);
//        insertBooking(b1);
//        insertBooking(b2);
//        System.out.println("Insert successful");
//        Booking bs = BookingSearch(1);
//        //Booking b2_fixed = new Booking(2,3,1, l2, "null", l3);
//        //updateBookingInfo(b2_fixed);
//        ArrayList<Booking> a = getBookingRecords(1, 3);
////        for(Booking b3 : a)
////        {
////            System.out.println(b3.getNote());
////        }
//        deleteBooking(3);
//    }
//}