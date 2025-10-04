package com.example.aims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AIMSTEST {
    public static void main(String[] args) {
        createTables();
    }

    public static void createTables() {
        String url = "jdbc:mysql://localhost:3306/aims";
        String user = "root";
        String password = "162006";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Tạo bảng media
            String createMediaTable = """
                    CREATE TABLE IF NOT EXISTS media (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        category VARCHAR(50) NOT NULL,
                        value INT NOT NULL,
                        price DECIMAL(10, 2) NOT NULL,
                        quantity INT NOT NULL,
                        imageURL VARCHAR(255),
                        rushOrderAvailable BOOLEAN,
                        weight DECIMAL(5, 2),
                        created_at DATETIME,
                        updated_at DATETIME
                    );
                    """;
            stmt.executeUpdate(createMediaTable);

            // Tạo bảng users
            String createUsersTable = """
                    CREATE TABLE IF NOT EXISTS users (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(255) NOT NULL,
                        email VARCHAR(255) UNIQUE,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    );
                    """;
            stmt.executeUpdate(createUsersTable);

            System.out.println("Tables created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
