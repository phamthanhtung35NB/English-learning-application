package com.example.baitaplon;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerGame extends Application {
    @FXML
    private void hangman() {
        // Mở mic hoặc thực hiện các thao tác cần thiết khi bắt đầu thu âm
        System.out.println("hangman started");
    }

    @FXML
    private void pronounce() {
        // Mở mic hoặc thực hiện các thao tác cần thiết khi bắt đầu thu âm
        System.out.println("pronounce started");
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TabGame.fxml"));
        primaryStage.setTitle("Speech Pronunciation Test");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


}
