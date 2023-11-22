package com.example.baitaplon;

//import com.example.baitaplon.ControllerTabSql;
//import com.example.baitaplon.SQLiteConnector;
//import com.example.baitaplon.WordSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DictionaryManagement {
//    Scanner scan = new Scanner(System.in);
/////////////////////////////////////////LOAD DATA OF STUDYING ARRAY///////////////////////////////////////////////////////
//lodata study_array in ControllerSoTuCaNhan.dataSoTu
public static void dictionarySQLiteLoadAll() {
    SQLiteConnector connector = new SQLiteConnector();
    Connection connection = connector.getConnection();
    try {
        Statement statement = connection.createStatement();

        // lay ra chuoi string
        String queryList = "SELECT id,word,html,description,pronounce FROM av;";

        PreparedStatement preparedStatement = connection.prepareStatement(queryList);
//            preparedStatement.setString(1, chuoiStudying_array);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
//            System.out.println("r:"+resultSet);
        //xoa dataSoTu
        ControllerTabSql.dataWordinSql.clear();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.println(id);
            String word_target = resultSet.getString("word");
            String html = resultSet.getString("html");
            String word_explain = resultSet.getString("description");
            String pronounce = resultSet.getString("pronounce");
            System.out.println(id + " " + word_target + " " + word_explain + " " + html + " " + pronounce);
            WordSQL word = new WordSQL(id, word_target, word_explain, html, pronounce);
            ControllerTabSql.dataWordinSql.put(word_target, word);
        }
        resultSet.close();
        statement.close();
        connection.close();
        connector.closeConnection();
    } catch (Exception e) {
        System.out.println("LỖI login :" + e.getMessage());
    }
}


    /**
     * Tìm từ bằng Cơ sở dữ liệu.
     * Gọi hàm: mở Terminal, thao tác nhập từ cần tìm, trả lại ý nghĩa của từ. Tất cả qua Terminal
     * Link vào phần giao diện
     */
    public static boolean dictionarySQLiteSearch(String input) {
        System.out.print("Tim tu: ");
//        String input = scan.nextLine();
        String searchWord = input.toLowerCase().trim();

        SQLiteConnector connector = new SQLiteConnector();
        Connection connection = connector.getConnection();

        try {
            Statement statement = connection.createStatement();
            String querry = "SELECT * FROM av WHERE word = '" + searchWord + "';";
            ResultSet resultSet = statement.executeQuery(querry);
            String mean = null;
            Boolean ifNot = false;
            while (resultSet.next()) {
                mean = resultSet.getString("description");
                System.out.println(mean);
            }
            if (mean==null) {
                ifNot = false;
            } else {
                ifNot = true;
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("LỖI:" + e.getMessage());
        }
        connector.closeConnection();
        return true;
    }

    /**
     * Thêm từ bằng Cơ sở dữ liệu.
     * Gọi hàm: mở Terminal, thao tác nhập từ cần thêm
     * Nếu từ đã tồn tại - in ra lỗi
     * Nếu từ chưa tồn tại yêu cầu nhập thêm ý nghĩa từ đó
     * Tất cả qua Terminal
     * Link vào phần giao diện
     */
    public static String addWordInSQLiteDB(String inputWord, String inputDescription) {
        System.out.print("Thêm từ: ");
//        String input = scan.nextLine();
        String addWord = inputWord.toLowerCase().trim();
        //Check whether it was in DB or not
        SQLiteConnector connectorSQLite = new SQLiteConnector();
        Connection connection = connectorSQLite.getConnection();
        boolean ifNot = false;
        String querry;
        String description = null;
        try {
            //kiem tra xem tu da co trong csdl chua
            querry = "SELECT id,description FROM av WHERE word = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, addWord);
            ResultSet resultSet = preparedStatement.executeQuery();
            int id = 0;


            while (resultSet.next()) {
                id = resultSet.getInt("id");
                description = resultSet.getString("description");
            }
            System.out.println(id);
            //Neu chua co thi yeu cau nhap nghia
            //Neu co roi thi bao loi
            if (id>0) {
                ifNot = false;
            } else {
                ifNot = true;
            }
            if (ifNot) {
                // Select max id
                Statement maxIdStatement = connection.createStatement();
                ResultSet maxIdResultSet = maxIdStatement.executeQuery("SELECT MAX(id) AS max_id FROM av;");
                Integer maxId = 0;

                if (maxIdResultSet.next()) {
                    maxId = (Integer) maxIdResultSet.getInt("max_id") + 1;
                }
                System.out.println(maxId);
//                System.out.println("Nhap nghia cua tu " + addWord);
                String mean = inputDescription.trim();
                //Add at last in database
                querry = "INSERT INTO av (id,word, html) VALUES (?,?, ?);";
                preparedStatement = connection.prepareStatement(querry);
                preparedStatement.setInt(1, maxId);
                preparedStatement.setString(2, addWord);
                preparedStatement.setString(3, mean);
                int check = preparedStatement.executeUpdate();
                if (check > 0) {
                    System.out.println("Thêm thành công từ vào CSDL");
                } else {
                    System.out.println("LỖI: KHÔNG THỂ THÊM TỪ VÀO CSDL");
                }
                maxIdResultSet.close();
                maxIdStatement.close();
            } else {
                resultSet.close();
                preparedStatement.close();
                connection.close();
                connectorSQLite.closeConnection();
                //Note
                System.out.println("TỪ ĐÃ TỒN TẠI");
                System.out.println(description);
                return description;
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("LỖI: " + e.getMessage());
        }
        connectorSQLite.closeConnection();
        return "done";
    }

    /**
     * Xóa từ bằng Cơ sở dữ liệu.
     * Gọi hàm: mở Terminal, thao tác nhập từ cần xóa
     * Nếu từ chưa có trong csdl - in ra lỗi
     * Xóa từ đó nếu đã tồn tại trong CSDL
     * Tất cả qua Terminal
     * Link vào phần giao diện
     */
    public static String dropWordInSQLiteDB(String input) {
        System.out.print("Xoa tu: ");
//        String input = scan.nextLine();
        String wordDrop = input.toLowerCase().trim();
        String retunrString = "";
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
                    retunrString="Delete Done";
                } else {
                    System.out.println("LỖI: KHÔNG THỂ XÓA từ " + wordDrop + " khỏi CSDL!");
                }
            } else {
                //Khong co nua thi bao loi
                System.out.println("LỖI: KHÔNG TÌM ĐƯỢC TỪ ĐÃ CHO");
                retunrString="Not Found";
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("LỖI: " + e.getMessage());
        }
        connectorSQLite.closeConnection();
    return retunrString;
    }
}
