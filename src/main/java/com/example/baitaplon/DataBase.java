package com.example.baitaplon;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DataBase {
    private final static String url = "jdbc:mysql://localhost:3306/jdbc_db";
    private final static String username = "root";
    private final static String password = "123456789";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Connected");

            } else {
                System.out.println("Failed");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
//        cloneConnection();
        return connection;
    }

    public static void cloneConnection(Connection connection) {
        try {
            if (connection == null) {
                System.out.println("Failed cloneConnection \"null\"");
            } else {

                connection.close();
            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        // MySQL database URL
        System.out.println(getConnection());
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("select * from dictionary");
        // show data
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2)
                    + "  " + rs.getString(3));
        }


    }
}