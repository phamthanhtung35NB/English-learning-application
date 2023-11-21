package com.example.baitaplon.Login;


import com.example.baitaplon.DataBase.DataBase;
import com.example.baitaplon.DataBase.DictionaryManagement;
import com.example.baitaplon.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerLogin {

    protected static String studying_array = "";

    /**
     * Khai báo các thành phần trong giao diện login
     */
    @FXML
    private TextField textName;
    @FXML
    private PasswordField textPass;
    @FXML
    private Label labelShowPass;
    @FXML
    private CheckBox trueShow;

//    @FXML
//    protected void buttonCancelLogin(ActionEvent event) {
//        Node source = (Node) event.getSource();
//        Stage currentStage = (Stage) source.getScene().getWindow();
//        currentStage.close();
//    }

    @FXML
    public void initialize() {
    }

    /**
     * Hiển thị mật khẩu khi check vào ô hiển thị mật khẩu
     *
     * @param event
     */
    @FXML
    protected void checkBoxShowPass(ActionEvent event) {
        if (trueShow.isSelected()) {
            // If selected, display the password
            labelShowPass.setText(textPass.getText());
            System.out.println(labelShowPass.getText());
        } else {
            // If not selected, hide the password
            labelShowPass.setText("");
        }
    }

    /**
     * Chuyển sang giao diện home sau khi đăng nhập thành công và đóng giao diện login
     *
     * @param event
     */
    public void viewLoginToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage HomeStage = new Stage();
            HomeStage.setTitle("Home");
            HomeStage.setScene(scene);

            /**
             * Lấy Node từ sự kiện
             * Đối tượng event là sự kiện mà chúng ta đã gán cho nút đăng nhập
             * Đối tượng source là Node gốc mà sự kiện được phát ra
             * Trong trường hợp này, Node gốc là nút đăng nhập
             * Sau khi có Node gốc, chúng ta có thể lấy Stage từ Node gốc
             * Cuối cùng, chúng ta có thể đóng Stage
             */
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
            if (HomeController.isLoadData == false) {
                DictionaryManagement.dictionarySQLiteLoadAll();
                HomeController.isLoadData = true;
            }
            if (HomeController.isLoadDataOfSoTuCaNhan == false) {
                DataBase.loadDataSqlOfSoTuCaNhan();
                HomeController.isLoadDataOfSoTuCaNhan = true;
            }
            HomeStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chuyển sang giao diện new account
     *
     * @param event
     * @throws IOException
     */
    @FXML
    protected void buttonNewAccount(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginNewAccount.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
    }

    @FXML
    protected void buttonChangePassword(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginChangePassword.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
    }

    /**
     * Kiểm tra tài khoản và mật khẩu có đúng không và chuyển sang giao diện home
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    protected void buttonOkLogin(ActionEvent event) {

        String name = textName.getText();
        String pass = textPass.getText();
        System.out.println(name + " " + pass);
        studying_array = DataBase.checkSQLiteLogin(name, pass);
//        System.out.println(studying_array);
        if ((name.equals("test") && pass.equals("test")) || !(studying_array.equals("-1"))) {
            System.out.println("Đăng nhập thành công");
            viewLoginToHome(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WARNING");
            alert.setContentText("Account information or password is incorrect");
            alert.show();
        }
    }


    /////////////////////////////////NEW ACCOUNT///////////////////////////////////////////////
    /**
     * Khai báo các thành phần trong giao diện new account
     */
    @FXML
    private TextField textNameNew;
    @FXML
    private PasswordField textPassNew;
    @FXML
    private PasswordField textRepeatPassNew;
    @FXML
    private Label labelShowPassNewAccount;
    @FXML
    private Label labelShowPassNewAccountRepeat;
    @FXML
    private CheckBox trueShowNewAccount;
    @FXML
    private CheckBox trueShowNewAccountRepeat;

    /**
     * Hiển thị mật khẩu khi check vào ô hiển thị mật khẩu cuả new account
     *
     * @param event
     */
    @FXML
    protected void checkBoxShowPassNewAccount(ActionEvent event) {
        if (trueShowNewAccount.isSelected()) {
            // If selected, display the password
            labelShowPassNewAccount.setText(textPassNew.getText());
        } else {
            // If not selected, hide the password
            labelShowPassNewAccount.setText("");
        }
    }

    /**
     * Hiển thị mật khẩu khi check vào ô hiển thị mật khẩu nhap lai cuả new account
     *
     * @param event
     */
    @FXML
    protected void checkBoxShowPassNewAccountRepeat(ActionEvent event) {
        if (trueShowNewAccountRepeat.isSelected()) {
            // If selected, display the password
            labelShowPassNewAccountRepeat.setText(textRepeatPassNew.getText());
//            System.out.println(labelShowPass.getText());
        } else {
            // If not selected, hide the password
            labelShowPassNewAccountRepeat.setText("");
        }
    }

    /**
     * Chuyển sang giao diện login
     *
     * @param event
     * @throws IOException
     */
    @FXML
    protected void buttonCancelNewAccount(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
    }

    /**
     * Tạo tài khoản mới
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    protected void buttonOkNewAccount(ActionEvent event) throws IOException {
        if (textPassNew.getText().equals(textRepeatPassNew.getText()) && !textPassNew.getText().equals("") && !textNameNew.getText().equals("")) {
            String name = textNameNew.getText();
            String pass = textPassNew.getText();

            String ketQua = DataBase.newAccountSQLite(name, pass);
            //Tài khoản đã tồn tại
            if (ketQua.equals("duplicate")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WARNING");
                System.out.println("Tài khoản đã tồn tại");
                alert.setContentText("Account already exists");
                alert.show();
            } else if (ketQua.equals("success")) {
                //tao tai khoan moi thanh cong
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WARNING");
                System.out.println("Tạo tài khoản mới thành công");
                alert.setContentText("Create account successfully\nYour account and password respectively: \nAccount[" + name + "]\nPassword[" + pass + "]}");
                alert.show();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Login.fxml"));
                Parent sampleParent = loader.load();
                Scene scene = new Scene(sampleParent);
                stage.setScene(scene);
            }
            //Tạo tài khoản mới thất bại
            else if (ketQua.equals("fail")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WARNING");
                System.out.println("Tạo tài khoản mới thất bại");
                alert.setContentText("Create account failed");
                alert.show();

            }
//            Tên tài khoản và Mật khẩu không được để trống, mật khẩu và mật khẩu lặp lại phải giống nhau
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WARNING");
            System.out.println("Tên tài khoản và Mật khẩu không được để trống, mật khẩu và mật khẩu lặp lại phải giống nhau");
            alert.setContentText("Account name and Password cannot be blank, password and repeated password need to be the same");
            alert.show();
        }
    }
    /////////////////////////////////////CHANGE PASSWORD/////////////////////////////////////////
    /**
     * Khai báo các thành phần trong giao diện new account
     */
    @FXML
    private TextField textNameChange;
    @FXML
    private PasswordField textPassChange;
    @FXML
    private PasswordField textPassChangeNew;
    @FXML
    private PasswordField textRepeatPassChangeNew;
    @FXML
    private Label labelShowPassOld;
    @FXML
    private Label labelShowPassChangeNew;
    @FXML
    private Label labelShowPassChangeRepeatNew;
    @FXML
    private CheckBox trueShowPassOld;
    @FXML
    private CheckBox trueShowPassChangeNew;
    @FXML
    private CheckBox trueShowPassChangeRepeatNew;

    /**
     * Hiển thị mật khẩu khi check vào ô hiển thị mật khẩu cuả new account
     *
     * @param event
     */
    @FXML
    protected void checkBoxShowPassOld(ActionEvent event) {
        if (trueShowPassOld.isSelected()) {
            // If selected, display the password
            labelShowPassOld.setText(textPassChange.getText());
        } else {
            // If not selected, hide the password
            labelShowPassOld.setText("");
        }
    }

    /**
     * Hiển thị mật khẩu khi check vào ô hiển thị mật khẩu nhap lai cuả new account
     *
     * @param event
     */
    @FXML
    protected void checkBoxShowPassChangeNew(ActionEvent event) {
        if (trueShowPassChangeNew.isSelected()) {
            // If selected, display the password
            labelShowPassChangeNew.setText(textPassChangeNew.getText());
//            System.out.println(labelShowPass.getText());
        } else {
            // If not selected, hide the password
            labelShowPassChangeNew.setText("");
        }
    }

    /**
     * Hiển thị mật khẩu khi check vào ô hiển thị mật khẩu nhap lai cuả new account
     *
     * @param event
     */
    @FXML
    protected void checkBoxShowPassChangeRepeatNew(ActionEvent event) {
        if (trueShowPassChangeRepeatNew.isSelected()) {
            // If selected, display the password
            labelShowPassChangeRepeatNew.setText(textRepeatPassChangeNew.getText());
//            System.out.println(labelShowPass.getText());
        } else {
            // If not selected, hide the password
            labelShowPassChangeRepeatNew.setText("");
        }
    }

    /**
     * Doi mat khau
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    protected void buttonOkChangePass(ActionEvent event) throws IOException {
        if (textRepeatPassChangeNew.getText().equals(textPassChangeNew.getText()) && !textNameChange.getText().equals("") &&
                !textPassChange.getText().equals("") && !textPassChangeNew.getText().equals("")) {
            String name = textNameChange.getText();
            String pass = textPassChange.getText();
            String passNew = textPassChangeNew.getText();


            String ketQua = DataBase.changePasswordSQLite(name, pass, passNew);
            //sai tên tài khoản hoặc mật khẩu
            if (ketQua.equals("IncorrectAccountOrPassword")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WARNING");
                alert.setContentText("Incorrect Account Or Password");
                alert.show();
            } else if (ketQua.equals("success")) {
                //tao tai khoan moi thanh cong
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WARNING");
                alert.setContentText("Done");
                alert.show();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Login.fxml"));
                Parent sampleParent = loader.load();
                Scene scene = new Scene(sampleParent);
                stage.setScene(scene);
            }
            //Tạo tài khoản mới thất bại
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WARNING");
                alert.setContentText("Error");
                alert.show();

            }
//            Tên tài khoản và Mật khẩu không được để trống, mật khẩu và mật khẩu lặp lại phải giống nhau
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WARNING");
            System.out.println("Tên tài khoản và Mật khẩu không được để trống, mật khẩu và mật khẩu lặp lại phải giống nhau");
            alert.setContentText("Account name and Password cannot be blank, password and repeated password need to be the same");
            alert.show();
        }
    }
}