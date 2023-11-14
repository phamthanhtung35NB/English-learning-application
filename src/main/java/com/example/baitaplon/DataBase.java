package com.example.baitaplon;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

public class DataBase {
    private final static String url = "jdbc:mysql://localhost:3306/jdbc_db";
    private final static String username = "root";
    private final static String password = "123456789";
    private static String chuoiStudying_array = "";
    private static String UserName = "";
    protected static Set<Integer> numberIdCuaTuDien = new TreeSet<>();

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

    //check tai khoan da co chua
    //tra ve true neu tk chua ton tai
    //tra ve false neu tk da ton tai
    public static boolean checkDataUsernameLogin(String username) throws SQLException {
        //cau lech khong co tham so

        Statement query = null;
        ResultSet ketQuaTruyVan = null;
        Connection connection = getConnectionToDataBase();
        try {
            System.out.println(connection);
            query = connection.createStatement();
            ketQuaTruyVan = query.executeQuery("select user_name from account");
            //check tk mk
            while (ketQuaTruyVan.next()) {
//                System.out.println(ketQuaTruyVan.getString("user_name") + "\n" + username);
                if (username.equals(ketQuaTruyVan.getString("user_name"))) {
                    System.out.println("true");
                    return true;
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
        System.out.println("false");
        return false;
    }

    //tra ve chuoi studying_array
    //neu tk mk dung tra ve chuoi studying_array
    //neus studing_array null tra ve chuoi rong ""
    //neu tk mk sai tra ve -1
    public static String checkDataLogin(String username, String password) throws SQLException {
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
                    UserName=ketQuaTruyVan.getString("user_name");
                    System.out.println(ketQuaTruyVan.getString("studying_array"));
                    if (ketQuaTruyVan.getString("studying_array") == null) {
                        chuoiStudying_array = "";
                        return chuoiStudying_array;
                    } else {
                        chuoiStudying_array = ketQuaTruyVan.getString("studying_array");
                        return chuoiStudying_array;
                    }
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
//Studing_array
    public static boolean setStuding_array(int id) throws SQLException {
        Connection connection = getConnectionToDataBase();
        Statement query = connection.createStatement();
        ResultSet ketQuaTruyVan = null;

        try {
//            String sql = "UPDATE account SET studying_array = "+idWordToString()+" WHERE user_name IN '"+UserName+"';";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            System.out.println(preparedStatement);
//            preparedStatement.executeUpdate();
//
            String sql = "UPDATE account SET studying_array = ? WHERE user_name IN (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idWordToString()); // Assuming idWordToString() returns an int
            preparedStatement.setString(2, UserName);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close resources in the finally block to ensure they are always closed
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

    /**
     * chuyen kieu set sang string de truy van
     * @return
     * @throws SQLException
     */
    public static String idWordToString() throws SQLException {
        numberIdCuaTuDien = takeData();
        StringBuilder idListBuilder = new StringBuilder();
        for (Integer Tam : numberIdCuaTuDien) {
            idListBuilder.append(Tam).append(",");
        }
        // xoa dau phay cuoi cung
        if (idListBuilder.length() > 0) {
            idListBuilder.deleteCharAt(idListBuilder.length() - 1);
        }
        return idListBuilder.toString();
    }

    /**
     * load data tu dien cua so tu ca nhan
     * @throws SQLException
     */
    public static void loadDataSqlOfSoTuCaNhan() throws SQLException {
        Statement query = null;
        ResultSet ketQuaTruyVan = null;
        Connection connection = getConnectionToDataBase();
        try {
            System.out.println(connection);
            query = connection.createStatement();
            //idListBuilder la 1 chuoi cac id cua tu dien can truy van

            // lay ra chuoi string
            String idList = "SELECT id,target, definition FROM dictionary WHERE id IN (" + idWordToString() + ");";
            ketQuaTruyVan = query.executeQuery(idList);

            while (ketQuaTruyVan.next()) {
                System.out.println(ketQuaTruyVan.getString("target") + " " + ketQuaTruyVan.getString("definition"));
                int id = ketQuaTruyVan.getInt("id");
                String word_target = ketQuaTruyVan.getString("target");
                String word_explain = ketQuaTruyVan.getString("definition");
                IndividualWord word = new IndividualWord(word_target, word_explain, id);
                ControllerSoTayCaNhan.dataSoTu.put(word_target, word);
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
    }

    public static boolean newAccount(String username, String password) throws SQLException {
        Connection connection = getConnectionToDataBase();
        Statement query = connection.createStatement();
        ResultSet ketQuaTruyVan = null;

        try {
            // Get the maximum account ID from the database
            ketQuaTruyVan = query.executeQuery("SELECT MAX(id_account) AS max_id FROM account");
            int maxId = 0;

            if (ketQuaTruyVan.next()) {
                maxId = ketQuaTruyVan.getInt("max_id");
                maxId++;
            }
            System.out.println(maxId);

            String sql = "INSERT INTO account (id_account, user_name, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, maxId);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            System.out.println(preparedStatement);
            // Execute the SQL statement to insert the new account
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close resources in the finally block to ensure they are always closed
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


    // lay tu tien anh cua dictionary bang id cua tu sau khi tach chuoi studying_array tra ve
    public static Set<Integer> takeData() throws SQLException {
        String[] numberStrings = chuoiStudying_array.split(",");
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
//        System.out.println(takeData("1,32,3,21,8"));
    }

//    public static void main(String[] args) throws SQLException {
//        System.out.println(newAccount("admin3", "admin3"));
//        Set<Integer> tam = takeData(login("admin2", "admin2"));
//        for (Integer i : tam) {
//            System.out.println(i);
//        }


//    }
    //test
//    @Test
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
//    }
}