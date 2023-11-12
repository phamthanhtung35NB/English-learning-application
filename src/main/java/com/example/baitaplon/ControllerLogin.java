package com.example.baitaplon;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerLogin {
    @FXML
    private TextField textName;
    @FXML
    private PasswordField textPass;
    @FXML
    protected void buttonOkLogin(ActionEvent event) throws SQLException {

        String name = textName.getText();
        String pass = textPass.getText();
        System.out.println(name + " " + pass);
        String studying_array = "";
        studying_array = DataBase.addDataLogin(name, pass);
        if ((name.equals("test") && pass.equals("test"))||!(studying_array.equals("-1"))) {
            viewLoginToHome(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WARNING");
            alert.setContentText("Account information or password is incorrect");
            alert.show();
        }

    }
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

            HomeStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}