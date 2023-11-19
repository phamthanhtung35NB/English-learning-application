package com.example.baitaplon;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;
public class DataBase {
    //
    private static Set<Integer> numberIdCuaTuDien = new TreeSet<>();
    private static String chuoiStudying_array = "-1";
    private static String UserName = "";
    //main
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        //login
        System.out.println(dataBase.checkSQLiteLogin("admin1", "admin11"));
        DataBase.loadDataSqlOfSoTuCaNhan();
    }

    //check tai khoan co dung khong
    //neu tk mk sai tra ve -1
    //neus studing_array null tra ve chuoi rong ""
    //neu tk mk dung tra ve chuoi studying_array
    public String checkSQLiteLogin(String username, String password) {
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
    //lodata study_array in ControllerSoTuCaNhan.dataSoTu
    public static void loadDataSqlOfSoTuCaNhan() {
        SQLiteConnector connector = new SQLiteConnector();
        Connection connection = connector.getConnection();
        try {
            Statement statement = connection.createStatement();

            // lay ra chuoi string
            String queryList = "SELECT id,word, definition FROM dictionary WHERE id IN (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(queryList);
            preparedStatement.setString(1, chuoiStudying_array);
            ResultSet resultSet = preparedStatement.executeQuery();
            //xoa dataSoTu
            ControllerSoTayCaNhan.dataSoTu.clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String word_target = resultSet.getString("target");
                String word_explain = resultSet.getString("definition");
                IndividualWord word = new IndividualWord(word_target, word_explain, id);
                ControllerSoTayCaNhan.dataSoTu.put(word_target, word);
            }
            //show dataSoTu
            for (String key : ControllerSoTayCaNhan.dataSoTu.keySet()) {
                System.out.println(key + " " + ControllerSoTayCaNhan.dataSoTu.get(key).getWord_explain());
            }
            resultSet.close();
            statement.close();
            connection.close();
            connector.closeConnection();
        } catch (Exception e) {
            System.out.println("LỖI login :" + e.getMessage());
        }
    }

    // add new account
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

            // Add new account
            String insertQuery = "INSERT INTO account (id_account, user_name, password) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, maxId);
            insertStatement.setString(2, username);
            insertStatement.setString(3, password);

            int rowsAffectedCheck = insertStatement.executeUpdate();

            checkResultSet.close();
            maxIdResultSet.close();
            checkStatement.close();
            maxIdStatement.close();
            insertStatement.close();
            // close connection and return true
            connector.closeConnection();
            if (rowsAffectedCheck > 0){
                return "success";
            } else {
                return "fail";
            }

        } catch (SQLException e) {
            System.out.println("LỖI new account: " + e.getMessage());
            return "fail";
        }
    }
    //doi mat khau
    public static String changePasswordSQLite(String password,String newPassword) {
        SQLiteConnector connector = new SQLiteConnector();
        Connection connection = connector.getConnection();

        try {
            String checkQuery = "SELECT * FROM account WHERE user_name = ? AND password = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, UserName);
            checkStatement.setString(2, password);
            ResultSet checkResultSet = checkStatement.executeQuery();

            while (checkResultSet.next()) {
                if (checkResultSet.getString("password").equals(password)&&checkResultSet.getString("user_name").equals(UserName)){
                    String updateQuery = "UPDATE account SET password = ? WHERE user_name = ?;";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1,newPassword);
                    preparedStatement.setString(2, UserName);

                    int rowsAffected = preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connector.closeConnection();
                    checkResultSet.close();
                    checkStatement.close();
                    if (rowsAffected > 0){
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

////////////////////////SET STUDYING ARRAY/////////////////////////////////////////////////////////////////////////////
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
            preparedStatement.setString(1,chuoiStudying_array);
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
    ///////////////////////////////////////////////////////////////////////////////////////////
    /**
     * chuyen kieu set sang string de truy van
     */

//    public static Boolean idWordToString() {
//        numberIdCuaTuDien = takeData();
//        StringBuilder idListBuilder = new StringBuilder();
//        for (Integer Tam : numberIdCuaTuDien) {
//            idListBuilder.append(Tam).append(",");
//        }
//        // xoa dau phay cuoi cung
//        if (idListBuilder.length() > 0) {
//            idListBuilder.deleteCharAt(idListBuilder.length() - 1);
//        }
//        chuoiStudying_array = idListBuilder.toString();
//        return true;
//    }
    ///////////////////////////////////DELETE STUDYING ARRAY//////////////////////////////////////////////////////////////////
    //delete
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
    //delete
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
            preparedStatement.setString(1,chuoiStudying_array);
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
    ///////////////////////////////////////////////////////////////////////////////////////////

}
//public class DataBase {
//    private final static String url = "jdbc:mysql://localhost:3306/jdbc_db";
//    private final static String username = "root";
//    private final static String password = "123456789";
//    private static String chuoiStudying_array = "";
//    private static String UserName = "";
//    protected static Set<Integer> numberIdCuaTuDien = new TreeSet<>();
//
//
//    public static Connection getConnectionToDataBase() {
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(url, username, password);
//            if (connection != null) {
//                System.out.println("Connected");
//            } else {
//                System.out.println("Failed");
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//
//    //check tai khoan da co chua
//    //tra ve true neu tk chua ton tai
//    //tra ve false neu tk da ton tai
//
//    public static boolean checkDataUsernameLogin(String username) throws SQLException {
//        //cau lech khong co tham so
//
//        Statement query = null;
//        ResultSet ketQuaTruyVan = null;
//        Connection connection = getConnectionToDataBase();
//        try {
//            System.out.println(connection);
//            query = connection.createStatement();
//            ketQuaTruyVan = query.executeQuery("select user_name from account");
//            //check tk mk
//            while (ketQuaTruyVan.next()) {
//                System.out.println(ketQuaTruyVan.getString("user_name") + "\n" + username);
//                if (username.equals(ketQuaTruyVan.getString("user_name"))) {
//                    System.out.println("true");
//                    return true;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ketQuaTruyVan != null) {
//                    ketQuaTruyVan.close();
//                }
//                if (query != null) {
//                    query.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("false");
//        return false;
//    }
//
//
//    //tra ve chuoi studying_array
//    //neu tk mk dung tra ve chuoi studying_array
//    //neus studing_array null tra ve chuoi rong ""
//    //neu tk mk sai tra ve -1
//
//    public static String checkDataLogin(String username, String password) throws SQLException {
//        //cau lech khong co tham so
//
//        Statement query = null;
//        ResultSet ketQuaTruyVan = null;
//        Connection connection = getConnectionToDataBase();
//        try {
//            System.out.println(connection);
//            query = connection.createStatement();
//            ketQuaTruyVan = query.executeQuery("select user_name,password,studying_array from account");
//            //check tk mk
//            while (ketQuaTruyVan.next()) {
//
//                System.out.printf(ketQuaTruyVan.getString("user_name") + " " + ketQuaTruyVan.getString("password") + "\n");
//                if (username.equals(ketQuaTruyVan.getString("user_name")) && password.equals(ketQuaTruyVan.getString("password"))) {
//                    UserName = ketQuaTruyVan.getString("user_name");
//                    System.out.println(ketQuaTruyVan.getString("studying_array"));
//                    if (ketQuaTruyVan.getString("studying_array") == null) {
//                        chuoiStudying_array = "";
//                        return chuoiStudying_array;
//                    } else {
//                        chuoiStudying_array = ketQuaTruyVan.getString("studying_array");
//                        return chuoiStudying_array;
//
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ketQuaTruyVan != null) {
//                    ketQuaTruyVan.close();
//                }
//                if (query != null) {
//                    query.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return "-1";
//    }
//
//    //Studing_array
//    public static boolean setStuding_array(int id) throws SQLException {
//        Connection connection = getConnectionToDataBase();
//        Statement query = connection.createStatement();
//        ResultSet ketQuaTruyVan = null;
//
//        try {
////            String sql = "UPDATE account SET studying_array = "+idWordToString()+" WHERE user_name IN '"+UserName+"';";
////            PreparedStatement preparedStatement = connection.prepareStatement(sql);
////            System.out.println(preparedStatement);
////            preparedStatement.executeUpdate();
////
//            String sql = "UPDATE account SET studying_array = ? WHERE user_name IN (?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, idWordToString()); // Assuming idWordToString() returns an int
//            preparedStatement.setString(2, UserName);
//            System.out.println(preparedStatement);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            // Close resources in the finally block to ensure they are always closed
//            try {
//                if (ketQuaTruyVan != null) {
//                    ketQuaTruyVan.close();
//                }
//                if (query != null) {
//                    query.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return true;
//    }
//
//    /**
//     * chuyen kieu set sang string de truy van
//     *
//     * @return
//     * @throws SQLException
//     */
//    public static String idWordToString() throws SQLException {
//        numberIdCuaTuDien = takeData();
//        StringBuilder idListBuilder = new StringBuilder();
//        for (Integer Tam : numberIdCuaTuDien) {
//            idListBuilder.append(Tam).append(",");
//        }
//        // xoa dau phay cuoi cung
//        if (idListBuilder.length() > 0) {
//            idListBuilder.deleteCharAt(idListBuilder.length() - 1);
//        }
//        return idListBuilder.toString();
//    }
//
//    /**
//     * load data tu dien cua so tu ca nhan
//     *
//     * @throws SQLException
//     */
//    public static void loadDataSqlOfSoTuCaNhan() throws SQLException {
//        Statement query = null;
//        ResultSet ketQuaTruyVan = null;
//        Connection connection = getConnectionToDataBase();
//        try {
//            System.out.println(connection);
//            query = connection.createStatement();
//            //idListBuilder la 1 chuoi cac id cua tu dien can truy van
//
//            // lay ra chuoi string
//            String idList = "SELECT id,target, definition FROM dictionary WHERE id IN (" + idWordToString() + ");";
//            ketQuaTruyVan = query.executeQuery(idList);
//
//            while (ketQuaTruyVan.next()) {
//                System.out.println(ketQuaTruyVan.getString("target") + " " + ketQuaTruyVan.getString("definition"));
//                int id = ketQuaTruyVan.getInt("id");
//                String word_target = ketQuaTruyVan.getString("target");
//                String word_explain = ketQuaTruyVan.getString("definition");
//                IndividualWord word = new IndividualWord(word_target, word_explain, id);
//                ControllerSoTayCaNhan.dataSoTu.put(word_target, word);
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ketQuaTruyVan != null) {
//                    ketQuaTruyVan.close();
//                }
//                if (query != null) {
//                    query.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    public static boolean newAccount(String username, String password) throws SQLException {
//        Connection connection = getConnectionToDataBase();
//        Statement query = connection.createStatement();
//        ResultSet ketQuaTruyVan = null;
//
//        try {
//            // Get the maximum account ID from the database
//            ketQuaTruyVan = query.executeQuery("SELECT MAX(id_account) AS max_id FROM account");
//            int maxId = 0;
//
//            if (ketQuaTruyVan.next()) {
//                maxId = ketQuaTruyVan.getInt("max_id");
//                maxId++;
//            }
//            System.out.println(maxId);
//
//            String sql = "INSERT INTO account (id_account, user_name, password) VALUES (?, ?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, maxId);
//            preparedStatement.setString(2, username);
//            preparedStatement.setString(3, password);
//            System.out.println(preparedStatement);
//            // Execute the SQL statement to insert the new account
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            // Close resources in the finally block to ensure they are always closed
//            try {
//                if (ketQuaTruyVan != null) {
//                    ketQuaTruyVan.close();
//                }
//                if (query != null) {
//                    query.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return true;
//    }
//
//
//    // lay tu tien anh cua dictionary bang id cua tu sau khi tach chuoi studying_array tra ve
//    public static Set<Integer> takeData() throws SQLException {
//        String[] numberStrings = chuoiStudying_array.split(",");
//
//        Set<Integer> numberSetKetQua = new TreeSet<>();
//
//        for (String numberString : numberStrings) {
//            try {
//                int number = Integer.parseInt(numberString.trim());
//                numberSetKetQua.add(number);
//            } catch (NumberFormatException e) {
//                System.out.println(numberString);
//            }
//        }
//        return numberSetKetQua;
//    }
//
//    public static void main(String[] args) throws SQLException {
//
////        System.out.println(takeData("1,32,3,21,8"));
//    }
//
////    public static void main(String[] args) throws SQLException {
//
////        System.out.println(newAccount("admin3", "admin3"));
////        Set<Integer> tam = takeData(login("admin2", "admin2"));
////        for (Integer i : tam) {
////            System.out.println(i);
////        }
//
//
//}
////    public static void main(String[] args) throws SQLException {
////        // MySQL database URL
////        System.out.println(getConnectionToDataBase());
////        Statement query = getConnectionToDataBase().createStatement();
////        ResultSet ketQuaTruyVan = query.executeQuery("select * from dictionary");
////        // show data
////        while (ketQuaTruyVan.next()) {
////            System.out.println(ketQuaTruyVan.getInt(1) + "  " + ketQuaTruyVan.getString(2)
////                    + "  " + ketQuaTruyVan.getString(3));
////        }
////        query.close();
//
