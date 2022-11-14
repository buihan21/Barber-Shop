/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jspmodel.daos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.jspmodel.models.Employee;
public class EmployeeDao {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Registerd");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbershop", "root", "123456");
        System.out.println("Connection successfully");
        return con;
    }

    public static int insertEmployee(Employee e) throws ClassNotFoundException, SQLException {
        Connection con = EmployeeDao.getConnection();
        System.out.println("Conection Established Successfully");
        PreparedStatement ps = con.prepareStatement("insert into Employee(id, name, task, base_salary, working_days) values(?,?,?,?,?)");
        ps.setInt(1, e.getId());
        ps.setString(2, e.getName());
        ps.setString(3, e.getTask());
        ps.setInt(4, e.getBase_salary());
        ps.setInt(5, e.getWorking_days());
        int y = ps.executeUpdate();
        con.close();
        return y;
    }

    public static Employee EmployeeSearch(int id) throws ClassNotFoundException, SQLException {
        Connection con = EmployeeDao.getConnection();
        System.out.println("Conection Established Successfully");
        Employee e = new Employee();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Employee WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            e.setId(rs.getInt("id"));
            e.setName(rs.getString("name"));
            e.setTask(rs.getString("task"));
            e.setBase_salary(rs.getInt("base_salary"));
            e.setWorking_days(rs.getInt("working_days"));
        }
        System.out.println("name: " + e.getName());
        con.close();
        return e;
    }

    public static void updateEmployeeInfo(Employee e) throws ClassNotFoundException, SQLException {

        Connection con = EmployeeDao.getConnection();
        System.out.println("Conection Established Successfully updateEmployeeInfo");
        PreparedStatement ps = con.prepareStatement("update Employee set id= ?, name = ?, task = ?, base_salary = ?, working_days = ? where id=?");
        ps.setInt(1, e.getId());
        ps.setString(2, e.getName());
        ps.setString(3, e.getTask());
        ps.setInt(4, e.getBase_salary());
        ps.setInt(5, e.getWorking_days());
        ps.setInt(6, e.getId());
        ps.executeUpdate();
        con.close();
        
    }

    public static int deleteEmployee(int id) throws ClassNotFoundException, SQLException {
        Connection con = EmployeeDao.getConnection();
        System.out.println("Conection Established Successfully");
        PreparedStatement ps = con.prepareStatement("delete from Employee where id=?");
        ps.setInt(1, id);
        int b = ps.executeUpdate();
        con.close();
        return b;
    }

    public static ArrayList<Employee> getEmployeeRecords(int start, int total) throws SQLException, ClassNotFoundException {
        ArrayList<Employee> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("select * from Employee limit " + (start - 1) + "," + total);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Employee e = new Employee();
            e.setId(rs.getInt("id"));
            e.setName(rs.getString("name"));
            e.setTask(rs.getString("task"));
            e.setBase_salary(rs.getInt("base_salary"));
            e.setWorking_days(rs.getInt("working_days"));
            list.add(e);
        }
        con.close();
        return list;
    }
// test
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Employee e1 = new Employee(1, "hoang", "cat", 200, 12);
        Employee e2 = new Employee(2, "an", "tia", 220, 15);
        Employee e3 = new Employee(1, "hoa", "cat toc", 200, 12);
        insertEmployee(e1);
        insertEmployee(e2);
        updateEmployeeInfo(e3);
        EmployeeSearch(1);
        ArrayList<Employee> el = getEmployeeRecords(1, 3);
        for(Employee e4: el)
        {
            System.out.println(e4.getName());
        }
        deleteEmployee(2);
    }
}