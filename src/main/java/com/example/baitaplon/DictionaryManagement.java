package com.example.baitaplon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DictionaryManagement {
    /**
     * Nhập vào bàn phím số lượng từ vựng (Word).
     * Định dạng nhập dữ liệu từ điển Anh – Việt:
     * +Dòng 1: Nhập từ tiếng Anh.
     * +Dòng 2: Nhập giải thích bằng tiếng Việt.
     */
    public void insertFromCommandline() {


    }

    /**
     * nhập dữ liệu từ điển từ tệp dictionaries.txt.
     * Quy định tệp dictionaries.txt: Mỗi dòng chứa từ tiếng anh và giải thích tiếng việt
     * (ngăn cách nhau bằng dấu tab).
     */
    public void insertFromFile() {

    }


    /**
     * chức năng tra cứu từ điển.
     */
    public void dictionaryLookup(String word) {

    }

    /**
     * chuc nang them tu.
     *
     * @param word_target  _ tu tieng anh
     * @param word_explain _ nghia  tieng viet
     * @return _ true khi them thanh cong nguoc lai
     */
    public boolean dictionaryAdd(String word_target, String word_explain) {

        return true;
    }

    /**
     * chuc nang sua tu.
     */
//    public void dictionaryEdit(String word_target, String word_explain) {
//        Dictionary.set(word_target, word_explain);
//    }

    /**
     * chuc nang xoa tu.
     *
     * @return true khi xoa thanh cong
     */
//    public boolean dictionaryErase(String word) {
//        if (Dictionary.delete(word) == true) {
//            System.out.println("Done");
//            return true;
//        }
//        System.out.println("Error");
//        return false;
//    }

    /**
     * Search Word in database.
     */
    public void dictionarySearcherinDB() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Tim tu: ");
        String input = scan.nextLine();
        String searchWord = input.toLowerCase();
        MySQLConnector mySQLConnection = new MySQLConnector();
        Connection connection = mySQLConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT definition FROM dictionary_db.dictionary where target like '" + searchWord + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String detail = resultSet.getString("definition");

                System.out.println("detail: " + detail);
                System.out.println("-------------------------");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mySQLConnection.closeConnection();
    }
}
