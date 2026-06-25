package com.mycompany.sign.in.web_app.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;
    
    private static String url;
    private static String user;
    private static String password;
    
    static {
        // Get credentials from environment variables
        url = System.getenv("DB_URL");
        user = System.getenv("DB_USER");
        password = System.getenv("DB_PASSWORD");
        
        // Fallback values for local development
        if (url == null || url.isEmpty()) {
            url = "jdbc:mysql://localhost:3306/railway";
            System.out.println("⚠️ DB_URL not found, using default: " + url);
        }
        if (user == null || user.isEmpty()) {
            user = "root";
            System.out.println("⚠️ DB_USER not found, using default: root");
        }
        if (password == null || password.isEmpty()) {
            password = "";
            System.out.println("⚠️ DB_PASSWORD not found, using default: (empty)");
        }
        
        System.out.println("=========================================");
        System.out.println("📌 Database Connection Details:");
        System.out.println("   URL: " + url);
        System.out.println("   User: " + user);
        System.out.println("   Password: " + (password.isEmpty() ? "(empty)" : "********"));
        System.out.println("=========================================");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    return connection;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        try {
            System.out.println("🔄 Attempting to connect to database...");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database connected successfully!");
            System.out.println("   Connected to: " + url);
        } catch (SQLException e) {
            System.err.println("❌ Connection failed! Check your DB credentials.");
            System.err.println("   Error: " + e.getMessage());
            e.printStackTrace();
        }
        
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("✅ Database connection closed.");
            } catch (SQLException e) {
                System.err.println("❌ Error closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}