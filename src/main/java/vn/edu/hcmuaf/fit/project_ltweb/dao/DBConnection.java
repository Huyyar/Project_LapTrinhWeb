package vn.edu.hcmuaf.fit.project_ltweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://caboose.proxy.rlwy.net:45861/railway?useSSL=true&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "yvjvUYIqFPLxeEDsKJktPPVDcRllKQsj";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load driver
            System.out.println("MySQL driver loaded");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
