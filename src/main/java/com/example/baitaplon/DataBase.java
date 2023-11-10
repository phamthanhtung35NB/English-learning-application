package com.example.baitaplon;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

public class DataBase {
    private final static String url = "jdbc:mysql://localhost:3306/jdbc_db";
    private final static String username = "root";
    private final static String password = "123456789";

    public static Connection getConnectionToDataBase() {
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
        return connection;
    }

    public static String addDataLogin(String username,String password) throws SQLException {
        //cau lech khong co tham so

        Statement query = null;
        ResultSet ketQuaTruyVan = null;
        Connection connection = getConnectionToDataBase();
        try {
            System.out.println(connection);
            query = connection.createStatement();
            ketQuaTruyVan = query.executeQuery("select user_name,password,studying_array from account");
            //check tk mk
            while (ketQuaTruyVan.next()) {
                System.out.printf(ketQuaTruyVan.getString("user_name") + " " + ketQuaTruyVan.getString("password") + "\n");
                if (username.equals(ketQuaTruyVan.getString("user_name")) && password.equals(ketQuaTruyVan.getString("password"))) {
                    return ketQuaTruyVan.getString("studying_array");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ketQuaTruyVan != null) {
                    ketQuaTruyVan.close();
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "-1";
    }

    public static boolean newAccount(String username, String password) throws SQLException {
        Connection connection = getConnectionToDataBase();
        Statement query = connection.createStatement();
        ResultSet ketQuaTruyVan = null;
        try {
            System.out.println(getConnectionToDataBase());
            ketQuaTruyVan = query.executeQuery("SELECT MAX(id_account) AS max_id FROM account");
            int maxId = ketQuaTruyVan.getInt("max_id");
            maxId++;

            String sql = "INSERT INTO account (id_account,user_name, password) VALUES (?,?,?)";
            //cau lech co tham so
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, maxId);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            // Push to database
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ketQuaTruyVan != null) {
                    ketQuaTruyVan.close();
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static Set<Integer> takeData(String string) throws SQLException {
        String[] numberStrings = string.split(",");
        Set<Integer> numberSetKetQua = new TreeSet<>();

        for (String numberString : numberStrings) {
            try {
                int number = Integer.parseInt(numberString.trim());
                numberSetKetQua.add(number);
            } catch (NumberFormatException e) {
                System.out.println(numberString);
            }
        }

        return numberSetKetQua;
    }

    public static void main(String[] args) throws SQLException {
//        System.out.println(newAccount("admin3", "admin3"));
//        Set<Integer> tam = takeData(login("admin2", "admin2"));
//        for (Integer i : tam) {
//            System.out.println(i);
//        }


    }
//    public static void main(String[] args) throws SQLException {
//        // MySQL database URL
//        System.out.println(getConnectionToDataBase());
//        Statement query = getConnectionToDataBase().createStatement();
//        ResultSet ketQuaTruyVan = query.executeQuery("select * from dictionary");
//        // show data
//        while (ketQuaTruyVan.next()) {
//            System.out.println(ketQuaTruyVan.getInt(1) + "  " + ketQuaTruyVan.getString(2)
//                    + "  " + ketQuaTruyVan.getString(3));
//        }
//        query.close();
//
//
//    }
}