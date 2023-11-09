package com.example.baitaplon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dictionary_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "thanhtre2004";

    private Connection connection;

    public MySQLConnector() {
        try {
            // Kết nối tới cơ sở dữ liệu
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Đã đóng kết nối đến cơ sở dữ liệu!");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đóng kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }
    }
}
