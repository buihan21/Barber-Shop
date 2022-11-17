package com.jspmodel.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private final String serverName = "MSI";
    private final String dbName = "barberShop";
    private final String portNumber = "1433";
    private final String instance = "";
    private final String userID = "sa";
    private final String password = "sa";

    public Connection getConnection() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        System.out.println("Driver Registered");
        return DriverManager.getConnection("jdbc:sqlserver://MSI\\MSSQLSERVER;databaseName=barberShop;encrypt=true;trustServerCertificate=true;","sa", "sa");
    }
}