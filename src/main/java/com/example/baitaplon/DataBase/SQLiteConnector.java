package com.example.baitaplon.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteConnector {
    Connection connection = null;
    String url = "jdbc:sqlite:data/dict_hh.db";
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public SQLiteConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            System.out.println("SQLite kết nối thành công");
        } catch (Exception e) {
            System.out.println("SQLite kết nối thất bại " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("THÀNH CÔNG: Đã đóng kết nối với CSDL");
            }
        } catch (Exception e) {
            System.out.println("LỖI: Lỗi đóng kết nối với CSDL");
        }
    }
}
