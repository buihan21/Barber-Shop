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
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" +dbName;
        if (instance.trim().isEmpty()){
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        }
        Class.forName("om.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
}
