package com.example.baitaplon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteConnector {
    Connection connection = null;
    String url = "jdbc:sqlite:/data/dict_hh.db";
    public void setUrl(String url) {
        this.url = url;
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
}
