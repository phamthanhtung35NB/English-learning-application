package com.example.baitaplon;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

public class DataBase {
    //
    private static Set<Integer> numberIdCuaTuDien = new TreeSet<>();
    private static String chuoiStudying_array = "-1";
    protected static String UserName = "";


    //main
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        //login
        System.out.println(dataBase.checkSQLiteLogin("admin1", "admin11"));
        DataBase.loadDataSqlOfSoTuCaNhan();
    }

    /////////////////////////////////////////LOGIN//////////////////////////////////////////////////////////////////////////////
    //check tai khoan co dung khong
    //neu tk mk sai tra ve -1
    //neus studing_array null tra ve chuoi rong ""
    //neu tk mk dung tra ve chuoi studying_array
    public static String checkSQLiteLogin(String username, String password) {
        SQLiteConnector connector = new SQLiteConnector();
        Connection connection = connector.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM account WHERE user_name = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserName = resultSet.getString("user_name");
                chuoiStudying_array = resultSet.getString("studying_array");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("LỖI login :" + e.getMessage());
        }
        connector.closeConnection();
        if (chuoiStudying_array == null) {
            return "";
        } else {
            return chuoiStudying_array;
        }
    }

    /////////////////////////////////////////LOAD DATA OF STUDYING ARRAY///////////////////////////////////////////////////////
    //lodata study_array in ControllerSoTuCaNhan.dataSoTu
    public static void loadDataSqlOfSoTuCaNhan() {
        SQLiteConnector connector = new SQLiteConnector();
        Connection connection = connector.getConnection();
        try {
            Statement statement = connection.createStatement();
            System.out.println(chuoiStudying_array);

            // lay ra chuoi string
            String queryList = "SELECT id,word,html,description,pronounce FROM av WHERE id IN (" + chuoiStudying_array + ");";

            PreparedStatement preparedStatement = connection.prepareStatement(queryList);
//            preparedStatement.setString(1, chuoiStudying_array);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
//            System.out.println("r:"+resultSet);
            //xoa dataSoTu
            ControllerSoTayCaNhan.dataSoTu.clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                System.out.println(id);
                String word_target = resultSet.getString("word");
                String html = resultSet.getString("html");
                String word_explain = resultSet.getString("description");
                String pronounce = resultSet.getString("pronounce");
//                System.out.println(id + " " + word_target + " " + word_explain + " " + html + " " + pronounce);
                WordSQL word = new WordSQL(id, word_target, word_explain, html, pronounce);
                ControllerSoTayCaNhan.dataSoTu.put(word_target, word);
            }
//            System.out.println("dataSoTu");
            //show dataSoTu
//            for (String key : ControllerSoTayCaNhan.dataSoTu.keySet()) {
//                System.out.println(key + " " + ControllerSoTayCaNhan.dataSoTu.get(key).getID());
//            }
            resultSet.close();
            statement.close();
            connection.close();
            connector.closeConnection();
        } catch (Exception e) {
            System.out.println("LỖI login :" + e.getMessage());
        }
    }

    /////////////////////////////////////////NEW ACCOUNT////////////////////////////////////////////////////////////////////////
    //check tai khoan da co chua
    //tra ve success neu tao tk thanh cong
    //tra ve duplicate neu tk da ton tai
    //tra ve fail neu tao tk that bai
    public static String newAccountSQLite(String username, String password) {
        SQLiteConnector connector = new SQLiteConnector();
        Connection connection = connector.getConnection();

        try {
            // Check xem có tài khoản trong data chưa
            String checkQuery = "SELECT * FROM account WHERE user_name = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, username);
            ResultSet checkResultSet = checkStatement.executeQuery();

            while (checkResultSet.next()) {
                System.out.println("Tài khoản đã tồn tại");
                // close connection and return false
                checkResultSet.close();
                checkStatement.close();
                connector.closeConnection();
                return "duplicate";
            }

            // Select max id
            Statement maxIdStatement = connection.createStatement();
            ResultSet maxIdResultSet = maxIdStatement.executeQuery("SELECT MAX(id_account) AS max_id FROM account");
            int maxId = 0;

            if (maxIdResultSet.next()) {
                maxId = maxIdResultSet.getInt("max_id");
                maxId++;
            }
            // Add
            String insertQuery = "INSERT INTO account (id_account, user_name, password,studying_array) VALUES (?, ?, ?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, maxId);
            insertStatement.setString(2, username);
            insertStatement.setString(3, password);
            insertStatement.setString(4, "");
            int check = insertStatement.executeUpdate();
            checkResultSet.close();
            maxIdResultSet.close();
            maxIdStatement.close();
            checkStatement.close();
            insertStatement.close();
            // close connection and return true
            connector.closeConnection();
            if (check > 0) {
                return "success";
            } else {
                return "fail";
            }

        } catch (SQLException e) {
            System.out.println("LỖI new account: " + e.getMessage());
            return "fail";
        }
    }

    ///////////////////////////////////////CHANGE PASSWORD///////////////////////////////////////////////////////////////////////
    //doi mat khau
    public static String changePasswordSQLite(String userName,String password, String newPassword) {
        SQLiteConnector connector = new SQLiteConnector();
        Connection connection = connector.getConnection();

        try {
            String checkQuery = "SELECT * FROM account WHERE user_name = ? AND password = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, userName);
            checkStatement.setString(2, password);
            ResultSet checkResultSet = checkStatement.executeQuery();

            while (checkResultSet.next()) {
                if (checkResultSet.getString("password").equals(password) && checkResultSet.getString("user_name").equals(userName)) {
                    String updateQuery = "UPDATE account SET password = ? WHERE user_name = ?;";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1, newPassword);
                    preparedStatement.setString(2, userName);
                    int rowsAffected = preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connector.closeConnection();
                    checkResultSet.close();
                    checkStatement.close();
                    if (rowsAffected > 0) {
                        return "success";
                    } else {
                        return "fail";
                    }
                }


            }
            return "IncorrectAccountOrPassword";

        } catch (SQLException e) {
            System.out.println("LỖI new account: " + e.getMessage());
            return "fail";
        }
    }

    ////////////////////////////////SET STUDYING ARRAY///////////////////////////////////////////////////////////////////////
    //chuoi studying_array sang kieu set (numberIdCuaTuDien)
    // load data tu dien cua so tu ca nhan
    // tra ve 1 set cac id cua tu dien
    public static void setTakeData(int id) {
        // tach chuoi studying_array cho vao mang string
        String[] numberStrings = chuoiStudying_array.split(",");

        // chuyen mang string sang set int
        for (String numberString : numberStrings) {
            try {
                int number = Integer.parseInt(numberString.trim());
                numberIdCuaTuDien.add(number);
            } catch (NumberFormatException e) {
                System.out.println(numberString);
            }
        }
        numberIdCuaTuDien.add(id);
    }

    /**
     * chuyen kieu set sang string de truy van
     */
    public static Boolean setIdWordToString(int id) {
        setTakeData(id);
        StringBuilder idListBuilderKetQua = new StringBuilder();
        for (Integer Tam : numberIdCuaTuDien) {
            idListBuilderKetQua.append(Tam).append(",");
        }
        // xoa dau phay cuoi cung
        if (idListBuilderKetQua.length() > 0) {
            idListBuilderKetQua.deleteCharAt(idListBuilderKetQua.length() - 1);
        }
        System.out.println(idListBuilderKetQua);
        chuoiStudying_array = idListBuilderKetQua.toString();
        return true;
    }

    //set studying_array
    public static boolean setSQLiteStuding_array(int id) {
        SQLiteConnector connector = new SQLiteConnector();
        Connection connection = connector.getConnection();
        //cap nhat set numberIdCuaTuDien
        //cap nhat chuoi studying_array
        setIdWordToString(id);
        System.out.println(chuoiStudying_array);
        try {
            // update studying_array
            String updateQuery = "UPDATE account SET studying_array = ? WHERE user_name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, chuoiStudying_array);
            preparedStatement.setString(2, UserName);

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connector.closeConnection();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("LỖI setStuding_array: " + e.getMessage());
            return false;
        }
    }

    ///////////////////////////////////DELETE WORD IN STUDYING ARRAY//////////////////////////////////////////////////////////
    //delete set numberIdCuaTuDien
    public static Boolean deleteIdWordToString(int id) {
        setTakeData(id);
        numberIdCuaTuDien.remove(id);
        StringBuilder idListBuilderKetQua = new StringBuilder();
        for (Integer Tam : numberIdCuaTuDien) {
            idListBuilderKetQua.append(Tam).append(",");
        }
        // xoa dau phay cuoi cung
        if (idListBuilderKetQua.length() > 0) {
            idListBuilderKetQua.deleteCharAt(idListBuilderKetQua.length() - 1);
        }
        chuoiStudying_array = idListBuilderKetQua.toString();
        return true;
    }

    //delete set studying_array
    public static Boolean deleteTackData(int id) {
        // tach chuoi studying_array cho vao mang string
        String[] numberStrings = chuoiStudying_array.split(",");

        // chuyen mang string sang set int
        for (String numberString : numberStrings) {
            try {
                int number = Integer.parseInt(numberString.trim());
                numberIdCuaTuDien.add(number);
            } catch (NumberFormatException e) {
                System.out.println(numberString);
            }
        }
        numberIdCuaTuDien.remove(id);
        return true;
    }

    public static Boolean deleteSQLiteStuding_array(int id) {
        SQLiteConnector connector = new SQLiteConnector();
        Connection connection = connector.getConnection();
        //cap nhat set numberIdCuaTuDien
        //cap nhat chuoi studying_array
        deleteIdWordToString(id);
        System.out.println(chuoiStudying_array);
        try {
            // update studying_array
            String updateQuery = "UPDATE account SET studying_array = ? WHERE user_name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, chuoiStudying_array);
            preparedStatement.setString(2, UserName);

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connector.closeConnection();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("LỖI setStuding_array: " + e.getMessage());
            return false;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
