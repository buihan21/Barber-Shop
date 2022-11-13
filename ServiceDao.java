
package com.jspmodel.daos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.jspmodel.models.Service;
public class ServiceDao {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Registerd");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbershop", "root", "123456");
        System.out.println("Connection successfully");
        return con;
    }

    public static int insertService(Service s) throws ClassNotFoundException, SQLException {
        Connection con = ServiceDao.getConnection();
        System.out.println("Conection Established Successfully");
        PreparedStatement ps = con.prepareStatement("insert into Service(id, employee_id, name, cost) values(?,?,?,?)");
        ps.setInt(1, s.getId());
        ps.setInt(2, s.getEmployee_id());
        ps.setString(3, s.getName());
        ps.setInt(4, s.getCost());
        int y = ps.executeUpdate();
        con.close();
        return y;
    }

    public static Service ServiceSearch(int id) throws ClassNotFoundException, SQLException {
        Connection con = ServiceDao.getConnection();
        System.out.println("Conection Established Successfully");
        Service s = new Service();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Service WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setEmployee_id(rs.getInt("employee_id"));
            s.setCost(rs.getInt("cost"));
        }
        System.out.println("name: " + s.getName());
        con.close();
        return s;
    }

    public static void updateServiceInfo(Service s) throws ClassNotFoundException, SQLException {

        Connection con = ServiceDao.getConnection();
        System.out.println("Conection Established Successfully updateServiceInfo");
        PreparedStatement ps = con.prepareStatement("update Service set id= ?, employee_id = ?, name = ?, cost = ? where id=?");
        ps.setInt(1, s.getId());
        ps.setInt(2, s.getEmployee_id());
        ps.setString(3, s.getName());
        ps.setInt(4, s.getCost());
        ps.setInt(5, s.getId());
        ps.executeUpdate();
        con.close();    
    }

    public static int deleteService(int id) throws ClassNotFoundException, SQLException {
        Connection con = ServiceDao.getConnection();
        System.out.println("Conection Established Successfully");
        PreparedStatement ps = con.prepareStatement("delete from Service where id=?");
        ps.setInt(1, id);
        int b = ps.executeUpdate();
        con.close();
        return b;
    }

    public static ArrayList<Service> getServiceRecords(int start, int total) throws SQLException, ClassNotFoundException {
        ArrayList<Service> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("select * from Service limit " + (start - 1) + "," + total);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Service s = new Service();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setEmployee_id(rs.getInt("employee_id"));
            s.setCost(rs.getInt("cost"));
            list.add(s);
        }
        con.close();
        return list;
    }
// test
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Service e1 = new Service(1, 2, "cat",  12);
        Service e2 = new Service(2, 3, "tia", 15);
        Service e3 = new Service(1, 4, "cat toc", 12);
        insertService(e1);
        insertService(e2);
        updateServiceInfo(e3);
        ServiceSearch(1);
        ArrayList<Service> el = getServiceRecords(1, 3);
        for(Service e4: el)
        {
            System.out.println(e4.getName());
        }
        deleteService(2);
    }
}