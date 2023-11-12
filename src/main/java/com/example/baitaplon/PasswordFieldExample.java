package com.example.baitaplon;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PasswordFieldExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Password Field Example");

        // Tạo các controls
        PasswordField passwordField = new PasswordField();
        CheckBox showPasswordCheckBox = new CheckBox("Hiện mật khẩu");
        Label resultLabel = new Label();

        // Thiết lập sự kiện khi check box được chọn/deselect
        showPasswordCheckBox.setOnAction(event -> {
            if (showPasswordCheckBox.isSelected()) {
                // Nếu được chọn, hiển thị mật khẩu
                passwordField.setPromptText("Mật khẩu: " + passwordField.getText());
                System.out.println(passwordField.getText());
            } else {
                // Nếu không được chọn, ẩn mật khẩu
                passwordField.setPromptText("Mật khẩu");
            }
        });

        // Thiết lập sự kiện khi mật khẩu thay đổi
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!showPasswordCheckBox.isSelected()) {
                // Nếu không hiển thị mật khẩu, hiển thị dấu * thay vì mật khẩu thực sự
                passwordField.setPromptText("Mật khẩu");
            }
        });

        // Thiết lập layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.getChildren().addAll(passwordField, showPasswordCheckBox, resultLabel);

        // Tạo scene và hiển thị stage
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
