package com.example.baitaplon;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class ControllerGamePronounce extends Application {
    @FXML
    private ToggleButton toggleButton;
    @FXML
    Label requestLabel;
    @FXML
    Label wordsReadLabel;
    @FXML
    private void startRecording() {
        System.out.println("Recording started");
    }
    private void startRecordingLoad() {

        String result = micString();

        if ("hello".equalsIgnoreCase(result.trim())) {
            System.out.println("Correct pronunciation! You win!");
        } else {
            System.out.println("Incorrect pronunciation. The correct word is 'hello'. Try again!");
        }
    }

    private void stopRecording() {
        System.out.println("Recording stopped");
    }


    private String micString() {
        return "hello";
    }
    @FXML
    private void toggleRecording() {
        requestLabel.setText("Please read the word below:");
        if (toggleButton.isSelected()) {
            // Bắt đầu thu âm khi ToggleButton được chọn
            startRecordingLoad();
            wordsReadLabel.setText("hello");
        } else {
            // Kết thúc thu âm khi ToggleButton không được chọn
            stopRecording();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Pronounce.fxml"));
        primaryStage.setTitle("Speech Pronunciation Test");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}