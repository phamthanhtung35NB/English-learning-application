package com.example.baitaplon;

import java.sql.*;
import java.util.Scanner;

public class DictionaryManagement {
    Scanner scan = new Scanner(System.in);


    /**
     * Tìm từ bằng Cơ sở dữ liệu.
     * Gọi hàm: mở Terminal, thao tác nhập từ cần tìm, trả lại ý nghĩa của từ. Tất cả qua Terminal
     */
    public void dictionarySQLiteSearch() {
        System.out.print("Tim tu: ");
        String input = scan.nextLine();
        String searchWord = input.toLowerCase();

        SQLiteConnector connector = new SQLiteConnector();
        Connection connection = connector.getConnection();

        try {
            Statement statement = connection.createStatement();
            String querry = "SELECT * FROM av WHERE word = '" + searchWord + "';";
            ResultSet resultSet = statement.executeQuery(querry);

            while (resultSet.next()) {
                String mean = resultSet.getString("description");
                System.out.println(mean);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("LỖI:" + e.getMessage());
        }
        connector.closeConnection();
    }

    /**
     * Thêm từ bằng Cơ sở dữ liệu.
     * Gọi hàm: mở Terminal, thao tác nhập từ cần thêm
     * Nếu từ đã tồn tại - in ra lỗi
     * Nếu từ chưa tồn tại yêu cầu nhập thêm ý nghĩa từ đó
     * Tất cả qua Terminal
     */
    public void addWordInSQLiteDB() {
        System.out.print("Thêm từ: ");
        String input = scan.nextLine();
        String addWord = input.toLowerCase();
        //Check whether it was in DB or not
        SQLiteConnector connectorSQLite = new SQLiteConnector();
        Connection connection = connectorSQLite.getConnection();
        boolean ifNot = false;
        String querry;
        try {
            querry = "SELECT html FROM av WHERE word = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, addWord);
            ResultSet resultSet = preparedStatement.executeQuery();
            String html = "";
            while (resultSet.next()) {
                html = resultSet.getString("html");
            }
            if (html.equals("")) {
                ifNot = true;
            } else {
                ifNot = false;
            }
            if (ifNot) {
                System.out.println("Nhap nghia cua tu " + addWord);
                String mean = scan.nextLine();
                //Add at last in database
                querry = "INSERT INTO av (word, description) VALUES (?, ?);";
                preparedStatement = connection.prepareStatement(querry);
                preparedStatement.setString(1, addWord);
                preparedStatement.setString(2, mean);
                int check = preparedStatement.executeUpdate();
                if (check > 0) {
                    System.out.println("Thêm thành công từ vào CSDL");
                } else {
                    System.out.println("LỖI: KHÔNG THỂ THÊM TỪ VÀO CSDL");
                }
            } else {
                //Note
                System.out.println("TỪ ĐÃ TỒN TẠI");
                System.out.println(html);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("LỖI: " + e.getMessage());
        }
        connectorSQLite.closeConnection();
    }

    /**
     * Xóa từ bằng Cơ sở dữ liệu.
     * Gọi hàm: mở Terminal, thao tác nhập từ cần xóa
     * Nếu từ chưa có trong csdl - in ra lỗi
     * Xóa từ đó nếu đã tồn tại trong CSDL
     * Tất cả qua Terminal
     */
    public void dropWordInSQLiteDB() {
        System.out.print("Xoa tu: ");
        String input = scan.nextLine();
        String wordDrop = input.toLowerCase();

        SQLiteConnector connectorSQLite = new SQLiteConnector();
        Connection connection = connectorSQLite.getConnection();
        boolean ifNot = true;
        String querry = "";
        try {
            querry = "SELECT * FROM av WHERE word = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, wordDrop);
            ResultSet resultSet = preparedStatement.executeQuery();
            String html = "";
            while (resultSet.next()) {
                if (resultSet.getString("html") != null) {
                    html = resultSet.getString("html");
                } else if (resultSet.getString("description") != null) {
                    html = resultSet.getString("description");
                }
            }
            if (html.equals("")) {
                ifNot = true;
            } else {
                ifNot = false;
            }
            if (!ifNot) {
                //Delete in DB
                querry = "DELETE FROM av WHERE word = ?";
                preparedStatement = connection.prepareStatement(querry);
                preparedStatement.setString(1, wordDrop);
                int check = preparedStatement.executeUpdate();
                if (check > 0) {
                    System.out.println("Đã XÓA từ " + wordDrop + " khỏi CSDL!");
                } else {
                    System.out.println("LỖI: KHÔNG THỂ XÓA từ " + wordDrop + " khỏi CSDL!");
                }
            } else {
                //Khong co nua thi bao loi
                System.out.println("LỖI: KHÔNG TÌM ĐƯỢC TỪ ĐÃ CHO");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("LỖI: " + e.getMessage());
        }
        connectorSQLite.closeConnection();
    }
}
